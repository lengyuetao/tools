package com.tao.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringUtils {
    public static void main(String[] args) {
        DecimalFormat b=new DecimalFormat("00000");
        System.out.println(b.format(31));
    }
}
