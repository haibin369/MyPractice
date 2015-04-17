package org.haibin369.ejb.bean;

import javax.ejb.Remote;

@Remote
public interface TransactionBean {
    public void insertData(String data) throws Exception;
}
