package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
    /**
     * DI(Dependency Injection) : 의존성 주입
     * AppConfig에서 의존성을 주입하면서 DIP, OCP를 만족시킨다.
     * 각 서비스들(orderService, MemberService)에서 사용하는 구현객체들은(Rate or Fix or MemoryMember) AppConfig에서 설정해준다.
     *
     */

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    // 회원 저장방법 선택
    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    // 할인정책 선택
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
