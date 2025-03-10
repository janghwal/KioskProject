package kiosk;

import java.util.*;

public class OrderItem {
    private final HashMap<String, Integer> orderItemMap = new HashMap<>();
    private final TempDB tempDB;

    OrderItem(TempDB tempDB){
        this.tempDB = tempDB;
    }

    public void addOrder(MenuItem menuItem){
        orderItemMap.merge(menuItem.getProductName(), 1, Integer::sum);
    }

    public void delOrder(MenuItem menuItem){
        orderItemMap.merge(menuItem.getProductName(), -1, Integer::sum);
    }

    public void delAllOrder(){
        orderItemMap.clear();
    }

    public Double priceOrder(String productName){
        return tempDB.getPrice(productName);
    }

//    Stream 미사용 버전
//    public double showOrderItem(){
//        double totalPrice = 0.0;
//        System.out.println("\n[ Orders ]");
//        int i = 1;
//        for (Map.Entry<String, Integer> entry : orderItemMap.entrySet()) {
//            System.out.print(i+". "+entry.getKey() + "| " + entry.getValue() + "EA | W " + priceOrder(entry.getKey()) + "| W ");
//            System.out.printf("%.1f%n", entry.getValue() * priceOrder(entry.getKey()));
//            totalPrice += entry.getValue() * priceOrder(entry.getKey());
//            i++;
//        }
//        System.out.print("\n[ Total ]\nW ");
//        System.out.printf("%.1f%n", totalPrice);
//        System.out.println("==================================================================================");
//        return totalPrice;
//    }

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
                orderItemMap.put(tempList.get(choiceNum-1), scan.nextInt());
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



    // Stream 사용
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

    public boolean isEmptyCheck(){
        return !orderItemMap.isEmpty();
    }
}
