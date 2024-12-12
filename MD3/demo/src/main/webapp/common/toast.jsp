<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <link rel="stylesheet" href="../css/toast.css">
</head>
<body>
<div class="toast-container">
  <c:choose>
    <c:when test="${sessionScope != null && not empty sessionScope.successMessage}">
      <div class="toast success">
        <i class="fa-solid fa-circle-check toast-icon" style="color: green"></i>
        <div class="toast-content">
          <div class="toast-title success"><strong>Thành Công</strong></div>
          <c:out value="${sessionScope.successMessage}" />
        </div>
        <button class="close-btn" onclick="this.parentElement.style.display='none'">&times;</button>
      </div>
      <c:remove var="successMessage" scope="session" />
    </c:when>
    <c:when test="${sessionScope != null && not empty sessionScope.errorMessage}">
      <div class="toast error">
        <i class="fa-solid fa-circle-xmark toast-icon" style="color: red"></i>
        <div class="toast-content">
          <div class="toast-title error"><strong>Thất Bại</strong></div>
          <c:out value="${sessionScope.errorMessage}" />
        </div>
        <button class="close-btn" onclick="this.parentElement.style.display='none'">&times;</button>
      </div>
      <c:remove var="errorMessage" scope="session" />
    </c:when>
    <c:when test="${sessionScope != null && not empty sessionScope.warningMessage}">
      <div class="toast warning">
        <i class="fa-solid fa-exclamation toast-icon" style="color: #838300"></i>
        <div class="toast-content">
          <div class="toast-title warning"><strong>Cảnh Báo</strong></div>
          <c:out value="${sessionScope.warningMessage}" />
        </div>
        <button class="close-btn" onclick="this.parentElement.style.display='none'">&times;</button>
      </div>
      <c:remove var="warningMessage" scope="session" />
    </c:when>
  </c:choose>
</div>

<script>
  document.addEventListener('DOMContentLoaded', function () {
    const toastElList = document.querySelectorAll('.toast');
    toastElList.forEach(toastEl => {
      toastEl.classList.add('show');  // Áp dụng hiệu ứng slide-in (fade-in)

      setTimeout(() => {
        toastEl.classList.add('fade-out');  // Thực hiện fade-out sau 3 giây
      }, 3000);

      setTimeout(() => {
        toastEl.style.display = 'none';  // Ẩn toast khi hiệu ứng fade-out hoàn tất
      }, 6000);  // Sau 6 giây (tổng thời gian hiển thị)

      // Khi người dùng click nút đóng
      toastEl.querySelector('.close-btn').addEventListener('click', () => {
        toastEl.style.display = 'none';
      });
    });
  });
</script>
</body>
