package org.example;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * <b>类名称：<b/>TestShiroMD5 <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月29日 11:01 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class TestShiroMD5 {
    public static void main(String[] args) {

        //创建一个MD5算法
//        Md5Hash md5Hash = new Md5Hash();
//        md5Hash.setBytes("123".getBytes());
//        String s = md5Hash.toHex();
//        System.out.println(s);

        //使用MD5
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println(md5Hash.toHex());

        //使用MD5 + salt处理
        Md5Hash md5Hash1 = new Md5Hash("123", UUID.randomUUID().toString().replace("-", ""));
        System.out.println(md5Hash1);

        //使用MD5 + salt处理 + hash散列
        Md5Hash md5Hash2 = new Md5Hash("123", UUID.randomUUID().toString().replace("-", ""),1024);
        System.out.println(md5Hash2);
    }
}
