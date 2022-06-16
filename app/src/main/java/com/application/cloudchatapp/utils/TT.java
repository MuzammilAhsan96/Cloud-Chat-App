package com.application.cloudchatapp.utils;

import java.util.ArrayList;
import java.util.List;

public class TT {

    public static void main(String[] ss) {

        List<SSS> d = new ArrayList<>();

        SSS s = new SSS();
        s.id = "1";
        s.name = "qweqweqweqwe";
        s.type = 1;
        d.add(s);

        SSS s2 = new SSS();
        s2.id = "2";
        s2.name = "eeeweqasdasd";
        s2.type = 2;
        d.add(s2);

        SSS s3 = new SSS();
        s3.id = "3";
        s3.name = "2211231231231";
        s3.type = 3;
        d.add(s3);

        SSS s4 = new SSS();
        s4.id = "4";
        s4.name = "3453vkmoke;lwl;mflwf";
        s4.type = 4;
        d.add(s4);

        String name1 = "";
        String id1 = "";

        for (SSS d1 : d) {
            int t = d1.type;
            if (t == 3) {
                name1 = d1.name;
                id1 = d1.id;
                break;
            }
        }

        List<SSS> ds = new ArrayList<>();

        for (SSS d1 : d) {
            if (d1.type != 3) {
                if (d1.type == 2) {
                    d1.name1 = name1;
                    d1.id1 = id1;
                }
                ds.add(d1);
            }
        }
        System.out.println(" kjb " + ds.size());
        System.out.println(" kjb " + ds);
    }

}
