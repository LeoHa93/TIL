package com.example.p500;

public class Item {
    String id, name;
    int age;
    String img;
    int rank;

    public Item(){}

    public Item(String id, String name, int age, String img) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.img = img;
    }

    public Item(String id, String name, int age, String img, int rank) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.img = img;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRank() {return rank;}

    public void setRank(int rank) {this.rank = rank;}
}
