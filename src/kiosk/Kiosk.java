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
        OrderItem orderItem = new OrderItem();

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
                            if(showItem(menuList.get(0).selectedItem(choiceItem)) == 1){
                                System.out.println(menuList.get(0).selectedItem(choiceItem).getProductName()+"제품이 장바구니에 추가되었습니다.");
                                System.out.println("==================================================================================");
                                orderItem.addOrder(menuList.get(0).selectedItem(choiceItem));
                            }else{
                                System.out.println("취소되었습니다.");
                            }
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
                            if(showItem(menuList.get(1).selectedItem(choiceItem)) == 1){
                                System.out.println(menuList.get(1).selectedItem(choiceItem).getProductName()+"제품이 장바구니에 추가되었습니다.");
                                System.out.println("==================================================================================");
                                orderItem.addOrder(menuList.get(1).selectedItem(choiceItem));
                            }else{
                                System.out.println("취소되었습니다.");
                            }
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
                            if(showItem(menuList.get(2).selectedItem(choiceItem)) == 1){
                                System.out.println(menuList.get(2).selectedItem(choiceItem).getProductName()+"제품이 장바구니에 추가되었습니다.");
                                System.out.println("==================================================================================");
                                orderItem.addOrder(menuList.get(2).selectedItem(choiceItem));
                            }else{
                                System.out.println("취소되었습니다.");
                            }
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("해당 제품은 없는 제품 입니다.");
                        }
                    }
                    break;
                case 4:
                    orderItem.showOrderItem();
                    if(byItem() == 1){
                        System.out.println("주문 완료");
                        orderItem.delAllOrder();
                    }
                    break;
                case 5:

                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("메뉴의 숫자를 입력해주세요.");
                    break;
            }
        } while (choiceMainMenu != 0);
    }

    public int showMainMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("[ MAIN MENU ]");
        System.out.println("1. Burgers");
        System.out.println("2. Drinks");
        System.out.println("3. Desserts\n");
        System.out.println("[ ORDER MENU ]");
        System.out.println("4. Orders");
        System.out.println("5. Cancel");
        System.out.println("0. 종료      | 종료");
        System.out.println("==================================================================================");
        System.out.print("번호를 입력 해주세요: ");
        return scan.nextInt();
    }

    //장바구니에 추가하는 함수로 수정
    public int showItem(MenuItem menuItem){
        Scanner scan = new Scanner(System.in);
        System.out.println("**********************************************************************************");
        System.out.println("[ Orders ]");
        System.out.println(menuItem.getProductName()+"| W "+menuItem.getProductPrice()+"| "+menuItem.getProductInfo());
        System.out.println("**********************************************************************************");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인       2. 취소");
        System.out.print("번호를 입력 해주세요: ");
        int num = scan.nextInt();
        System.out.println("==================================================================================");
        return num;
    }

    public int byItem(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1. 주문       2. 메뉴판");
        System.out.print("번호를 입력 해주세요: ");
        int num = scan.nextInt();
        System.out.println("==================================================================================");
        return num;
    }
}
