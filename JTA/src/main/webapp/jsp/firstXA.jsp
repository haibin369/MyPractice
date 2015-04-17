<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.transaction.UserTransaction" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    Context ctx = new InitialContext();
    DataSource oracleDS = (DataSource) ctx.lookup("java:/OracleXADS");
    Connection oracleConn = oracleDS.getConnection();
    Statement oracleSmt = null;

    DataSource oracleDS2 = (DataSource) ctx.lookup("java:/OracleXADS2");
    Connection oracleConn2 = oracleDS2.getConnection();
    Statement oracleSmt2 = null;

//    UserTransaction userTx = (UserTransaction) ctx.lookup("java:jboss/UserTransaction");
    UserTransaction userTx = (UserTransaction) ctx.lookup("UserTransaction");

    userTx.begin();
    try {
        oracleSmt = oracleConn.createStatement();
        oracleSmt.executeUpdate("INSERT INTO TEST VALUES('SDF')");

        oracleSmt2 = oracleConn2.createStatement();
        oracleSmt2.executeUpdate("INSERT INTO JTA VALUES('SFSDF', 'SDFFF')");
        userTx.commit();
    } catch (Exception e) {
        e.printStackTrace();
        userTx.rollback();
    } finally {
        oracleSmt.close();
        oracleConn.close();
        oracleDS.getClass();
        oracleSmt2.close();
        oracleConn2.close();
        oracleDS2.getClass();
    }


%>

</body>
</html>
