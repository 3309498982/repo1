package com.example.rbac.service.impl;

import com.example.rbac.dao.MSysOperatorDao;
import com.example.rbac.entity.MSysOperator;
import com.example.rbac.service.OperatorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <b>类名称：</b>OperatorServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>刘亚州<br/>
 * <b>修改人：</b>刘亚州<br/>
 * <b>修改时间：</b>2020/7/14 9:54<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0<br />
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Resource
    private MSysOperatorDao operatorDao;

    @Override
    public MSysOperator findByOperator(String username) {
        return operatorDao.findByOperator(username);
    }
}
