package org.haibin369.ejb.bean;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
//@Local
public interface FirstBean {
    public String hello(String name);
}
