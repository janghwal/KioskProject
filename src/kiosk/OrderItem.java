package kiosk;

import java.util.HashMap;
import java.util.Map;

public class OrderItem {
    private HashMap<String, Integer> orderItemList = new HashMap<>();

    public void addOrder(MenuItem menuItem){
        orderItemList.merge(menuItem.getProductName(), 1, Integer::sum);
    }

    public void delOrder(MenuItem menuItem){
        orderItemList.merge(menuItem.getProductName(), -1, Integer::sum);
    }

    public void delAllOrder(){
        orderItemList.clear();
    }

    public Double priceOrder(String productName){
        TempDB tempDB = new TempDB();
        return tempDB.getPrice(productName);
    }

    public void showOrderItem(){
        Double totalPrice = 0.0;
        System.out.println("[ Orders ]");
        for (Map.Entry<String, Integer> entry : orderItemList.entrySet()) {
            System.out.println(entry.getKey() + "| " + entry.getValue() + "EA | W " + priceOrder(entry.getKey()) + "| W " + entry.getValue() * priceOrder(entry.getKey()));
            totalPrice += entry.getValue() * priceOrder(entry.getKey());
        }
        System.out.println("\n[ Total ]\nW "+totalPrice);
        System.out.println("==================================================================================");
    }

    public boolean isEmptyCheck(){
        if(orderItemList.isEmpty()){
            return false;
        }
        return true;
    }
}
