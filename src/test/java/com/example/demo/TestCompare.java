package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.mapping.Array;

public class TestCompare {

	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		list.add("6");
//		list.add("1");
//		list.add("4");
//		list.add("3");
//
//		Collections.sort(list, (String s1, String s2) -> {
//			int ii = s1.compareTo(s2);
//			System.out.println("ii=" + ii);
//			return s1.compareTo(s2);
//		});
//		list.forEach(s -> System.out.println(s));

		String[] strs = { "4", "2", "6", "9" };
		Arrays.sort(strs, (String s1, String s2) -> (s1.compareTo(s2)));
		for (String s : strs) {
			System.out.println(s);
		}
	}

}
