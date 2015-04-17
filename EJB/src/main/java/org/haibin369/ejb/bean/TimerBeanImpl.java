package org.haibin369.ejb.bean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.util.Date;

@Stateless(mappedName = "TimerEJB")
public class TimerBeanImpl implements TimerBean {

    @Resource
    TimerService timerService;

    @Override
    public void createSchedule() {
        timerService.createTimer(new Date(), 5000, "My Schedule");
    }

    @Timeout
    public void schedleJob(Timer timer) {
        timer.cancel();
        System.out.println("From Schedule Job!");
    }
}
