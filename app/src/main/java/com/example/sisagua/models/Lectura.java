package com.example.sisagua.models;
import com.google.gson.annotations.SerializedName;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;

public class Lectura {

    @SerializedName("data")
    private ArrayList<LecturaResponse> data;

    public ArrayList<LecturaResponse> getData() {
        return data;
    }

    public void setData(ArrayList<LecturaResponse> data) {
        this.data = data;
    }
}

