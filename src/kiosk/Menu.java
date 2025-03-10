package kiosk;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<MenuItem> itemList;

    Menu(String category, TempDB tempDB){
        itemList = tempDB.getProductList(category);
    }

    public int showItem(){
        Scanner scan = new Scanner(System.in);
        int i = 1;
        System.out.println("\n[ "+itemList.get(0).getProductCategory().toUpperCase()+" MENU ]");
        for(MenuItem item : itemList){
            System.out.println(i+". "+item.getProductName()+"| W "+item.getProductPrice()+"| "+item.getProductInfo());
            i++;
        }
        System.out.println("0. 뒤로가기      | 뒤로");
        System.out.println("==================================================================================");
        System.out.print("번호를 입력 해주세요: ");
        return scan.nextInt();
    }

    public MenuItem selectedItem(int num) {
        return itemList.get(num-1);
    }

}
