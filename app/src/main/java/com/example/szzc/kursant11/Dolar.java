package com.example.szzc.kursant11;

public class Dolar implements Currency {

    private Double rate;
    private String date;
    private String Url = "http://api.nbp.pl/api/exchangerates/rates/a/USD/last/?format=json";

    @Override
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getSection() {
        return Section;
    }

    public String getDateSection() {
        return DateSection;
    }

}
