package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("김도영");

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));

    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("김도영");
        member2.setName("김도영");

        // when
        Long saveMember1Id = memberService.join(member1);
        Long saveMember2Id = memberService.join(member2);

        // then
        fail("에러가 발생해야 한다.");

    }
}