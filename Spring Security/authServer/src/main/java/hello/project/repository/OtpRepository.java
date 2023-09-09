package hello.project.repository;

import hello.project.domain.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp,String> {
    Optional<Otp> findOtpByName(String name);
}