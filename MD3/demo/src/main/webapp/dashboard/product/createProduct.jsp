<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thêm Sản Phẩm</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- Gọi toast -->
<jsp:include page="../../common/toast.jsp" />

<div class="main-content" style="margin-top: 4rem">
  <form action="${pageContext.request.contextPath}/product?action=createProduct" method="post">
    <h2 class="mb-4">Thêm Sản Phẩm Mới</h2>
    <div class="mb-3">
      <label for="productName" class="form-label">Tên sản phẩm:</label>
      <input type="text" class="form-control" id="productName" name="productName" value="${param.productName}" required>
    </div>

    <div class="mb-3">
      <label for="productPrice" class="form-label">Thêm giá:</label>
      <input type="number" class="form-control" id="productPrice" name="productPrice" value="${param.productPrice}" required>
    </div>

    <div class="mb-3">
      <label for="productStock" class="form-label">Nhập số lượng:</label>
      <input type="number" class="form-control" id="productStock" name="productStock" value="${param.productStock}" required>
    </div>

    <div class="mb-3">
      <label for="categoryID" class="form-label">Lựa chọn thương hiệu:</label>
      <select class="form-select" id="categoryID" name="categoryID" required>
        <option value="" disabled selected>-- Chọn thương hiệu --</option>
        <c:forEach var="category" items="${categories}">
          <option value="${category.categoryID}">
              ${category.categoryName}
          </option>
        </c:forEach>
      </select>
    </div>

    <div class="d-flex justify-content-between">
      <button type="submit" class="btn btn-primary">Thêm Sản Phẩm</button>
      <a href="${pageContext.request.contextPath}/product?action=listProducts" class="btn btn-secondary">
        Quay lại
      </a>
    </div>
  </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
