package com.example.demo.entity;

public class Product {
    private Long id;
    private String name;
    private int price;
    private String category;
    private String image;

    // 기본 생성자
    public Product() {}

    // 전체 필드 생성자 (간단 예시)
    public Product(Long id, String name, int price, String category, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    // getter, setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
