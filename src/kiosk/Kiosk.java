package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private List<MenuItem> itemList = new ArrayList<>();

    Kiosk(){
        MenuItem menuItem1 = new MenuItem("ShackBurger   ", 6.9 , "토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        itemList.add(menuItem1);
        MenuItem menuItem2 = new MenuItem("SmokeShack    ", 8.9 , "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        itemList.add(menuItem2);
        MenuItem menuItem3 = new MenuItem("Cheeseburger  ", 6.9 , "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        itemList.add(menuItem3);
        MenuItem menuItem4 = new MenuItem("Hamburger     ", 5.4 , "비프패티를 기반으로 야채가 들어간 기본버거");
        itemList.add(menuItem4);
    }

    public void start(){
        Scanner scan = new Scanner(System.in);
        int i = 1;
        for(MenuItem item : itemList){
            System.out.println(i+". "+item.getProductName()+"| W "+item.getProductPrice()+"| "+item.getProductInfo());
            i++;
        }
        System.out.println("0. 종료      | 종료");
        System.out.println("==================================================================================");
        while(true) {
            int choiceNumber = scan.nextInt();
            if(choiceNumber == 1){
                System.out.println(itemList.get(0).getProductName()+"| W "+itemList.get(0).getProductPrice()+"| "+itemList.get(0).getProductInfo());
            } else if(choiceNumber == 2){
                System.out.println(itemList.get(1).getProductName()+"| W "+itemList.get(1).getProductPrice()+"| "+itemList.get(1).getProductInfo());
            } else if(choiceNumber == 3){
                System.out.println(itemList.get(2).getProductName()+"| W "+itemList.get(2).getProductPrice()+"| "+itemList.get(2).getProductInfo());
            } else if(choiceNumber == 4){
                System.out.println(itemList.get(3).getProductName()+"| W "+itemList.get(3).getProductPrice()+"| "+itemList.get(3).getProductInfo());
            } else if(choiceNumber == 0){
                System.out.println("프로그램을 종료합니다.");
                break;
            }else{
                System.out.println("잘못된 번호를 입력하였습니다. 다시 입력해주세요.");
            }
        }
    }
}
