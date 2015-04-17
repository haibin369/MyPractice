<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First DS</title>
</head>
<body>
    <div>
        <%
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/MySQLDS");
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM JTA");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                out.println(resultSet.getString(1));
            }
            resultSet.close();
            statement.close();
            connection.close();
        %>
    </div>
</body>
</html>
