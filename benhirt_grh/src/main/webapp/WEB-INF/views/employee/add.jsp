
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
        <h1>Ajouter un employee</h1>
        <form:form method="post" action="${pageContext.request.contextPath}/employee/save" modelAttribute="employee" >
            <form:input path="id" type="hidden" />
            <div class="form-group">
                <label class="form-check-label">
                    Departement
                </label>

                <form:select path="departement"  class="form-control" id="select">
                    <c:forEach items="${departements}"   var="u">
                        <option value="${u.id}"> ${u.dname}  </option>
                    </c:forEach>
                </form:select>
                <form:errors path="departement" cssClass="alert-danger" />
            </div>



            <div class="form-group">
                <label for="nom">Nom</label>
                <form:input path="nom" cssClass="form-control"  placeholder="nom" />
                <form:errors path="nom" cssClass="alert-danger" />
            </div>
            <div class="form-group">
                <label for="prenom" class="col-md-3 control-label">Prenom</label>
                <div class="col-md-9">
                    <form:input path="prenom" cssClass="form-control" />
                </div>
                <div class="col-md-3">
                    <form:errors path="prenom" cssClass="alert alert-warning" />
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-md-3 control-label">Email</label>
                <div class="col-md-9">
                    <form:input path="email" cssClass="form-control" />
                </div>
                <div class="col-md-3">
                    <form:errors path="email" cssClass="alert alert-warning" />
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-md-3 control-label">Password</label>
                <div class="col-md-9">
                    <form:input path="password" cssClass="form-control" />
                </div>
                <div class="col-md-3">
                    <form:errors path="password" cssClass="alert alert-warning" />
                </div>
            </div>

            <div class="form-group">
                <label for="type" class="col-md-3 control-label">Type</label>
                <div class="col-md-9">
                    <form:input path="type" cssClass="form-control" />
                </div>
                <div class="col-md-3">
                    <form:errors path="type" cssClass="alert alert-warning" />
                </div>
            </div>

            <div class="form-group">
                <label for="salaire" class="col-md-3 control-label">Salaire</label>
                <div class="col-md-9">
                    <form:input path="salaire" cssClass="form-control" />
                </div>
                <div class="col-md-3">
                    <form:errors path="salaire" cssClass="alert alert-warning" />
                </div>
            </div>

            <div class="form-group">
                <label for="chiffre" class="col-md-3 control-label">
                    Chiffre d'affaires</label>
                <div class="col-md-9">
                    <form:input path="chiffre" cssClass="form-control" />
                </div>
                <div class="col-md-3">
                    <form:errors path="chiffre" cssClass="alert alert-warning" />
                </div>
            </div>


            <div class="form-group">
                <!-- Button -->
                <div class="col-md-offset-3 col-md-9">
                    <form:button cssClass="btn btn-primary">Submit</form:button>
                </div>
            </div>
        </form:form>
    </header>
</div>
</body>
</html>
