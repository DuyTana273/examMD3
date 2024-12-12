package com.demo.demo.controller;

import com.demo.demo.model.Category;
import com.demo.demo.service.category.CategoryService;
import com.demo.demo.service.category.ICategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "category", urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {
    private ICategoryService iCategoryService;

    @Override
    public void init() {
        iCategoryService = new CategoryService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "listCategory";
        }
        switch (action) {
            case "listCategory":
                listCategory(req, resp);
                break;
            default:
                listCategory(req, resp);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "listCategory";
        }
//        switch (action) {
//            case "createCategories":
//                createCategories(req, resp);
//                break;
//            case "updateCategories":
//                updateCategories(req, resp);
//                break;
//            case "deleteCategories":
//                deleteCategories(req, resp);
//                break;
//        }
    }

    private void listCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String searchKeyword = req.getParameter("searchCategoryName");
        List<Category> listCategory;

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            listCategory = iCategoryService.searchCategory(searchKeyword.trim());

            if (listCategory.isEmpty()) {
                session.setAttribute("errorMessage", "Không tìm thấy!");
            }
        } else {
            listCategory = iCategoryService.findAllCategory();
        }

        req.setAttribute("listCategories", listCategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/dashboard/categories/listCategories.jsp");
        dispatcher.forward(req, resp);
    }

}
