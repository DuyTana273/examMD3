<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-table th {
            background-color: #f8f9fa;
            font-weight: bold;
        }
        .card-table td {
            font-size: 1.1rem;
        }
        .img-avatar {
            max-width: 100px;
        }
    </style>
</head>
<body>

<!-- Gọi toast -->
<jsp:include page="../../common/toast.jsp" />

<div id="main-content" style="margin-top: 4rem">
    <div class="container">
        <%-- Hiển thị chi tiết sản phẩm --%>
        <c:if test="${not empty product}">
            <div class="card">
                <div class="card-header">
                    <h4>Chi tiết sản phẩm: ${product.productName}</h4>
                </div>
                <div class="card-body">
                    <table class="table table-bordered table-striped card-table">
                        <tr>
                            <th>Tên sản phẩm</th>
                            <td>${product.productName}</td>
                        </tr>
                        <tr>
                            <th>Mô tả</th>
                            <td>${product.productDescription}</td>
                        </tr>
                        <tr>
                            <th>Giá tiền</th>
                            <td>${product.productPrice}</td>
                        </tr>
                        <tr>
                            <th>Số lượng</th>
                            <td>${product.productStock}</td>
                        </tr>
                        <tr>
                            <th>Màu sắc</th>
                            <td><${product.productColor}></td>
                        </tr>
                        <tr>
                            <th>Thương hiệu</th>
                            <td>${product.categoryName}</td>
                        </tr>
                    </table>
                </div>
                <div class="card-footer d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/product?action=listProducts" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i> Quay lại
                    </a>
                    <a href="${pageContext.request.contextPath}/product?action=updateProduct&productName=${product.productName}" class="btn btn-warning">
                        <i class="fas fa-edit"></i> Sửa sản phẩm
                    </a>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
