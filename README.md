# KioskProject
## Main
  * main 실행
  * Kiosk 인스턴스 생성 및 start() 매서드 실행

    
## Kiosk
  * __field__
    * menuList
      * Menu 타입 인스턴스를 리스트로 저장
      * 카테고리 별 메뉴로 사용 ex) Burgers 메뉴 인스턴스, Drinks 메뉴 인스턴스 등
    * orderItem
      * OrderItem 타입 인스턴스 저장


    
  * __constructor__
    * TempDB 인스턴스 생성 -> Menu 인스턴스 생성 및 OrderItem 인스턴스 생성에 사용
    * 카테고리별 Menu 인스턴스 생성 후 menuList에 추가
    * orderItem 인스턴스 생성



  * __method__
    * start()
      * 프로그램의 메인 축
    * showMainMenu()
      * 메인 메뉴를 보여주고 사용자의 선택 값을 리턴한다.
        ```
        [ MAIN MENU ]
        1. Burgers
        2. Drinks
        3. Desserts
        0. 종료      | 종료
        ==================================================================================
        번호를 입력 해주세요:
        ```
    * showItem(MenuItem menuItem)
      * 장바구니에 추가하고자 하는 품목에 대해 사용자의 의사를 재확인하고 리턴
        ```
        **********************************************************************************
        [ Orders ]
        Hamburger        | W 5.4| 비프패티를 기반으로 야채가 들어간 기본버거
        **********************************************************************************
        위 메뉴를 장바구니에 추가하시겠습니까?
        1. 확인       2. 취소
        번호를 입력 해주세요:
        ```
    * byItem()
      * 장바구니에서 선택할 수 있는 메뉴를 보여주고 사용자의 선택을 리턴
        ```
        ==================================================================================
        1. 주문      2.수량 변경   3. 메뉴판
        번호를 입력 해주세요:
        ```
    * choiceDiscount(double totalPrice)
      * 사용자의 신분 확인 후 총 금액에 할인율 적용하여 리턴
        ```
        할인 정보를 입력해주세요.
        1. 국가유공자 : 10%
        2. 군인     :  5%
        3. 학생     :  3%
        4. 일반     :  0%
        
        번호를 입력 해주세요:
        ```

## Menu
  * __field__
    * itemList
      * 카테고리별 MenuItem(품목)이 리스트 형태로 저장된다.

        
  * __constructor__
    * 카테고리와 TempDB 인스턴스를 받아 카테고리 별 품목을 itemList에 저장한다.

      
  * __method__
    * showItem()
      * 카테고리 별 메뉴를 출력한 후 사용자의 선택을 리턴한다.
        ```
        [ BURGERS MENU ]
        1. ShackBurger      | W 6.9| 토마토, 양상추, 쉑소스가 토핑된 치즈버거
        2. SmokeShack       | W 8.9| 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
        3. Cheeseburger     | W 6.9| 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
        4. Hamburger        | W 5.4| 비프패티를 기반으로 야채가 들어간 기본버거
        0. 뒤로가기      | 뒤로
        ==================================================================================
        번호를 입력 해주세요:
        ```
        ```
        [ DRINKS MENU ]
        1. ClassicCola      | W 2.5| 시원하고 달콤한 콜라 음료
        2. Lemonade         | W 2.8| 상큼한 레몬과 설탕이 어우러진 음료
        3. IcedTea          | W 2.3| 얼음과 함께 즐기는 시원한 홍차
        4. Milkshake        | W 4.0| 부드럽고 크리미한 초콜릿 밀크쉐이크
        0. 뒤로가기      | 뒤로
        ==================================================================================
        번호를 입력 해주세요:
        ```
        
    * selectedItem(int num)
      * 사용자가 선택한 번호의 MenuItem 인스턴스를 리턴한다.



## MenuItem
  * __field__
    * productName 제품명
      
    * productPrice 단가
      
    * productInfo  제품 설명
      
    * productCategory  제품 분류 (카테고리)
      
  * __constructor__
    * 제품명, 단가, 제품 설명, 제품 분류 초기화
   
      
  * __method__
    * getProductName()
   
    * getProductPrice()
   
    * getProductInfo()
   
    * getProductCategory()


## OrderItem
  * __field__
    * orderItemMap
      * 실제 저장되는 장바구니
      * 품목명, 수량이 HashMap 형태로 저장된다.
    * tempDB
      * 단가 확인을 위해 사용


  * __constructor__
    * Kiosk로부터 전달 받은 tempDB로 this.tempDB 초기화


  * __method__
    * addOrder(MenuItem menuItem)
      * 사용자가 메뉴를 선택하면 HashMap 품목과 수량 1 저장
      * 이미 매뉴가 있었을 경우 수량 1 추가
    * delOrder(MenuItem menuItem)
      * 사용자의 매뉴 선택시 수량 -1 하는 함수
      * 사용하고 있지 않음 -> 수량을 1 줄이는 것이 아닌 특정 수로 변경하는 로직 구현
    * delAllOrder()
      * 저장되어 있는 모든 장바구니 항목을 삭제하는 매서드
      * 주문 완료 후, 메인 메뉴 5번 캔슬 할 경우 Kiosk에서 호출된다.
    * priceOrder(String productName)
      * tempDB에서 품목에 맞는 단가 정보를 가져오는 매서드
      * showOrderItem()에서 호출하여 사용한다.
    * showOrderItem()
      * 장바구니에 담겨있는 품목과, 단가, 수량, 총금액을 보여준다.
      * 주문, 수량변경, 메뉴판 3가지 메뉴를 추가로 보여주고 사용자로부터의 선택을 리턴한다.
      ```
      [ Orders ]
        Menu Item        | Quantity | Unit Price | Price
        Milkshake        |   1EA    |   W 4.0    | W 4.0
        ShackBurger      |   2EA    |   W 6.9    | W 13.8
        Cheesecake       |   1EA    |   W 4.0    | W 4.0
        ChocolateBrownie |   3EA    |   W 3.5    | W 10.5
        ClassicCola      |   1EA    |   W 2.5    | W 2.5
        Cheeseburger     |   1EA    |   W 6.9    | W 6.9
        SmokeShack       |   1EA    |   W 8.9    | W 8.9
        
        [ Total ]
        W 50.6
        ==================================================================================
        1. 주문      2.수량 변경   3. 메뉴판
           번호를 입력 해주세요:
      ```
    * quantityChange()
      * 수량을 변경하는 로직
      * 장바구니 메뉴에서 수량변경을 선택한 경우 출력된다.
      * 변경하고자 하는 품목 선택하기 -> 원하는 수량 입력하기 -> 결과 표시(showOrderItem()) 순으로 진행
      * 변경을 원하는 수량이 0인 경우 stream().filter()를 이용하여 해당 품목 삭제 구현
      ```
      [ Orders ]
      Num | Menu Item        | Quantity | Unit Price | Price
      1.  | Milkshake        |   1EA    |   W 4.0    | W 4.0
      2.  | ShackBurger      |   2EA    |   W 6.9    | W 13.8
      3.  | Cheesecake       |   1EA    |   W 4.0    | W 4.0
      4.  | ChocolateBrownie |   3EA    |   W 3.5    | W 10.5
      5.  | ClassicCola      |   1EA    |   W 2.5    | W 2.5
      6.  | Cheeseburger     |   1EA    |   W 6.9    | W 6.9
      7.  | SmokeShack       |   1EA    |   W 8.9    | W 8.9
      ==================================================================================
      수량을 변경 할 메뉴의 번호를 입력해주세요:
      ```
      ```
      **********************************************************************************
      Milkshake        |   1EA    |   W 4.0    | W 4.0
      **********************************************************************************
      몇개로 변경하시겠습니까?:
      ```
      ```
      [ Orders ]
      Menu Item        | Quantity | Unit Price | Price
      Milkshake        |   8EA    |   W 4.0    | W 32.0
      ShackBurger      |   2EA    |   W 6.9    | W 13.8
      Cheesecake       |   1EA    |   W 4.0    | W 4.0
      ChocolateBrownie |   3EA    |   W 3.5    | W 10.5
      ClassicCola      |   1EA    |   W 2.5    | W 2.5
      Cheeseburger     |   1EA    |   W 6.9    | W 6.9
      SmokeShack       |   1EA    |   W 8.9    | W 8.9
      
      [ Total ]
      W 78.6
      ==================================================================================
      1. 주문      2.수량 변경   3. 메뉴판
      번호를 입력 해주세요:
      ```
    * isEmptyCheck()
      * 장바구니가 비어있는지 확인하는 매서드 Kiosk에서 호출하여 사용된다.
      * 비어 있을경우 false, 비어있지 않을 경우 true 리턴


## Discount
  * Enum 사용
  * NATIONAL_MERIT_RECIPIENT, MILITARY_PERSONNEL, STUDENT, GENERAL로 구성된다.
  * 각각 국가 유공자, 군인, 학생, 일반이며 람다식을 사용하여 10%, 5%, 3%, 0%의 할인을 적용하는 매서드 구현되어 있다.


## TempDB
  * __field__
    * productList
      * MenuItem 타입의 객체를 리스트 형태로 저장한다.
      * DB 대용으로 사용하기 위해 만들었다.


  * __constructor__
    * DB에 들어갈 모든 데이터가 하드코딩 되어 productList에 저장된다.


  * __method__
    * getProductList(String category)
      * 카테고리에 맞는 MenuItem 리스트를 반환한다.

    * getPrice(String productName)
      * 제품명에 맞는 단가를 리턴한다.
