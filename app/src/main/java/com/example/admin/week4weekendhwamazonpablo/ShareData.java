package com.example.admin.week4weekendhwamazonpablo;

import com.example.admin.week4weekendhwamazonpablo.model.Response;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Admin on 9/25/2017.
 */

public class ShareData {
    List<Response> data;

    public List<Response> getData() {
        return data;
    }

    public void setData(List<Response> data) {
        this.data = data;
    }

    private static final ShareData ourInstance = new ShareData();

    public static ShareData getInstance() {
        return ourInstance;
    }

    private ShareData() {
    }
}
