package com.shouhulife.utilslibrary.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


/**
 * 保存各种数据到本地持久化
 * 作者：hong on 2017/2/20 15:40
 * 邮箱：xhdshz@foxmail.com
 */
public class SharedPreferencesUtil {
    private SharedPreferences sp;

    public SharedPreferencesUtil(Context context){
        if(context != null){
            sp = context.getSharedPreferences("mysharedpreferences", Context.MODE_PRIVATE);
        }
    }
    public SharedPreferences getSP(){
        if(sp!=null){
            return sp;
        }
        return null;
    }

    //=======================字符串类型
    public void saveData(String key, String value){
        if(sp!=null){
            sp.edit().putString(key, value).commit();
        }
    }

    public String getData(String key){
        String value="";
        if(sp!=null){
            value = sp.getString(key, "");
        }
        return value;
    }

    //======================boolean类型
    public void saveData(String key, boolean value){
        if(sp!=null){
            sp.edit().putBoolean(key,value).commit();
        }
    }

    public boolean getDataboole(String key){
        if(sp!=null){
            return sp.getBoolean(key,false);
        }
        return false;
    }
    //-=================保存Long类型
    public void saveData(String key, Long value){
        if(sp!=null){
            sp.edit().putLong(key, value).commit();
        }
    }

    public long getDatalong(String key){
        if(sp!=null){
            return sp.getLong(key,0);
        }
        return 0;
    }
}
