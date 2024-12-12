package com.demo.demo.controller;

import com.demo.demo.model.Category;
import com.demo.demo.model.Product;
import com.demo.demo.service.category.CategoryService;
import com.demo.demo.service.category.ICategoryService;
import com.demo.demo.service.product.IProductService;
import com.demo.demo.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "product", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private IProductService iProductService;
    private ICategoryService iCategoryService;

    @Override
    public void init() {
        iProductService = new ProductService();
        iCategoryService = new CategoryService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "listProducts";
        }
        switch (action) {
            case "viewProduct":
                viewProduct(req, resp);
                break;
            case "createProduct":
                showCreateForm(req, resp);
                break;
            case "updateProduct":
                showUpdateForm(req, resp);
                break;
            case "deleteProduct":
                deleteProduct(req, resp);
                break;
            default:
                listProducts(req, resp);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "listProducts";
        }
        switch (action) {
            case "createProduct":
                createProduct(req, resp);
                break;
            case "updateProduct":
                updateProduct(req, resp);
                break;
            case "deleteProduct":
                deleteProduct(req, resp);
                break;
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String searchKeyword = req.getParameter("searchProduct");
        List<Product> listProducts;

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            listProducts = iProductService.searchProduct(searchKeyword.trim());

            if (listProducts.isEmpty()) {
                session.setAttribute("errorMessage", "Không tìm thấy!");
            }
        } else {
            listProducts = iProductService.findAllProducts();
        }

        req.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard/product/listProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        List<Category> categories = iCategoryService.findAllCategory();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("dashboard/product/createProduct.jsp").forward(req, resp);
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Category> categories = iCategoryService.findAllCategory();

        String productName = req.getParameter("productName");
        double productPrice = Double.parseDouble(req.getParameter("productPrice"));
        int productStock = Integer.parseInt(req.getParameter("productStock"));
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));

        Optional<Product> checkProduct = iProductService.findProductByName(productName);
        if (checkProduct.isPresent()) {
            session.setAttribute("errorMessage", "Sản phẩm này đã tồn tại");
            req.setAttribute("product_name", productName);
            req.setAttribute("product_price", productPrice);
            req.setAttribute("product_stock", productStock);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("dashboard/product/createProduct.jsp").forward(req, resp);
            return;
        }

        if (productPrice <= 0 || productStock <= 0) {
            session.setAttribute("warningMessage", "Giá hoặc số lượng phải lớn hơn 0");
            req.setAttribute("product_name", productName);
            req.setAttribute("product_price", productPrice);
            req.setAttribute("product_stock", productStock);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("dashboard/product/createProduct.jsp").forward(req, resp);
            return;
        }


        Product newProduct = new Product();
        newProduct.setProductName(productName);
        newProduct.setProductPrice(productPrice);
        newProduct.setProductStock(productStock);
        newProduct.setCategoryID(categoryID);

        try {
            iProductService.addProduct(newProduct);
            session.setAttribute("successMessage", "Tạo sản phẩm mới thành công");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Có lỗi khi thêm mới sản phẩm");
            req.getRequestDispatcher(req.getContextPath() + "/product?action=listProducts").forward(req, resp);
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String productName = req.getParameter("productName");
        Optional<Product> productToDelete = iProductService.findProductByName(productName);
        if (productToDelete.isEmpty()) {
            session.setAttribute("errorMessage", "Sản phẩm không tồn tại!");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
            return;
        }

        Product product = productToDelete.get();
        try {
            boolean success = iProductService.deleteProductByName(productName);
            if (success) {
                session.setAttribute("successMessage", "Xoá sản phẩm thành công");
            } else {
                session.setAttribute("errorMessage", "Không thể xoá sản phẩm!");
            }
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Đã xảy ra lỗi khi xoá sản phẩm!");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
        }

    }

    private void viewProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String productName = req.getParameter("productName");
        Optional<Product> productToView = iProductService.findProductByName(productName);
        if (productToView.isEmpty()) {
            session.setAttribute("errorMessage", "Sản phẩm không tồn tại");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
            return;
        }

        Product product = productToView.get();
        try {
            req.setAttribute("product", product);
            req.getRequestDispatcher("dashboard/product/viewProduct.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Đã xảy ra lỗi khi xem thông tin sản phẩm!");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
        }
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        String product_name = req.getParameter("product_name");
        Optional<Product> checkProduct = iProductService.findProductByName(product_name);
        if (checkProduct.isEmpty()) {
            session.setAttribute("errorMessage", "Sản phẩm không tồn tại!");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
            return;
        }

        Product product = checkProduct.get();
        List<Category> categories = iCategoryService.findAllCategory();

        req.setAttribute("product", product);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("dashboard/product/updateProduct.jsp").forward(req, resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Category> categories = iCategoryService.findAllCategory();

        String productName = req.getParameter("productName");
        String productDescription = req.getParameter("productDescription");
        double productPrice = Double.parseDouble(req.getParameter("productPrice"));
        int productStock = Integer.parseInt(req.getParameter("productStock"));
        String productColor = req.getParameter("productColor");
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));

        if (productName == null || productName.isEmpty()) {
            session.setAttribute("errorMessage", "Tên sản phẩm không được để trống!");
            req.setAttribute("productName", productName);
            req.setAttribute("productDescription", productDescription);
            req.setAttribute("productPrice", productPrice);
            req.setAttribute("productStock", productStock);
            req.setAttribute("productColor", productColor);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/dashboard/products/createProduct.jsp").forward(req, resp);
            return;
        }

        if (productPrice <= 0 || productStock <= 0) {
            session.setAttribute("warningMessage", "Giá hoặc số lượng phải lớn hơn 0");
            req.setAttribute("productName", productName);
            req.setAttribute("productDescription", productDescription);
            req.setAttribute("productPrice", productPrice);
            req.setAttribute("productStock", productStock);
            req.setAttribute("productColor", productColor);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/dashboard/products/createProduct.jsp").forward(req, resp);
            return;
        }

        Optional<Product> productToUpdate = iProductService.findProductByName(productName);
        if (productToUpdate.isEmpty()) {
            session.setAttribute("errorMessage", "Không tìm thấy sản phẩm!");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
            return;
        }
        Product product = productToUpdate.get();

        // Cập nhật thông tin sản phẩm
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        product.setProductStock(productStock);
        product.setProductColor(productColor);
        product.setCategoryID(categoryID);

        try {
            iProductService.updateProduct(product);
            session.setAttribute("successMessage", "Chỉnh sửa sản phẩm thành công!");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Đã có lỗi khi cập nhật sản phẩm!");
            resp.sendRedirect(req.getContextPath() + "/product?action=listProducts");
        }
    }

}
