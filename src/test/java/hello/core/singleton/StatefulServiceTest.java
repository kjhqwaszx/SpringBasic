package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: 사용자A가 10000원 주문
//        statefulService1.order("userA", 10000);
        int userA = statefulService1.unStatefulOrder("userA", 10000);

        //ThreadB: 사용자B가 20000원 주문
//        statefulService2.order("userA", 20000);
        int userB = statefulService2.unStatefulOrder("userA", 10000);

        //ThreadA: 사용자A가 주문 금액 조회
//        statefulService1.getPrice();

        //사용자A는 10000원을 주문했는데 20000원이 나온다.
//        assertThat(statefulService1.getPrice()).isEqualTo(10000);
        assertThat(userA).isEqualTo(10000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
