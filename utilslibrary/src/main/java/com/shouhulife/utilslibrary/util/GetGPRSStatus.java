package com.shouhulife.utilslibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;

import java.lang.reflect.Method;

public class GetGPRSStatus {
    /**
     * 返回手机移动数据的状态**
     @param pContext*
     @param arg      默认填null*
     @return true 连接 false 未连接
     **/

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean getMobileDataState(Context pContext, Object[] arg) {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            Class ownerClass = mConnectivityManager.getClass();
            Class[] argsClass = null;
            if (arg != null) {
                argsClass = new Class[1];
                argsClass[0] = arg.getClass();
            }
            Method method = ownerClass.getMethod("getMobileDataEnabled", argsClass);
            Boolean isOpen = (Boolean) method.invoke(mConnectivityManager, arg);
            return isOpen;
        } catch (Exception e) {
            return false;
        }
    }
}
