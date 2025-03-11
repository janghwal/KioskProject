package kiosk;

public class MenuItem {
    /*
    productName 제품명, productPrice 제품 가격, productInfo 제품 설명, productCategory 카테고리
     */
    private final String productName;
    private final Double productPrice;
    private final String productInfo;
    private final String productCategory;

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
