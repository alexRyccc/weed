package com.weed.loginfo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weed.loginfo.annotion.ExcelColumn;
import com.weed.loginfo.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: create by Alexryc
 * @version: v1.0
 * @description: com.weed.loginfo.util
 * @date 2020/12/9 17:20
 */
public class ExcelReadUtil {

    private static NumberFormat numberFormat = NumberFormat.getInstance();
    static {
        numberFormat.setGroupingUsed(false);
    }

    public static final String PATTERN = "yyyy-MM-dd";

    public final static String DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    /**
     * 输出excel文件
     *
     * @param data 数据集合
     */
    public static File outExcelFile(List<?> data, String fileType) {
        String prefix = fileType + "_" +DateUtil.format(new Date(), DATE_TIME_PATTERN) + "_";
        File file = null;
        try {
            file = File.createTempFile(prefix, ".xls");
        } catch (IOException e) {
            throw new ServiceException("文件创建失败");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);

        // 创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建sheet
        Sheet sheet = wb.createSheet("sheel");

        // 创建表头行
        org.apache.poi.ss.usermodel.Row row = sheet.createRow(0);

        // 创建单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        // 居中显示
        style.setAlignment(HorizontalAlignment.CENTER);

        // 获取实体所有属性
        Field[] fields = data.get(0).getClass().getDeclaredFields();
        // 列索引
        int index = 0;
        // 列名称
        String name = "";
        ExcelColumn excelColumn;

        // 创建表头
        for (Field f : fields) {
            // 是否是注解
            if (f.isAnnotationPresent(ExcelColumn.class)) {
                // 获取注解
                excelColumn = f.getAnnotation(ExcelColumn.class);
                // 获取列索引
                index = excelColumn.columnIndex();
                // 列名称
                name = excelColumn.columnName();
                // 创建单元格
                creCell(row, index, name, style);
            }
        }

        // 行索引  因为表头已经设置，索引行索引从1开始
        int rowIndex = 1;
        for (Object obj : data) {
            // 创建新行，索引加1,为创建下一行做准备
            row = sheet.createRow(rowIndex++);
            for (Field f : fields) {
                // 设置属性可访问
                f.setAccessible(true);
                // 判断是否是注解
                if (f.isAnnotationPresent(ExcelColumn.class)) {
                    // 获取注解
                    excelColumn = f.getAnnotation(ExcelColumn.class);
                    // 获取列索引
                    index = excelColumn.columnIndex();
                    try {
                        // 创建单元格     f.get(obj)从obj对象中获取值设置到单元格中
                        String cellValue = "";
                        Object value = f.get(obj);
                        if (value instanceof Boolean) {
                            cellValue = "是";
                            if (!(Boolean) value) {
                                cellValue = "否";
                            }
                        } else if (value instanceof Date) {
                            cellValue = sdf.format((Date) value);
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            if (value != null) {
                                cellValue = value.toString();
                            }
                        }
                        creCell(row, index, cellValue, style);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            wb.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (wb != null) {
                    try {
                        wb.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 读取excel文件，并把读取到的数据封装到clazz中
     *
     * @param path  文件路径
     * @param clazz 实体类
     * @return 返回clazz集合
     */
    public static <T extends Object> List<T> readExcelFile(String path, Class<T> clazz) {
        // 存储excel数据
        List<T> list = new ArrayList<>();
        FileInputStream is = null;

        try {
            is = new FileInputStream(new File(path));
        } catch (FileNotFoundException e1) {
            throw new RuntimeException("文件路径异常");
        }

        Workbook wookbook = null;

        // 根据excel文件版本获取工作簿
        if (path.endsWith(".xls")) {
            wookbook = xls(is);
        } else if (path.endsWith(".xlsx")) {
            wookbook = xlsx(is);
        } else {
            throw new RuntimeException("文件出错，非excel文件");
        }

        // 得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);

        // 获取行总数
        int rows = sheet.getLastRowNum() + 1;

        org.apache.poi.ss.usermodel.Row row;

        // 获取类所有属性
        Field[] fields = clazz.getDeclaredFields();

        T obj = null;
        int coumnIndex = 0;
        org.apache.poi.ss.usermodel.Cell cell = null;
        ExcelColumn excelColumn = null;
        for (int i = 1; i < rows; i++) {
            // 获取excel行
            row = sheet.getRow(i);
            if(row != null) {
                try {
                    // 创建实体
                    obj = clazz.newInstance();
                    for (Field f : fields) {
                        // 设置属性可访问
                        f.setAccessible(true);
                        // 判断是否是注解
                        if (f.isAnnotationPresent(ExcelColumn.class)) {
                            // 获取注解
                            excelColumn = f.getAnnotation(ExcelColumn.class);
                            // 获取列索引
                            coumnIndex = excelColumn.columnIndex();
                            // 获取单元格
                            cell = row.getCell(coumnIndex);
                            // 设置属性
                            if (cell != null && StringUtils.isNotBlank(String.valueOf(cell))) {
                                setFieldValue(obj, f, wookbook, cell);
                            }
                        }
                    }
                    // 添加到集合中
                    String json = JSON.toJSONString(obj);
                    JSONObject jsonObject = JSON.parseObject(json);
                    if (!jsonObject.isEmpty()) {
                        list.add(obj);
                    }
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        }

        try {
            //释放资源
            wookbook.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 设置属性值
     *
     * @param obj  操作对象
     * @param f    对象属性
     * @param cell excel单元格
     */
    private static void setFieldValue(Object obj, Field f, Workbook wookbook, org.apache.poi.ss.usermodel.Cell cell) {
        try {
            if(cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC){
                String value = numberFormat.format(cell.getNumericCellValue());
                f.set(obj, value);
            }else {
                f.set(obj, String.valueOf(cell));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对excel 2003处理
     */
    private static Workbook xls(InputStream is) {
        try {
            // 得到工作簿
            return new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对excel 2007处理
     */
    private static Workbook xlsx(InputStream is) {
        try {
            // 得到工作簿
            return new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建单元格
     *
     * @param row
     * @param c
     * @param cellValue
     * @param style
     */
    private static void creCell(Row row, int c, String cellValue, CellStyle style) {
        Cell cell = row.createCell(c);
        cell.setCellValue(cellValue);
        cell.setCellStyle(style);
    }
}