package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    /*
    menuList는 Burgers Menu, Drinks Menu, Desserts Menu 3가지를 저장한다.
    orderItem은 장바구니 객체로 제품을 선택하거나 주문 할 때 호출해서 사용한다.
    menuList, orderItem 객체 변경 없으므로 final 키워드 추가
     */
    private final List<Menu> menuList = new ArrayList<>();
    private final OrderItem orderItem;
    Scanner scan = new Scanner(System.in);

    /*
    tempDB 객체를 만든 후 tempDB에 초기화 되어 있는 정보를 가져온다.
    Burgers, Drinks, Desserts 에 맞는 항목만들 추려서 각각의 Menu 객체를 만들고 menuList에 저장한다.
    tempDB 객체를 장바구니 객체에 넘겨 품목별 단가 정보를 사용할 수 있도록 한다.
     */
    Kiosk(){
        TempDB tempDB = new TempDB();
        Menu burgersMenu = new Menu("Burgers", tempDB);
        Menu drinksMenu = new Menu("Drinks", tempDB);
        Menu dessertsMenu = new Menu("Desserts", tempDB);
        menuList.add(burgersMenu);
        menuList.add(drinksMenu);
        menuList.add(dessertsMenu);
        orderItem = new OrderItem(tempDB);
    }

    /*
    1~3번 메뉴와 0번 매뉴는 항상 출력 및 분기
    장바구니에 항목이 없을 경우 4,5번 항목은 잘못된 입력으로 처리
    1~3번: menuList 객체를 호출하여 진행, 주문은 orderItem에 저장
    4번: orderItem 호출
    5번: orderItem의 내부 초기화
     */
    public void start(){
        int choiceMainMenu;
        do{
            choiceMainMenu = showMainMenu();
            if(choiceMainMenu >= 1 && choiceMainMenu <=3){
                int choiceItem = menuList.get(choiceMainMenu-1).showItem();
                if(choiceItem == 0){
                    continue;
                }
                try {
                    if(showItem(menuList.get(choiceMainMenu-1).selectedItem(choiceItem))){
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

    /*
    메뉴를 보여주고 사용자의 선택값을 반환한다.
     */
    private int showMainMenu(){
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

    /*
    선택 품목을 확인 시켜주고 추가할 것인지 재확인
    결과에 따라 boolean 값 반환
     */
    private boolean showItem(MenuItem menuItem){
        System.out.println("\n**********************************************************************************");
        System.out.println("[ Orders ]");
        System.out.println(menuItem.getProductName()+"| W "+menuItem.getProductPrice()+"| "+menuItem.getProductInfo());
        System.out.println("**********************************************************************************");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인       2. 취소");
        System.out.print("번호를 입력 해주세요: ");
        int num = scan.nextInt();
        System.out.println("\n==================================================================================");
        if(num == 1){
            return true;
        }
        else{
            return false;
        }
    }

    /*
    장바구니 메뉴를 보여주고 사용자의 선택을 반환한다.
     */
    private int byItem(){
        System.out.println("1. 주문      2.수량 변경   3. 메뉴판");
        System.out.print("번호를 입력 해주세요: ");
        int num = scan.nextInt();
        System.out.println("\n==================================================================================");
        return num;
    }

    /*
    할인 정보를 제공 후 할인이 적용된 totalPrice를 반환한다.
     */
    private double choiceDiscount(double totalPrice){
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
