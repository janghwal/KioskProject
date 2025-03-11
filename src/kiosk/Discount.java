package kiosk;

//할인 분류 국가유공자 10%, 군인 5%, 학샐 3%, 일반
public enum Discount {
    NATIONAL_MERIT_RECIPIENT(price -> (price * 90 / 100)),
    MILITARY_PERSONNEL(price -> (price * 95 / 100)),
    STUDENT(price -> (price * 97 / 100)),
    GENERAL(price -> (price));

    private final DistountInterface discount;

    Discount(DistountInterface discount){
        this.discount = discount;
    }

    public double apply(double price){
        return discount.priceDiscount(price);
    }
}
