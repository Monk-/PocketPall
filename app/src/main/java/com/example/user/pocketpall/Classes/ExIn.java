package com.example.user.pocketpall.Classes;

import java.util.Date;

enum Categories{

    Car(0, "Car"),
    Clothing(1, "Clothing"),
    Electronics(2, "Electronics"),
    Expenses(3, "Expenses"),
    Home(4, "Home"),
    Income(5, "Income"),
    Work(6, "Work"),
    Education(7, "Education"),
    Sports(8, "Sports");


    Categories(int code, String title){
        this.code=code;
        this.title = title;
    }
    protected int code;
    protected String title;

    public int getCode() {
        return this.code;
    }
    public String getString(Categories d){
        return d.title;
    }
}


public abstract class ExIn {
    String title;
    String comment;
    String date;
    Double amount;

    public ExIn(String title, String comment, String date, Double amount, Integer category) {
        this.title = title;
        this.comment = comment;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    Integer category;
    //TODO category


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ExIn{" +
                "title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", category=" + category +
                '}';
    }
}
