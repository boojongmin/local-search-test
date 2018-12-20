package boojongmin.localsearch.repository;

import boojongmin.localsearch.domain.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
}
