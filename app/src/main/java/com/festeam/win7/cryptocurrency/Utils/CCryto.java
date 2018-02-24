package com.festeam.win7.cryptocurrency.Utils;

/**
 * Created by win7 on 2/23/18.
 */

public class CCryto {
    String name;
    String price;
    String change24;
    String change7;
    String symbol;

    public CCryto() {

    }

    public CCryto(String name, String price, String change24, String change7, String symbol) {
        this.name = name;
        this.price = price;
        this.change24 = change24;
        this.change7 = change7;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange24() {
        return change24;
    }

    public void setChange24(String change24) {
        this.change24 = change24;
    }

    public String getChange7() {
        return change7;
    }

    public void setChange7(String change7) {
        this.change7 = change7;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
