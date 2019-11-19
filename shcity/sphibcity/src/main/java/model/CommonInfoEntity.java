package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public class CommonInfoEntity {
    @Column(name = "POPULATION")
    private Integer population;

    @Column(name = "SQUARE")
    private Double square;
}
