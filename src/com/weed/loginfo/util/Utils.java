package com.weed.loginfo.util;

import com.weed.loginfo.annotion.Check;
import com.weed.loginfo.annotion.CheckField;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author Alexryc
 * @Date 2020/10/30/030 14:02
 * @Version 1.0
 */
public class Utils {

    public static <E> String  judgeArray(Object a,Object b){
        String typeNameA = a.getClass().getTypeName();
        String typeNameB = b.getClass().getTypeName();
        if (!typeNameA.equals(typeNameB)){
            return "对象类型不一致";
        }
        List listA =new ArrayList();
        List listB =new ArrayList();
        Object[] aArrays = (Object[]) a;
        Object[] bArrays = (Object[]) b;
        List<E> addList = new ArrayList<E>();
        List<E> reduceaList = new ArrayList<E>();

        //对新增的数据进行归纳
        for (int i = 0; i < aArrays.length; i++) {
            if (!myArrayContains(bArrays, aArrays[i])) {
                addList.add((E) aArrays[i]);
            }
        }
        //对减少的数据进行归纳
        for (int i = 0; i < bArrays.length; i++) {
            if (!myArrayContains(aArrays, bArrays[i])) {
                reduceaList.add((E) bArrays[i]);
            }
        }
        StringBuilder recordLog = new StringBuilder();
        if (addList.size() != 0) {
            recordLog.append("新增信息：" + addList).append(" ");
        }

        if (reduceaList.size() != 0) {
            recordLog.append("减少信息：" + reduceaList).append(" ");
        }
        return recordLog.toString();
    }
    /**
     * 计算列表aList相对于bList的增加的情况，兼容任何类型元素的列表数据结构
     *
     * @param aArrays 新集合
     * @param bArrays 旧集合
     * @return 返回增加的元素组成的列表
     */
    public static <E> List<E> getAddArrys(Object[]  aArrays, Object[] bArrays) {
        List<E> addList = new ArrayList<E>();
        for (int i = 0; i < aArrays.length; i++) {
            if (!myArrayContains(bArrays, aArrays[i])) {
                addList.add((E) aArrays[i]);
            }
        }
        return addList;
    }

    /**
     * 计算列表aList相对于bList的减少的情况，兼容任何类型元素的列表数据结构
     *
     * @param aArrays 新集合
     * @param bArrays 旧集合
     * @return 返回减少的元素组成的列表
     */
    public static <E> List<E> getReduceArrays(Object[]  aArrays, Object[] bArrays) {
        List<E> reduceaList = new ArrayList<E>();
        for (int i = 0; i < bArrays.length; i++) {
            if (!myArrayContains(aArrays, bArrays[i])) {
                reduceaList.add((E) bArrays[i]);
            }
        }
        return reduceaList;
    }
    /**
     * 判断元素element是否是sourceList列表中的一个子元素
     *
     * @param sourceArray 源列表
     * @param element    待判断的包含元素
     * @return 包含返回 true，不包含返回 false
     */
    private static <E> boolean myArrayContains(Object[] sourceArray, E element) {
        if (sourceArray == null || element == null) {
            return false;
        }
        if (sourceArray.length==0) {
            return false;
        }
        for (Object tip : sourceArray) {
            if (element.equals(tip)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 计算列表aList相对于bList的增加的情况，兼容任何类型元素的列表数据结构
     *
     * @param aList 新列表
     * @param bList 旧列表
     * @return 返回增加的元素组成的列表
     */
    public static <E> List<E> getAddaListThanbList(List<E> aList, List<E> bList) {
        List<E> addList = new ArrayList<E>();
        for (int i = 0; i < aList.size(); i++) {
            if (!myListContains(bList, aList.get(i))) {
                addList.add(aList.get(i));
            }
        }
        return addList;
    }

    /**
     * 计算列表aList相对于bList的减少的情况，兼容任何类型元素的列表数据结构
     *
     * @param aList 新列表
     * @param bList 旧列表
     * @return 返回减少的元素组成的列表
     */
    public static <E> List<E> getReduceaListThanbList(List<E> aList, List<E> bList) {
        List<E> reduceaList = new ArrayList<E>();
        for (int i = 0; i < bList.size(); i++) {
            if (!myListContains(aList, bList.get(i))) {
                reduceaList.add(bList.get(i));
            }
        }
        return reduceaList;
    }

    /**
     * 判断元素element是否是sourceList列表中的一个子元素
     *
     * @param sourceList 源列表
     * @param element    待判断的包含元素
     * @return 包含返回 true，不包含返回 false
     */
    private static <E> boolean myListContains(List<E> sourceList, E element) {
        if (sourceList == null || element == null) {
            return false;
        }
        if (sourceList.isEmpty()) {
            return false;
        }
        for (E tip : sourceList) {
            if (element.equals(tip)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 计算列表aList相对于bList的减少的情况，兼容任何类型元素的列表数据结构
     *
     * @param aList 新列表（内部不含对象）
     * @param bList 旧列表（内部不含对象）
     * @return 返回元素组成的列表
     */
    public static <E> String getListupdateOnly(List<E> aList, List<E> bList) {
        List<E> addList = new ArrayList<E>();
        List<E> reduceaList = new ArrayList<E>();
        if (!(aList.getClass()==bList.getClass())){
            try {
                throw  new Exception("对象类型不一致");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //对新增的数据进行归纳
        for (int i = 0; i < aList.size(); i++) {
            if (!myListContains(bList, aList.get(i))) {
                addList.add(aList.get(i));
            }
        }
        //对减少的数据进行归纳
        for (int i = 0; i < bList.size(); i++) {
            if (!myListContains(aList, bList.get(i))) {
                reduceaList.add(bList.get(i));
            }
        }
        StringBuilder recordLog = new StringBuilder();
        if (addList.size() != 0) {
            recordLog.append("新增信息：" + addList).append(" ");
        }

        if (reduceaList.size() != 0) {
            recordLog.append("减少信息：" + reduceaList).append(" ");
        }
        return recordLog.toString();
    }


    /**
     * @param oldBean 原始数据
     * @param newBean 新数据
     * @return 根据传入的对象返回变化值
     */
    public static String contrastSourceByBean(Object oldBean, Object newBean) {
        String str = "";

        Object pojo1 = (Object) oldBean;
        Object pojo2 = (Object) newBean;
        if (!(pojo1.getClass()==pojo2.getClass())){
            return "对象类型不一致";
        }
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();

            Class<CheckField> chkField = CheckField.class;
            int i = 1;
            for (Field field : fields) {
                CheckField cf = field.getAnnotation(chkField);
                if (field.isAnnotationPresent(chkField)) {
                    //返回被标注的对象

                    //获取字段是否填写（不校验，空，非空）
                    if (cf.check().equals(Check.unchecks)&&cf.type().equals(Check.basics)) {
                        continue;
                    }
                    //同意对private字段取值
                    field.setAccessible(true);
                    Object fValue = null;
                    try {
                        fValue = field.get(pojo1);
                    } catch (Exception e) {
                        return "获取字段值异常！";
                    }
                }

                //获取对象具体信息
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                //对象间比较
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = getMethod.invoke(pojo2);
                if (o2 == null) {
                    continue;
                } else if (o1 == null) {
                    if (filterCheck(cf)) {
                        continue;
                    }
                    String getName = cf.name();
                    str += getName + ":新增值:" + o2;
                    i++;
                } else {
                    if (filterCheck(cf)){
                        String otto = contrastSourceFundByBean(o1, o2);
                        str+="{"+otto+"}";
                    }
                    if (!o1.equals(o2)) {
                        if (filterCheck(cf)) {
                            continue;
                        }
                        if (i != 1) {
                            str += "|";
                        }
                        String getName = cf.name();
                        str += getName + ":旧值:" + o1 + ",新值:" + o2;
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * @param oldBean 原始数据
     * @param newBean 新数据
     * @return 根据传入的对象返回变化值
     */
    public static String contrastSourceFundByBean(Object oldBean, Object newBean) {
        StringBuilder str = new StringBuilder();

        Object pojo1 = (Object) oldBean;
        Object pojo2 = (Object) newBean;
        if (!(pojo1.getClass()==pojo2.getClass())){
            return "对象类型不一致";
        }
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();

            Class<CheckField> chkField = CheckField.class;
            int i = 1;
            for (Field field : fields) {
                CheckField cf = field.getAnnotation(chkField);
                if (field.isAnnotationPresent(chkField)) {
                    //返回被标注的对象
                    //
                    //获取字段是否填写（不校验，空，非空）
                    if (cf.check().equals(Check.unchecks)&&cf.type().equals(Check.basics)) {
                        continue;
                    }
                    //同意对private字段取值
                    field.setAccessible(true);
                    Object fValue = null;
                    try {
                        fValue = field.get(pojo1);
                    } catch (Exception e) {
                        return "获取字段值异常！";
                    }
                    //获取最大值
                    int max = cf.maxlen();
                    //获取配置最小值
                    int min = cf.minlen();
                    int flength = 0;
                    //为空时判断
                    if (fValue != null) {
                        try {
                            String strVal = fValue.toString();
                            flength = strVal.length();
                        } catch (Exception e) {

                        }
                    }
                    if (max < flength) {
                        return  cf.name() + "长度不能大于" + max + "！";
                    }
                    if (min > flength) {
                        return  cf.name() + "长度不能小于" + min + "！";
                    }
                }
                //获取对象具体信息
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                    //对象间比较
                    Object o1 = getMethod.invoke(pojo1);
                    Object o2 = getMethod.invoke(pojo2);
                    if (o2 == null) {
                        continue;
                    } else if (o1 == null) {
                        if (filterCheck(cf)) {
                            continue;
                        }
                        String getName = cf.name();
                        str.append(getName).append(":新增值:").append(o2);
                        i++;
                    } else {
                        if (filterCheck(cf)){
                            String otto = contrastSourceFundByBean(o1, o2);
                            String getName = cf.name();
                            str.append("|").append(getName).append("{").append("[").append(otto).append("]").append("}");
                        }else if(filterChecksets(cf)){
                            String list =getListupdateOnly((List)o1,(List)o2);
                            String getName = cf.name();
                            str.append("|").append(getName).append("{").append("[").append(list).append("]").append("}");
                        }else if(filterCheckarray(cf)){
                            String array =judgeArray(o1,o2);
                            String getName = cf.name();
                            str.append("|").append(getName).append("{").append("[").append(array).append("]").append("}");

                        }else{
                            if (!o1.equals(o2)) {
                                if (filterCheck(cf)) {
                                    continue;
                                }
                                if (i != 1) {
                                    str.append("|");
                                }
                                String getName = cf.name();
                                str.append(getName).append(":旧值:").append(o1).append(",新值:").append(o2);
                                i++;
                            }
                        }


                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }



    /**
     * 对传入的状态进行判断
     * @param cf
     * @return
     */
    private static boolean filterChecksets(CheckField cf){
        return cf.type().equals(Check.sets);
    }

    private static boolean filterCheck(CheckField cf){
        return cf.type().equals(Check.beans);
    }

    private static boolean filterCheckarray(CheckField cf){
        return cf.type().equals(Check.arrays);
    }
}
