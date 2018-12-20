package boojongmin.localsearch;

import boojongmin.localsearch.domain.Member;
import boojongmin.localsearch.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCircuitBreaker
@EnableRedisRepositories
public class Application {

    // 어플리케이션 시작 시점에 사용자 생성 요구사항 적용
    @Bean
    CommandLineRunner memberInit(MemberRepository repository, PasswordEncoder encoder) {
        return args -> {
            String password = encoder.encode("password");
            Member member = Member.builder().id(0L).userId("user").password(password).name("유저").build();
            repository.save(member);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

