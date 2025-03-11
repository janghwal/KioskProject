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
  * __constructor__
  * __method__


## Discount
  * __field__
  * __constructor__
  * __method__


## TempDB
  * __field__
  * __constructor__
  * __method__
