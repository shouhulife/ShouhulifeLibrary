package com.shouhulife.utilslibrary.util;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

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
