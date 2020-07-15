package com.example.rbac.service;

import com.example.rbac.entity.MSysOperator;

/**
 * <b>类名称：</b>OperatorService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>刘亚州<br/>
 * <b>修改人：</b>刘亚州<br/>
 * <b>修改时间：</b>2020/7/14 9:52<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0<br />
 */
public interface OperatorService {

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    MSysOperator findByOperator(String username);
}
