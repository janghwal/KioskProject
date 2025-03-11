package kiosk;

import java.util.*;

public class OrderItem {
    /* 장바구니 정보가 저장된다. */
    private final HashMap<String, Integer> orderItemMap = new HashMap<>();
    private final TempDB tempDB;

    OrderItem(TempDB tempDB){
        this.tempDB = tempDB;
    }

    //수량을 한개 늘리는 매서드 사용자가 매뉴를 선택한 경우 호출된다.
    public void addOrder(MenuItem menuItem){
        orderItemMap.merge(menuItem.getProductName(), 1, Integer::sum);
    }

    //수량을 한개 줄이는 매서드, 사용 X
    public void delOrder(MenuItem menuItem){
        orderItemMap.merge(menuItem.getProductName(), -1, Integer::sum);
    }

    //주문 후 혹은 캔슬 할 경우 실행되어 장바구니를 비운다.
    public void delAllOrder(){
        orderItemMap.clear();
    }

    //품목 이름을 입력받아 DB로부터 단가 정보를 가져온다.
    public Double priceOrder(String productName){
        return tempDB.getPrice(productName);
    }

    /*
    Stream 미사용 버전
    public double showOrderItem(){
        double totalPrice = 0.0;
        System.out.println("\n[ Orders ]");
        int i = 1;
        for (Map.Entry<String, Integer> entry : orderItemMap.entrySet()) {
            System.out.print(i+". "+entry.getKey() + "| " + entry.getValue() + "EA | W " + priceOrder(entry.getKey()) + "| W ");
            System.out.printf("%.1f%n", entry.getValue() * priceOrder(entry.getKey()));
            totalPrice += entry.getValue() * priceOrder(entry.getKey());
            i++;
        }
        System.out.print("\n[ Total ]\nW ");
        System.out.printf("%.1f%n", totalPrice);
        System.out.println("==================================================================================");
        return totalPrice;
    }
     */


    /*
    장바구니에서 수량을 변경할때 사용한다.
     */
    public void quantityChange(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n[ Orders ]");
        int i = 1;
        int choiceNum;
        ArrayList<String> tempList = new ArrayList<>();
        System.out.println("Num | Menu Item        | Quantity | Unit Price | Price");
        for (Map.Entry<String, Integer> entry : orderItemMap.entrySet()) {
            System.out.print(i+".  | "+entry.getKey() + "|   " + entry.getValue() + "EA    |   W " + priceOrder(entry.getKey()) + "    | W ");
            System.out.printf("%.1f%n", entry.getValue() * priceOrder(entry.getKey()));
            i++;
            tempList.add(entry.getKey());
        }
        System.out.println("==================================================================================");
        System.out.print("수량을 변경 할 메뉴의 번호를 입력해주세요: ");
        choiceNum = scan.nextInt();

        try{
            System.out.println("\n**********************************************************************************");
            System.out.print(tempList.get(choiceNum-1) + "|   " + orderItemMap.get(tempList.get(choiceNum-1)) + "EA    |   W " + priceOrder(tempList.get(choiceNum-1)) + "    | W ");
            System.out.printf("%.1f%n", orderItemMap.get(tempList.get(choiceNum-1)) * priceOrder(tempList.get(choiceNum-1)));
            System.out.println("**********************************************************************************");
            System.out.print("몇개로 변경하시겠습니까?: ");
            int changeQ = scan.nextInt();
            if(changeQ == 0){
                orderItemMap.remove(tempList.get(choiceNum-1));
                System.out.println("변경되었습니다.");
            }
            else if(changeQ < 0){
                System.out.println("잘못된 숫자를 입력하셨습니다. ");
            }
            else{
                orderItemMap.put(tempList.get(choiceNum-1), changeQ);
                System.out.println("변경되었습니다.");
            }
        }catch (IndexOutOfBoundsException e1){
            System.out.println("\n**********************************************************************************");
            System.out.println("잘못된 숫자를 입력하셨습니다. ");
            System.out.println("**********************************************************************************\n");
        }catch (InputMismatchException e2) {
            System.out.println("\n**********************************************************************************");
            System.out.println("숫자를 입력해주세요. ");
            System.out.println("**********************************************************************************\n");
        }
    }



    /*
    stream, 람다식을 사용해서 장바구니에 담긴 품목들과 수량 가격을 보여준다.
    총 가격을 반환한다.
     */
    public double showOrderItem(){
        double totalPrice;
        System.out.println("\n[ Orders ]");
        System.out.println("Menu Item        | Quantity | Unit Price | Price");
        totalPrice = orderItemMap.entrySet().stream()
                .peek(entry -> {
                    System.out.print(entry.getKey() + "|   " + entry.getValue() + "EA    |   W " + priceOrder(entry.getKey()) + "    | W ");
                    System.out.printf("%.1f%n", entry.getValue() * priceOrder(entry.getKey()));
                })
                .mapToDouble(entry -> entry.getValue() * priceOrder(entry.getKey())).sum();
        System.out.print("\n[ Total ]\nW ");
        System.out.printf("%.1f%n", totalPrice);
        System.out.println("==================================================================================");
        return totalPrice;
    }

    /*
    장바구니에 항목이 있는지 체크하는 매서드
     */
    public boolean isEmptyCheck(){
        return !orderItemMap.isEmpty();
    }
}
