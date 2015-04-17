package org.haibin369.ejb.bean;

import org.haibin369.ejb.interceptor.MyInterceptor;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Stateless(mappedName = "cmtTransactionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(MyInterceptor.class)
public class CMTTransactionBeanImpl implements TransactionBean {
    @Resource
    private SessionContext sessionContext;
    @Resource(mappedName = "java:/MySQLDS")
    DataSource dataSource;
//    DataSource dataSource = (DataSource) ctx.lookup("java:/MySQLDS");

    @Override
    public void insertData(String data) throws Exception {
//        Context ctx = new InitialContext();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO EJB VALUES(?)");
            statement.setString(1, data);
            statement.execute();

            PreparedStatement errorStatement = connection.prepareStatement("INSERT INTO EJB VALUES('1', '2')");
            errorStatement.execute();
            sessionContext.setRollbackOnly();
        }finally{
            if(statement != null){
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
