package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberSelectionImpl implements MemberSelection {
    final private MemberRepository memberRepository;
    final private LanguageSelection languageSelection;
    @Autowired
    MemberSelectionImpl(MemberRepository memberRepository, @Qualifier("java") LanguageSelection languageSelection){
        this.memberRepository = memberRepository;
        this.languageSelection = languageSelection;
    }
    public void select(){
        List<Member> members = memberRepository.findAll();
        for (Member member : members){
            member.setLanguage(this.languageSelection.getCode());
        }
    }
}
