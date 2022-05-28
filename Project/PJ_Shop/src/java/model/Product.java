/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 84969
 */
public class Product {
    private String productId;
      private String description;
      private float price;
      private String productName;
      private String unit;
      private int year;
      private int quantity;
      private String img;
      private Category categoryID;

    public Product() {
    }

    public Product(String productId, String description, float price, String productName, String unit, int year, int quantity, String img, Category categoryID) {
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.productName = productName;
        this.unit = unit;
        this.year = year;
        this.quantity = quantity;
        this.img = img;
        this.categoryID = categoryID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }
              

      
}
