package com.example.global;

import com.example.demo.enteties.Item;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Item> cart;
    static {
        cart=new ArrayList<>();
    }
}
