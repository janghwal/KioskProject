package kiosk;

import java.util.ArrayList;
import java.util.List;

//추후 관리자 메뉴 만들어서 DB 관리 시키기

public class TempDB {
    private List<MenuItem> productList = new ArrayList<>();

    TempDB(){
        MenuItem menuItem1 = new MenuItem("ShackBurger      ", 6.9 , "토마토, 양상추, 쉑소스가 토핑된 치즈버거", "Burgers");
        productList.add(menuItem1);
        MenuItem menuItem2 = new MenuItem("SmokeShack       ", 8.9 , "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", "Burgers");
        productList.add(menuItem2);
        MenuItem menuItem3 = new MenuItem("Cheeseburger     ", 6.9 , "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", "Burgers");
        productList.add(menuItem3);
        MenuItem menuItem4 = new MenuItem("Hamburger        ", 5.4 , "비프패티를 기반으로 야채가 들어간 기본버거", "Burgers");
        productList.add(menuItem4);

        MenuItem menuItem5 = new MenuItem("ClassicCola      ", 2.5 , "시원하고 달콤한 콜라 음료", "Drinks");
        productList.add(menuItem5);
        MenuItem menuItem6 = new MenuItem("Lemonade         ", 2.8 , "상큼한 레몬과 설탕이 어우러진 음료", "Drinks");
        productList.add(menuItem6);
        MenuItem menuItem7 = new MenuItem("IcedTea          ", 2.3 , "얼음과 함께 즐기는 시원한 홍차", "Drinks");
        productList.add(menuItem7);
        MenuItem menuItem8 = new MenuItem("Milkshake        ", 4.0 , "부드럽고 크리미한 초콜릿 밀크쉐이크", "Drinks");
        productList.add(menuItem8);

        MenuItem menuItem9 = new MenuItem("ChocolateBrownie ", 3.5 , "진한 초콜릿 맛의 브라우니", "Desserts");
        productList.add(menuItem9);
        MenuItem menuItem10 = new MenuItem("ApplePie         ", 3.8 , "달콤한 사과와 시나몬이 들어간 파이", "Desserts");
        productList.add(menuItem10);
        MenuItem menuItem11 = new MenuItem("IceCreamSundae   ", 4.2 , "아이스크림 위에 다양한 토핑을 얹은 디저트", "Desserts");
        productList.add(menuItem11);
        MenuItem menuItem12 = new MenuItem("Cheesecake       ", 4.0 , "부드럽고 고소한 치즈케이크", "Desserts");
        productList.add(menuItem12);
    }

    public List<MenuItem> getProductList(String category){
        List<MenuItem> tempList = new ArrayList<>();
        for(MenuItem item : productList){
            if(item.getProductCategory().equals(category)){
                tempList.add(item);
            }
        }

        return tempList;
    }

    public Double getPrice(String productName){
        for(MenuItem item: productList){
            if(item.getProductName().equals(productName)){
                return item.getProductPrice();
            }
        }
        return 0.0;
    }
}
