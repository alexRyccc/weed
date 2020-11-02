package com.weed.loginfo.test;

import com.weed.loginfo.test.User;
import com.weed.loginfo.test.testBean;
import com.weed.loginfo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.weed.loginfo.util.Utils.judgeArray;

public class Main {
    /**
     * 测试类
     * @param args
     */
    public static void main(String[] args) {

        String s1 = judgeArray(new int[]{1,2,3}, new String[]{"1","2","3"});
         String[] st =new String []{"1","2","3","4"};
        String[] st2 =new String[]{"2","3","9","4","5"};
        List<String> ag =new ArrayList<>();
        ag.add("啊实打实");
        ag.add("asd");
        ag.add("梵蒂冈");
        List<String> bh =new ArrayList<>();
        bh.add("啊实打实");
        bh.add("马萨卡");
        bh.add("梵蒂冈");

        User u1 =new User("陈龙",23);
        User u2 =new User("房氏龙",24);
        testBean t1 =new testBean("30000","10000","20000","0","0","30000",st,ag,u1);


        testBean t2 =new testBean("40000","15000","15000","10000","5000","40000",st2, bh,u2);

        String s = Utils.contrastSourceFundByBean(t1, t2);
        System.out.println(s);


    }
}
