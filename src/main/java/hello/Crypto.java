package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Crypto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("quote")
    private QUOTE quote;

    public void setid(int id) {
        this.id = id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setsymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setslug(String slug) {
        this.slug = slug;
    }

    public void setquote(QUOTE quote) {
        this.quote = quote;
    }

    public int getid() {
        return this.id;
    }

    public String getname() {
        return this.name;
    }

    public String getsymbol() {
        return this.symbol;
    }

    public String getslug() {
        return this.slug;
    }

    public QUOTE getquote() {
        return this.quote;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class INRR {
    @JsonProperty("price")
    private Float price;
    @JsonProperty("volume_24h")
    private Float volume_24h;
    @JsonProperty("percent_change_1h")
    private Float percent_change_1h;
    @JsonProperty("percent_change_24h")
    private Float percent_change_24h;
    @JsonProperty("percent_change_7d")
    private Float percent_change_7d;
    @JsonProperty("market_cap")
    private Float market_cap;

    public void setprice(Float price) {
        this.price = price;
    }

    public void setvolume_24h(Float volume_24h) {
        this.volume_24h = volume_24h;
    }

    public void setpercent_change_1h(Float percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public void setpercent_change_24h(Float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public void setpercent_change_7d(Float percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public void setmarket_cap(Float market_cap) {
        this.market_cap = market_cap;
    }

    public Float getprice() {
        return price;
    }

    public Float getvolume_24h() {
        return volume_24h;
    }

    public Float getpercent_change_1h() {
        return percent_change_1h;
    }

    public Float getpercent_change_24h() {
        return percent_change_24h;
    }

    public Float getpercent_change_7d() {
        return percent_change_7d;
    }

    public Float getmarket_cap() {
        return market_cap;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class QUOTE {
    @JsonProperty("INR")
    INRR INR;

    public void setINR(INRR INR) {
        this.INR = INR;
    }

    public INRR getINR() {
        return INR;
    }

}