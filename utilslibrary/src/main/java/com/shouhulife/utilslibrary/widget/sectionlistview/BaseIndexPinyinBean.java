package com.shouhulife.utilslibrary.widget.sectionlistview;

/**
 * 介绍：索引类的汉语拼音的接口
 * 作者：zhangxutong
 * 时间： 16/09/04.
 */

public abstract class BaseIndexPinyinBean extends BaseIndexTagBean implements IIndexTargetInterface {
    private String baseIndexPinyin;//城市的拼音

    public String getBaseIndexPinyin() {
        return baseIndexPinyin;
    }

    public void setBaseIndexPinyin(String baseIndexPinyin) {
        this.baseIndexPinyin = baseIndexPinyin;
    }
}
