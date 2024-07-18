package kr.co.polycube.backendtest.global.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LottoRank {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOUR(4),
    FIVE(5),
    NOTHING(-1);

    private final int rank;
}
