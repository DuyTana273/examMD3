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
import java.util.List;

@WebServlet(name = "users", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private IUserService iUserService;

    @Override
    public void init() throws ServletException {
        iUserService = new UserService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "listUsers";
        }
        switch (action) {
//            case "viewUser":
//                viewUser(req, resp);
//                break;
//            case "createUser":
//                showCreateForm(req, resp);
//                break;
//            case "updateUser":
//                showUpdateForm(req, resp);
//                break;
//            case "deleteUser":
//                deleteUser(req, resp);
//                break;
            default:
                listUsers(req, resp);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "listUsers";
        }
        switch (action) {
//            case "createUser":
//                createUser(req, resp);
//                break;
//            case "updateUser":
//                updateUser(req, resp);
//                break;
//            case "deleteUser":
//                deleteUser(req, resp);
//                break;
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            session.setAttribute("errorMessage", "Bạn cần phải đăng nhập!");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        if ("customer".equals(loggedInUser.getUserRole())) {
            session.setAttribute("warningMessage", "Bạn không có quyền truy cập Dashboard!");
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String searchKeyword = req.getParameter("searchUser");
        List<Users> listUsers;

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            listUsers = iUserService.searchUsers(searchKeyword.trim());

            if (listUsers.isEmpty()) {
                session.setAttribute("errorMessage", "Không tìm thấy!");
            }
        } else {
            listUsers = iUserService.findAllUsers();
        }

        req.setAttribute("listUsers", listUsers);

        RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard/users/listUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
