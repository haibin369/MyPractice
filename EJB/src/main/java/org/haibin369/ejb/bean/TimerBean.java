package org.haibin369.ejb.bean;

import javax.ejb.Remote;

@Remote
public interface TimerBean {
    public void createSchedule();

}
