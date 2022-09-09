package com.shopping.repository;

import com.shopping.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository{

    private final EntityManager em;

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }

    @Override
    public List<String> findByUserId(String userId) {
        return em.createQuery("select m.userId from Member as m where m.userId = :userId", String.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
