<%--
  Created by IntelliJ IDEA.
  User: binizmohamed
  Date: 4/6/20
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Ajouter employee</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>

<div class="container">
    <jsp:directive.include file="../layout/header.jsp" />
    <header class="col-lg-12">
        <h1>Ajouter un département</h1>
        <form:errors path="departement" cssClass="text-danger" />
        <form:form method="post" action="${pageContext.request.contextPath}/departement/save" modelAttribute="departement" >
            <form:input path="id" type="hidden" />
            <div class="form-group">
                <label for="dname">Nom</label>
                <form:input path="dname" cssClass="form-control"  placeholder="Nom" />
                <form:errors path="dname" cssClass="alert-danger" />
            </div>
            <div class="form-group">
                <label for="type">Type</label>
                <form:input path="type" cssClass="form-control"  placeholder="Type" />
                <form:errors path="type" cssClass="alert-danger" />
            </div>




            <input class="btn btn-primary" type = "submit" value = "Submit"/>
        </form:form>
    </header>
</div>
</body>
</html>
