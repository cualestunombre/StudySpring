package hello.demo.repository;

import hello.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{
    private Map<Integer,Member> repo;
    public MemoryMemberRepository(){
        this.repo = new HashMap<Integer,Member>();
    }
    @Override
    public Member save(Member member) {
        this.repo.put(member.getId(), member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(this.repo.values());
    }

    @Override
    public Member findById(int id)  {
        return repo.get(id);
    }
}
