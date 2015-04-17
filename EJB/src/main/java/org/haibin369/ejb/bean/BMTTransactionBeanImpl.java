package org.haibin369.ejb.bean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Stateless(mappedName = "bmtTransactionBean")
@TransactionManagement(TransactionManagementType.BEAN)
public class BMTTransactionBeanImpl implements TransactionBean {
    @Resource
    UserTransaction userTx;

    @Override
    public void insertData(String data) throws Exception {
        Context ctx = new InitialContext();
        userTx.begin();
        try {
            DataSource dataSource = (DataSource) ctx.lookup("java:/MySQLDS");
            Connection connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO EJB VALUES(?)");
            statement.setString(1, data);
            statement.execute();

            PreparedStatement errorStatement = connection.prepareStatement("INSERT INTO EJB VALUES('1', '2')");
            errorStatement.execute();
            userTx.commit();
        } catch (Exception e) {
            userTx.rollback();
            throw e;
        }
    }
}
