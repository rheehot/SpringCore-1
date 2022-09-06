package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig1 = new AppConfig();
        memberService = appConfig1.memberService();
        orderService = appConfig1.orderService();
    }

    @Test
    void createOrder(){
        Long memberId=1L;
        Member member= new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order= orderService.createOrder(memberId, "itemA", 10000);  // NPE떳던 이유 : repository에 있던 map의 store가 static이 아니었음.

        //static이 아닐 경우  -> 인스턴스가 새로 생겨서 결론적으로는 같은 값이긴 하지만 인스턴스가 다르기때문에 join했을때 새로운 인스턴스를 생성한다.




        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }
}
