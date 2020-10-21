package com.example.p533;

public class Movie {
    int rank;
    String movie_name;
    long sales_amount;
    String open_date;
    String img;

    public Movie(){}

    public Movie(int rank, String movie_name, long sales_amount, String open_date, String img) {
        this.rank = rank;
        this.movie_name = movie_name;
        this.sales_amount = sales_amount;
        this.open_date = open_date;
        this.img = img;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public long getSales_amount() {
        return sales_amount;
    }

    public void setSales_amount(long sales_amount) {
        this.sales_amount = sales_amount;
    }

    public String getOpen_date() {
        return open_date;
    }

    public void setOpen_date(String open_date) {
        this.open_date = open_date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
