package kiosk;

public class MenuItem {
    private String productName;
    private Double productPrice;
    private String productInfo;

    MenuItem(String productName, Double productPrice, String productInfo){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productInfo = productInfo;
    }

    public String getProductName(){
        return productName;
    }
    public Double getProductPrice(){
        return productPrice;
    }
    public String getProductInfo(){
        return productInfo;
    }

}
