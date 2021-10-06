package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드

//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기서 문제
//    }
    // 상태를 유지하는 필드를 만들지 말고 주문과 동시에 주문금액 return
    public int unStatefulOrder(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        return price;
    }

//    public int getPrice(){
//        return price;
//    }


}
