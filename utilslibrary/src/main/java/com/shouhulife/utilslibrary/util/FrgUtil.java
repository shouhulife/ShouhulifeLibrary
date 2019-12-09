package com.shouhulife.utilslibrary.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 将fragment添加到activity
 */
public class FrgUtil {
    public static void addFragmentToActivity(FragmentManager manager, Fragment fragment, int frameId) {

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(frameId, fragment);

        transaction.commit();

    }
}
