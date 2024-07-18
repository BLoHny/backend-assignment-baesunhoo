package kr.co.polycube.backendtest.domain.lotto.repository;

import kr.co.polycube.backendtest.domain.lotto.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository extends JpaRepository<Lotto, Long> {
}
