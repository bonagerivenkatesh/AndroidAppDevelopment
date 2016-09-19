package com.example.libraryguest2.hw2;

import android.net.Uri;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LibrarayGuest on 9/13/2016.
 */
public class Expense implements Serializable{

    String name;
    double amount;
    String category;
    String date;
    String uri;

    public Expense(String name, double amount, String category, String date, String uri) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.uri = uri;
    }
}
