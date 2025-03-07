package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private List<Menu> menuList = new ArrayList<>();

    Kiosk(){
        TempDB tempDB = new TempDB();
        Menu burgersMenu = new Menu("Burgers", tempDB);
        Menu drinksMenu = new Menu("Drinks", tempDB);
        Menu dessertsMenu = new Menu("Desserts", tempDB);
        menuList.add(burgersMenu);
        menuList.add(drinksMenu);
        menuList.add(dessertsMenu);
    }

    public void start(){
        int choiceMainMenu;
        do {
            choiceMainMenu = showMainMenu();
            switch (choiceMainMenu) {
                case 1:
                    while(true) {
                        int choiceItem = menuList.get(0).showItem();
                        if(choiceItem == 0){
                            break;
                        }
                        try {
                            showItem(menuList.get(0).selectedItem(choiceItem));
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("해당 제품은 없는 제품 입니다.");
                        }
                    }
                    break;
                case 2:
                    while(true) {
                        int choiceItem = menuList.get(1).showItem();
                        if(choiceItem == 0){
                            break;
                        }
                        try {
                            showItem(menuList.get(1).selectedItem(choiceItem));
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("해당 제품은 없는 제품 입니다.");
                        }
                    }
                    break;
                case 3:
                    while(true) {
                        int choiceItem = menuList.get(2).showItem();
                        if(choiceItem == 0){
                            break;
                        }
                        try {
                            showItem(menuList.get(2).selectedItem(choiceItem));
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("해당 제품은 없는 제품 입니다.");
                        }
                    }
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("매뉴의 숫자를 입력해주세요.");
                    break;
            }
        } while (choiceMainMenu != 0);
    }

    public int showMainMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("[ MAIN MENU ]");
        System.out.println("1. Burgers");
        System.out.println("2. Drinks");
        System.out.println("3. Desserts");
        System.out.println("0. 종료      | 종료");
        System.out.println("==================================================================================");
        System.out.print("번호를 입력 해주세요: ");
        return scan.nextInt();
    }

    //장바구니에 추가하는 함수로 수정
    public void showItem(MenuItem menuItem){
        System.out.println("**********************************************************************************");
        System.out.println(menuItem.getProductName()+"| W "+menuItem.getProductPrice()+"| "+menuItem.getProductInfo());
        System.out.println("**********************************************************************************");
    }
}
