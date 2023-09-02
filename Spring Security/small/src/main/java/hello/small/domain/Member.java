package hello.small.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "member",fetch=FetchType.LAZY)
    private List<Authority> authorities = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "token_id")
    private Token token;

}
