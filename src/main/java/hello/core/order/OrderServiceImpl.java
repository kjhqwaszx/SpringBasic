package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    /**
     * DIP 위반. (추상(Interface)에만 의존해야한다.)
     *   OrderServiceImple이 추상(DiscountPolicy[interface]), 구현체(Fix~ or Rate~) 둘다에 의존한다.
     *
     * OCP 위반. (구현 객체를 변경하더라도 클라이언트 코드를 수정하면 안된다.( = 확장에는 열려있고 변경에는 닫혀있어야 한다.)
     *   고정할인에서 정률할인으로 변경 시 new Fix~() 에서 new Rate~()로 소스 변경을 해주어야함.
     *
     */

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * AppConf에서 생성자를 통해 의존성을 주입해주기때문에 DIP, OCP 만족.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    // @RequiredArgsConstructor 로 생략가능
//    @Autowired //의존성 자동 주입 MemberRepository, DiscountPolicy 타입을 찾아서 자동으로 주입해준다. ( MemoryMemberRepository, RateDiscountPolicy가 된다.)
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId) ;
        int discountPrice = discountPolicy.discount(member, itemPrice);
        Order order = new Order(memberId, itemName, itemPrice,discountPrice);

        return order;
    }

    //Configuration 싱글톤 테스트용
   public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
