package com.myblog;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        new Test().test().test1();
    }
    public Test test(){
        return new Test();
    }
    public void test1(){
        System.out.println(101);
    }
}
