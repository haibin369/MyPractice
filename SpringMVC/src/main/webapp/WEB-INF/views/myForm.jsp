<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Spring MVC Form</title>
</head>
<body>
<h3>Spring MVC Form</h3>
<sp:form method="post" modelAttribute="myForm" action="FormController" enctype="multipart/form-data">
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="myForm_nickname">Nick name:</label></th>
                <td><input name="nickname" size="15" id="myForm_nickname"/></td>
            </tr>
            <tr>
                <th><label for="myForm_username">User name:</label></th>
                <td>
                    <sp:input path="username" size="15" id="myForm_username"/>
                    <sp:errors path="username"/>
                </td>
            </tr>
            <tr>
                <th><label for="myForm_password">Password:</label></th>
                <td>
                    <sp:password path="password" size="30" showPassword="true" id="myForm_password"/>
                    <sp:errors path="password"/>
                </td>
            </tr>
            <tr>
                <th><label for="myForm_logo">Logo:</label></th>
                <td>
                    <input id="myForm_logo" name="logo" type="file"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <button type="submit">Submit</button>
    <br/><br/>
    <h2>All Errors:</h2>
    <sp:errors path="*" element="div"/>
</sp:form>

<form method="post" action="<%=request.getContextPath()%>/FormController" enctype="multipart/form-data">
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="myForm_nickname">Nick name:</label></th>
                <td><input name="nickname" size="15" id="myForm_nickname"/></td>
            </tr>
            <tr>
                <th><label for="myForm_username">User name:</label></th>
                <td>
                    <input type="text" name="username" id="myForm_username"/>
                </td>
            </tr>
            <tr>
                <th><label for="myForm_password">Password:</label></th>
                <td>
                    <input type="password" name="password" id="myForm_password"/>
                </td>
            </tr>
            <tr>
                <th><label for="myForm_logo">Logo:</label></th>
                <td>
                    <input id="myForm_logo" name="logo" type="file"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <button type="submit">Submit</button>
    <br/><br/>
    <h2>All Errors:</h2>
</form>
</body>
</html>
