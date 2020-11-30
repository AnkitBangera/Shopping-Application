package com.Shopping.demo.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommonUtil {
	public static <T> List<T> convertFromIteratorToList(Iterator<T> itr) {
		List<T> list = new ArrayList<T>();
		if (list != null) {
			while (itr.hasNext()) {
				list.add(itr.next());
			}
		}
		return list;
	}
}
