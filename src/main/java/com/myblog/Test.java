package com.myblog;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Integer> res = Arrays.asList(12, 20, 30, 40,43,57,23,43,21,24,76);
        List<Integer> val = res.stream().sorted().collect(Collectors.toList());
        System.out.println(val);
    }
}
