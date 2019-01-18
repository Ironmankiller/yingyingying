package com.qstm.industry.Model;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
