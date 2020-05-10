package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    /**
     *   회원 가입
     */
    @Transactional          // 트랜잭션이 default로 되어 있는 것이 우선권을 가져서 readOnly 옵션이 false로 들어감
    public Long join(Member member) {
        validateDuplicateMember(member.getName());      // 회원 명 중복 유효성 검사
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(String name) {
        if(!memberRepository.findMemberByName(name).isEmpty()) {
            throw new IllegalStateException("회원 명이 이미 존재합니다.");
        }
    }

    /**
     *  회원 기본키를 기준으로 조회
     */
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    /**
     *  회원 전체 조회
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     *  회원 이름을 기준으로 조회
     */
    public List<Member> findMemberByName(String name) {
        return memberRepository.findMemberByName(name);
    }

}
