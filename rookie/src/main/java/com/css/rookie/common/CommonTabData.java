package com.css.rookie.common;

import java.io.Serializable;

/**
 * <b>类名称：<b/>CommonTabData <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年8月13日 17:26 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class CommonTabData implements Serializable {
    private static final long serialVersionUID = -7303570517548659631L;
    private int count;
    private Object list;
    private int pageNo;
    private int pageSize;


    public CommonTabData() {
    }

    public CommonTabData(int count, Object list, int pageNo, int pageSize) {
        this.count = count;
        this.list = list;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
