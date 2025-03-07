package kiosk;

public class MenuItem {
    private String productName;
    private Double productPrice;
    private String productInfo;
    private String productCategory;

    MenuItem(String productName, Double productPrice, String productInfo, String productCategory){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productInfo = productInfo;
        this.productCategory = productCategory;
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
    public String getProductCategory(){
        return productCategory;
    }

}
