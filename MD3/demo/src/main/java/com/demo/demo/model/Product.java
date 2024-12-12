package com.demo.demo.model;

public class Product {
    private Integer productID;
    private String productName;
    private Double productPrice;
    private Integer productStock;
    private String productColor;
    private String productDescription;
    private Integer categoryID;
    private String categoryName;

    public Product() {
    }

    public Product(String productName, Double productPrice, Integer productStock, String productColor, String productDescription, Integer categoryID, String categoryName) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productColor = productColor;
        this.productDescription = productDescription;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    // Constructor đầy đủ
    public Product(Integer productID, String productName, Double productPrice, Integer productStock, String productColor, String productDescription, Integer categoryID, String categoryName) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productColor = productColor;
        this.productDescription = productDescription;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
