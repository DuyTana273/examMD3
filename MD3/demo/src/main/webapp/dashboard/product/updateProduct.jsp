<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
<head>
  <title>Update Product</title>
</head>
<body>
<!-- Gọi toast -->
<jsp:include page="../../common/toast.jsp" />

<form action="${pageContext.request.contextPath}/product?action=updateProduct" method="post" style="margin-top: 4rem">
  <input type="hidden" name="product_id" value="${product.product_id}">
  <div class="mb-3">
    <label for="productName" class="form-label">Tên Sản phẩm</label>
    <input type="text" id="productName" name="productName" class="form-control" value="${productName != null ? productName : product.productName}">
  </div>

  <div class="mb-3">
    <label for="productDescription" class="form-label">Mô Tả</label>
    <input type="text" id="productDescription" name="productDescription" class="form-control" value="${productDescription != null ? productDescription : product.productDescription}">
  </div>

  <div class="mb-3">
    <label for="productPrice" class="form-label">Giá</label>
    <input type="number" id="productPrice" name="productPrice" class="form-control" value="${productPrice != null ? productPrice : product.productPrice}">
  </div>

  <div class="mb-3">
    <label for="productStock" class="form-label">Số Lượng</label>
    <input type="number" id="productStock" name="productStock" class="form-control" value="${productStock != null ? productStock : product.productStock}">
  </div>

  <div class="mb-3">
    <label for="productColor" class="form-label">Màu</label>
    <input type="text" id="productColor" name="productColor" class="form-control" value="${productColor != null ? productColor : product.productColor}">
  </div>

  <div class="mb-3">
    <label for="categoryID" class="form-label">Lựa chọn thương hiệu:</label>
    <select class="form-select" id="categoryID" name="categoryID" required>
      <option value="" disabled>-- Chọn thương hiệu --</option>
      <c:forEach var="category" items="${categories}">
        <option value="${category.categoryID}" ${category.categoryID == product.categoryID ? 'selected' : ''}>
            ${category.categoryName}
        </option>
      </c:forEach>
    </select>
  </div>

  <div class="d-flex justify-content-between">
    <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
    <a href="${pageContext.request.contextPath}/product?action=listProduct" class="btn btn-secondary">
      Quay lại
    </a>
  </div>
</form>

</body>
</html>
