import com.weed.loginfo.util.ExcelReadUtil;
import com.weed.loginfo.util.JsonUtil;
import com.weed.loginfo.util.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.weed.loginfo.util.Utils.judgeArray;

public class Main {
    /**
     * 测试类
     *
     * @param args
     */
    public static void main(String[] args) {

//        List<List<String>> paths =new ArrayList<List<String>>();
//        List<String> l1 =new ArrayList<>();
//        l1.add("London");
//        l1.add("New York") ;
//        List<String> l2 =new ArrayList<>();
//        l2.add("New York");
//        l2.add("Lima") ;
//        List<String> l3 =new ArrayList<>();
//        l3.add("Lima");
//        l3.add("Sao Paulo") ;
//        paths.add(l1);
//        paths.add(l2);
//        paths.add(l3);
//        destCity(paths);

    }

    public void excelRead(){
        List<ExcelReq> list = ExcelReadUtil.readExcelFile("File地址", ExcelReq.class);
    }

    @Test
    public void beanTest() {
        String s1 = judgeArray(new int[]{1, 2, 3}, new String[]{"1", "2", "3"});
        String[] st = new String[]{"1", "2", "3", "4"};
        String[] st2 = new String[]{"2", "3", "9", "4", "5"};
        List<String> ag = new ArrayList<String>();
        ag.add("渤海");
        ag.add("黄海");
        ag.add("梵蒂冈");
        List<String> bh = new ArrayList<String>();
        bh.add("黄海");
        bh.add("长江");
        bh.add("梵蒂冈");

        User u1 = new User("陈龙", 23);
        User u2 = new User("陈道成", 24);
        testBean t1 = new testBean("30000", "10000", "20000", "0", "0", "30000", st, ag, u1);


        testBean t2 = new testBean("40000", "15000", "15000", "10000", "5000", "40000", st2, bh, u2);

        String s = JsonUtil.contrastSourceFundByBean(t1, t2,0);
        System.out.println(s);
    }


    public static String destCity(List<List<String>> paths) {
        List<String> list1 = new ArrayList<String>();
        for (List<String> list : paths) {
            list1.add(list.get(0));
        }
        List<String> list2 = new ArrayList<String>();
        for (List<String> list : paths) {
            list2.add(list.get(1));
        }
        for (int i = 0; i < list2.size(); i++) {
            if (!myArrayContains(list1, list2.get(i))) {
                return list2.get(i);

            }
        }
        return null;

    }

    private static <E> boolean myArrayContains(List<String> sourceArray, E element) {
        if (sourceArray == null || element == null) {
            return false;
        }
        if (sourceArray.size() == 0) {
            return false;
        }
        for (Object tip : sourceArray) {
            if (element.equals(tip)) {
                return true;
            }
        }
        return false;
    }
}
