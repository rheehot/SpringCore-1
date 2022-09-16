package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);


        //threadA: A사용자가 10000원을 주문
        int userPrice= statefulService.order("userA", 10000);
        //threadB : B사용자가 20000원을 주문
        int userBPrice= statefulService2.order("userB", 20000);

        //ThreadA : 사용자a 주문 금액 조회
        //int price = statefulService.getPrice();
        //System.out.println("price = " + price);


        statefulService.setName("userA");
        statefulService2.setName("userB");

        System.out.println("statefulService = " + statefulService.getName());


        assertThat(userBPrice).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }

}