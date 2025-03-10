package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private List<Menu> menuList = new ArrayList<>();
    private OrderItem orderItem;
    private TempDB tempDB = new TempDB();

    Kiosk(){
        Menu burgersMenu = new Menu("Burgers", tempDB);
        Menu drinksMenu = new Menu("Drinks", tempDB);
        Menu dessertsMenu = new Menu("Desserts", tempDB);
        menuList.add(burgersMenu);
        menuList.add(drinksMenu);
        menuList.add(dessertsMenu);
        orderItem = new OrderItem(tempDB);
    }

    public void start(){
        int choiceMainMenu = 0;

        do{
            choiceMainMenu = showMainMenu();
            if(choiceMainMenu >= 1 && choiceMainMenu <=3){
                int choiceItem = menuList.get(choiceMainMenu-1).showItem();
                if(choiceItem == 0){
                    continue;
                }
                try {
                    if(showItem(menuList.get(choiceMainMenu-1).selectedItem(choiceItem)) == 1){
                        System.out.println(menuList.get(choiceMainMenu-1).selectedItem(choiceItem).getProductName()+"제품이 장바구니에 추가되었습니다.");
                        System.out.println("==================================================================================\n");
                        orderItem.addOrder(menuList.get(choiceMainMenu-1).selectedItem(choiceItem));
                    }else{
                        System.out.println("취소되었습니다.\n");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("해당 제품은 없는 제품 입니다.\n");
                }
            }
            else if(choiceMainMenu == 4 && orderItem.isEmptyCheck()){
                while(true){
                    double totalPrice = orderItem.showOrderItem();
                    int byNum = byItem();
                    if(byNum == 1){
                        totalPrice = choiceDiscount(totalPrice);
                        System.out.println("주문이 완료되었습니다. 금액은 W "+ totalPrice + " 입니다.\n\n");
                        System.out.println("==================================================================================");
                        orderItem.delAllOrder();
                        break;
                    }
                    else if(byNum == 2){
                        orderItem.quantityChange();
                    }
                    else if(byNum == 3){
                        break;
                    }
                }

            }
            else if(choiceMainMenu == 5 && orderItem.isEmptyCheck()){
                orderItem.delAllOrder();
            }
            else{
                System.out.println("메뉴의 숫자를 입력해주세요.");
            }
        }while(choiceMainMenu != 0);
    }

    public int showMainMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n[ MAIN MENU ]");
        System.out.println("1. Burgers");
        System.out.println("2. Drinks");
        System.out.println("3. Desserts");
        if(orderItem.isEmptyCheck()){
            System.out.println("\n[ ORDER MENU ]");
            System.out.println("4. Orders");
            System.out.println("5. Cancel");
        }
        System.out.println("0. 종료      | 종료");
        System.out.println("==================================================================================");
        System.out.print("번호를 입력 해주세요: ");
        return scan.nextInt();
    }

    //장바구니에 추가하는 함수로 수정
    public int showItem(MenuItem menuItem){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n**********************************************************************************");
        System.out.println("[ Orders ]");
        System.out.println(menuItem.getProductName()+"| W "+menuItem.getProductPrice()+"| "+menuItem.getProductInfo());
        System.out.println("**********************************************************************************");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인       2. 취소");
        System.out.print("번호를 입력 해주세요: ");
        int num = scan.nextInt();
        System.out.println("\n==================================================================================");
        return num;
    }

    public int byItem(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1. 주문      2.수량 변경   3. 메뉴판");
        System.out.print("번호를 입력 해주세요: ");
        int num = scan.nextInt();
        System.out.println("\n==================================================================================");
        return num;
    }

    public double choiceDiscount(double totalPrice){
        Scanner scan = new Scanner(System.in);
        System.out.println("할인 정보를 입력해주세요.");
        System.out.println("""
                1. 국가유공자 : 10%
                2. 군인     :  5%
                3. 학생     :  3%
                4. 일반     :  0%
                """);
        System.out.print("번호를 입력 해주세요: ");
        return switch(scan.nextInt()){
            case 1:
                yield Discount.NATIONAL_MERIT_RECIPIENT.apply(totalPrice);
            case 2:
                yield Discount.MILITARY_PERSONNEL.apply(totalPrice);
            case 3:
                yield Discount.STUDENT.apply(totalPrice);
            case 4:
                yield Discount.GENERAL.apply(totalPrice);
            default:
                yield totalPrice;
        };
    }

}
