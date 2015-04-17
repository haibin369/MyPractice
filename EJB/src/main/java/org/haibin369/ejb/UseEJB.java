package org.haibin369.ejb;

import org.haibin369.ejb.bean.FirstBean;
import org.haibin369.ejb.bean.TimerBean;
import org.haibin369.ejb.bean.TransactionBean;
import org.haibin369.ejb.util.Utils;

import javax.naming.Context;
import javax.naming.NamingException;

public class UseEJB {
    public static void main(String[] args) throws Exception {
        useTimerEjb();
    }

    public static void useFirstBean() throws NamingException {
        Context context = Utils.getContext();
        FirstBean firstBean = (FirstBean) context.lookup("FirstEJB");
        System.out.println(firstBean.hello("haibin369"));
    }

    public static void useCMTTransactionBean(String data) throws Exception {
        Context context = Utils.getContext();
        TransactionBean transactionBean = (TransactionBean) context.lookup("cmtTransactionBean");
        transactionBean.insertData(data);
    }

    public static void useBMTTransactionBean(String data) throws Exception {
        Context context = Utils.getContext();
        TransactionBean transactionBean = (TransactionBean) context.lookup("bmtTransactionBean");
        transactionBean.insertData(data);
    }

    public static void useTimerEjb() throws NamingException {
        Context context = Utils.getContext();
        TimerBean timerBean = (TimerBean) context.lookup("TimerEJB");
        timerBean.createSchedule();
    }
}
