//InClass03
//Members: Venkatesh Bonageri (800964302)
//         Vikas Deshpande    (800964852)

package com.example.venkatesh.inclass03;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Student implements Serializable{

    String name;
    String email;
    String department;
    int mood;

    public Student(String name, String email, String department, int mood) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.mood = mood;
    }

}
