package hello.project.service;

import hello.project.domain.Member;
import hello.project.domain.Otp;
import hello.project.repository.MemberRepository;
import hello.project.repository.OtpRepository;
import hello.project.util.GenerateCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final OtpRepository otpRepository;

    public void addMember(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public void auth(Member member){
        Optional<Member> o =
                memberRepository.findMemberByName(member.getName());

        if(o.isPresent()){
            Member m = o.get();
            if(passwordEncoder.matches(
                    member.getPassword(),
                    m.getPassword()
            )){
                renewOtp(m);
            }else{
                throw new BadCredentialsException("Bad credentials");
            }

        }else{
            throw new BadCredentialsException("Bad credentials");
        }
    }

    public boolean check(Otp otpToValidate){
        Optional<Otp> memberOtp =
                otpRepository.findOtpByName(
                        otpToValidate.getName()
                );
        if(memberOtp.isPresent()){
            Otp otp = memberOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) return true;
        }

        return false;

    }

    private void renewOtp(Member m){
        String code = GenerateCodeUtil
                .generateCode();

        Optional<Otp> memberOtp =
                otpRepository.findOtpByName(m.getName());

        if(memberOtp.isPresent()){
            Otp otp = memberOtp.get();
            otp.setCode(code);
        }else{
            Otp otp = new Otp();
            otp.setName(m.getName());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }
}
