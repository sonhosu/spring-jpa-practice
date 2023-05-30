package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest //spring 컨테이너 내부에서 돌리기위해 필요함
@Transactional  // Test가 끝나면 자체적으로 DB rollback.  Service 에 붙어있는 것과 기능이 다름.
public class MemberServiceTest
{

    @Autowired
    MemberService memberService;
    MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception{

        // given
        Member member = new Member();
        member.setName("kim");

        System.out.println("=====1======");
        //when
        Long savedId = memberService.join(member);
        System.out.println("======2=====");
        System.out.println("savedId:"+savedId);
        //then
        System.out.println("member:"+member);
        System.out.println("memberRepository.findOne(savedId):"+memberRepository.findOne(savedId));


        assertEquals(member, memberRepository.findOne(savedId));
        System.out.println("======3=====");
    }




    @Test(expected = IllegalStateException.class)
    public void 중복예외() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);


        //then
        fail("예외가 발생해야함");

    }
}