<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <%--  Nhúng CSS  --%>
    <link rel="stylesheet" href="css/login.css">

    <%--  BOOTSTRAP --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body>
<%--    <!-- Gọi Sidebar -->--%>
<%--    <jsp:include page="common/sidebar.jsp" />--%>
    <!-- Gọi Toast -->
    <%@ include file="common/toast.jsp" %>

    <!-- Main Content -->
    <div class="main-content" style="margin-top: 6rem">
        <div class="panda">
            <div class="ear"></div>
            <div class="face">
                <div class="eye-shade"></div>
                <div class="eye-white">
                    <div class="eye-ball"></div>
                </div>
                <div class="eye-shade rgt"></div>
                <div class="eye-white rgt">
                    <div class="eye-ball"></div>
                </div>
                <div class="nose"></div>
                <div class="mouth"></div>
            </div>
            <div class="body"> </div>
            <div class="foot">
                <div class="finger"></div>
            </div>
            <div class="foot rgt">
                <div class="finger"></div>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/login" method="post" class="panda-form bg-white p-4 shadow rounded">
            <div class="hand"></div>
            <div class="hand rgt"></div>
            <h1 class="panda-h1">Login</h1>
            <div class="form-group">
                <label for="username" class="form-label">Tài khoản:</label>
                <input type="text" id="username" name="username" class="form-control" required="required" placeholder="Nhập tài khoản">
            </div>
            <div class="form-group">
                <label for="password" class="form-label">Mật khẩu:</label>
                <input type="password" id="password" name="password" class="form-control" required="required" placeholder="Nhập mật khẩu">
            </div>
            <button type="submit" class="panda-btn">Đăng nhập</button>
            <p>Don't have an account?</p>
            <a href="${pageContext.request.contextPath}/register.jsp">Đăng ký tại đây</a>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        // Hiệu ứng khi nhấn vào input password
        $('#password').focusin(function() {
            $('form').addClass('up');
        });

        $('#password').focusout(function() {
            $('form').removeClass('up');
        });

        // Hiệu ứng mắt Panda
        $(document).on("mousemove", function(event) {
            var dw = $(document).width() / 15;
            var dh = $(document).height() / 15;
            var x = (event.pageX / dw);
            var y = (event.pageY / dh);
            $('.eye-ball').css({
                width: x + 'px',
                height: y + 'px'
            });
        });
    </script>

</body>

</html>