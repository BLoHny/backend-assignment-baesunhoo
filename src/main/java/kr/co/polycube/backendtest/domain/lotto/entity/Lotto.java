package kr.co.polycube.backendtest.domain.lotto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lotto {

    @Id
    @Column(name = "lottoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number1;

    private int number2;

    private int number3;

    private int number4;

    private int number5;

    private int number6;
}
