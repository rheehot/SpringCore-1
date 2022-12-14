package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleTonTest {
    
    
    
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService = appConfig.memberService();
        
        //2.조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        
        //참조값이 다른 것을 확인

        System.out.println("memberService = " + memberService);
        System.out.println("memberService1 = " + memberService1);
        
        
        //memberService 1 은 그냥이랑 달라야됨
        Assertions.assertThat(memberService1).isNotEqualTo(memberService);




    }



    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleTonService(){
        SingleTonService singleTonService = SingleTonService.getInstance();
        SingleTonService singleTonService1 = SingleTonService.getInstance();


        System.out.println("singleTonService1 = " + singleTonService1);
        System.out.println("singleTonService = " + singleTonService);


        Assertions.assertThat(singleTonService).isSameAs(singleTonService1);

        //same ==
        //.Equals메서드랑 똑같은게 isEqual


    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


//        AppConfig appConfig = new AppConfig();
//
//        //1. 조회: 호출할 때 마다 객체를 생성
//        MemberService memberService = appConfig.memberService();
//
          MemberService memberService = ac.getBean("memberService", MemberService.class);
          MemberService memberService1 = ac.getBean("memberService", MemberService.class);

//        //2.조회 : 호출할 때 마다 객체를 생성
//        MemberService memberService1 = appConfig.memberService();
//
//        //참조값이 다른 것을 확인
//
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService1 = " + memberService1);
//
//
//        //memberService 1 은 그냥이랑 달라야됨
//        Assertions.assertThat(memberService1).isNotEqualTo(memberService);
        Assertions.assertThat(memberService).isSameAs(memberService1);



    }



}
