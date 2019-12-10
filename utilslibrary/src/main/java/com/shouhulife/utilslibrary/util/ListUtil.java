package com.shouhulife.utilslibrary.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author : shouhulife
 * time: 2019/12/10 16
 * email: xhdshz@foxmail.com
 */
public class ListUtil {

    /**
     * list 去重
     * @param list
     */
    public static void removeDuplicate(List<String> list) {
        HashSet<String> set = new HashSet<String>(list.size());
        List<String> result = new ArrayList<String>(list.size());
        for (String str : list) {
            if (set.add(str)) {
                result.add(str);
            }
        }
        list.clear();
        list.addAll(result);
    }
}
