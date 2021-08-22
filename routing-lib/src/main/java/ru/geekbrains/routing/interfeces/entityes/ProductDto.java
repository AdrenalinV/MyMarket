package ru.geekbrains.routing.interfeces.entityes;




public class ProductDto {
    private Long id;
    private String title;
    private double cost;

    public ProductDto(){};


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
