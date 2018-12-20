//package boojongmin.localsearch.config;
//
//import boojongmin.localsearch.domain.Member;
//import boojongmin.localsearch.repository.MemberRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InitComponent implements CommandLineRunner {
//    private MemberRepository repository;
//    private PasswordEncoder encoder;
//
//    public InitComponent(MemberRepository repository, PasswordEncoder encoder) {
//        this.repository = repository;
//        this.encoder = encoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String password = encoder.encode("password");
//        Member member = Member.builder().id(0L).userId("user").password(password).name("유저").build();
//        repository.save(member);
//
//    }
//}
