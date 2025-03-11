package kiosk;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<MenuItem> itemList;

    /*
    TempDB로부터 특정 카테고리의 데이터를 받아와 itemList에 추가한다.
     */
    Menu(String category, TempDB tempDB){
        itemList = tempDB.getProductList(category);
    }

    /*
    itemList에 저장된 품목들을 리스트 형태로 보여준다.
    사용자의 선택을 리턴한다.
     */
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

    /*
    리스트의 인덱스로 itemList에 접근하여 MenuItem을 반환한다.
     */
    public MenuItem selectedItem(int num) {
        return itemList.get(num-1);
    }

}
