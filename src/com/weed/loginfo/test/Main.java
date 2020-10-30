package com.weed.loginfo.test;

import com.weed.loginfo.test.User;
import com.weed.loginfo.test.testBean;
import com.weed.loginfo.util.Utils;

public class Main {
    /**
     * 测试类
     * @param args
     */
    public static void main(String[] args) {
        User u1 =new User("陈龙",23);
        User u2 =new User("房氏龙",24);
        testBean t1 =new testBean("30000","10000","20000","0","0","30000", u1);
        testBean t2 =new testBean("40000","15000","15000","10000","5000","40000", u2);

        String s = Utils.contrastSourceFundByBean(t1, t2);
        System.out.println(s);
    }
}
