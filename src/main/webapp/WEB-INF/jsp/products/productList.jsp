<%--
  Created by IntelliJ IDEA.
  User: lichking
  Date: 2017. 8. 21.
  Time: AM 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />

    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>상품명</th>
                <th>가격</th>
                <th>재고수량</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.stockQuantity}</td>
                    <td>
                        <a href="/products/${product.id}/edit" class="btn btn-primary" role="button">수정</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->

</body>
</html>