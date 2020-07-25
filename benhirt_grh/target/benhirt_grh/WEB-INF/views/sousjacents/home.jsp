<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Blog</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <jsp:directive.include file="../layout/header.jsp" />
 <header class="col-lg-12">
    <h1>Tous les sousJacents du Blog</h1>

        <div class="col-lg-12">
            <a href="${pageContext.request.contextPath}/sousJacents/add" class="btn btn-primary">Ajouter Sous-Jacent</a>
            <br/>
        </div>
        <table class="table table-bordered">
            <tr>
                <th>Id</th>
                <th>Sous-Jacent</th>
                <th>Email</th>
                <th>Type</th>
                <th>Salaire</th>

            </tr>
            <c:forEach items="${pageable.content}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nom}</td>
                    <td>${item.email}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/sousJacents/delete/${pageable.number}/${item.id}" class="btn btn-danger"
                           onclick="if (!(confirm('Vous Voulez supprimer cet element?'))) return false">Supprimer</a>
                        <a href="${pageContext.request.contextPath}/sousJacents/add/${item.id}" class="btn btn-success">Modifier</a>
                    </td>
                </tr>

            </c:forEach>
        </table>

     <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:choose>
                <c:when test="${pageable.number !=0 }">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/sousJacents/page/${pageable.number-1 }">Previous</a></li>
                </c:when>
            </c:choose>
            <c:forEach begin="0"   end="${pageable.totalPages}" var="i">
                <c:choose>
                    <c:when test="${pageable.number ==i }">
                        <li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}/sousJacents/page/${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/sousJacents/page/${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${pageable.number <pageable.totalPages-1 }">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/sousJacents/page/${pageable.number+1 }">Next</a></li>
                </c:when>
            </c:choose>
        </ul>
     </nav>



 </header>

</div>

</body>
</html>