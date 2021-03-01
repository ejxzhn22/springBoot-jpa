package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext  => @Autowired 바꿀수 있음 , spring data jpa가 지원해줌
    // 그래서 @RequiredArgsConstructor를 쓸수 있음.
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id); //member를 반환
    }

    public List<Member> findAll() {
        //jpql 엔티티 객체를 대상으로 쿼리를 동작
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
