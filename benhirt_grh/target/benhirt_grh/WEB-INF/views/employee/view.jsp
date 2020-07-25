<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Blog</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">

    <jsp:directive.include file="../layout/header.jsp" />

    <div class="col-lg-12">
        <h1>${employee.nom}</h1>
        <h1>${employee.prenom}</h1>
        <h1>${employee.email}</h1>
        <h1>${employee.departement}</h1>
        <h1>${employee.salaire}</h1>
        <p><small>Created: <fmt:formatDate type = "date" value = "${employee.created}" /> </small></p>
        <p> User : <b>${employee.departement.name}</b>
    </div>
</div>

</body>
</html>