package boojongmin.localsearch.service;

import boojongmin.localsearch.domain.Member;
import boojongmin.localsearch.repository.MemberRepository;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(BeanIds.USER_DETAILS_SERVICE)
public class UserService implements UserDetailsService {
    private MemberRepository repository;

    public UserService(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserId(username).map(Member::getUserDetails).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
