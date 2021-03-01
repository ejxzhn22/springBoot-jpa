package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
//@AllArgsConstructor 생성자를 만들어줌
@RequiredArgsConstructor // final이 붙은 필드만 가지고 생성자를 만들어줌
public class MemberService {
    // 테스트 할때 힘듬
    private final MemberRepository memberRepository;

/*
    @Autowired //어노테이션없어도 생성자가 하나라면 알아서 인젝션을 해줌. 생략가능
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
*/

    //애플리케이션이 동작할때 변경할 필요 없음. 따라서 set사용 x
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    //회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
