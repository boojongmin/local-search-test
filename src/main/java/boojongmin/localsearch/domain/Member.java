package boojongmin.localsearch.domain;

import lombok.*;

import javax.persistence.*;

//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;

// jpa
@Entity
@Table(name = "MEMBER", indexes = {@Index(name = "idx_user_id", columnList = "userId", unique = true)})
// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Member {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private String name;

//    public UserDetails getUserDetails() {
//        return User.withUsername(this.userId)
//                .password(password)
//                .authorities("USER")
//                .build();
//    }
}
