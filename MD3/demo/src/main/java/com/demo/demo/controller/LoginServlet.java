package com.demo.demo.controller;


import com.demo.demo.model.Users;
import com.demo.demo.service.IUserService;
import com.demo.demo.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private IUserService iUserService;

    @Override
    public void init() throws ServletException {
        iUserService = new UserService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);

        try {
            Optional<Users> loginUser = iUserService.findUserByUsernameAndPassword(username, password);

            if (loginUser.isPresent()) {
                Users users = loginUser.get();
                session.setAttribute("loggedInUser", users);
                session.setAttribute("userRole", users.getUserRole());

                String userRole = users.getUserRole();
                if ("admin".equals(userRole)) {
                    resp.sendRedirect(req.getContextPath() + "/users?action=listUsers");
                } else if ("manager".equals(userRole)) {
                    resp.sendRedirect(req.getContextPath() + "/users?action=listUsers");
                } else if ("employee".equals(userRole)) {
                    resp.sendRedirect(req.getContextPath() + "/users?action=listUsers");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/index.jsp");
                }

                session.setAttribute("successMessage", "Đăng nhập thành công!");
            } else {
                session.setAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu! Vui lòng thử lại!");
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình đăng nhập. Vui lòng thử lại sau.");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
