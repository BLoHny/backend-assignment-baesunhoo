package kr.co.polycube.backendtest.domain.lotto.repository;

import kr.co.polycube.backendtest.domain.lotto.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
