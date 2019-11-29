package com.home.sphibcity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;

@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="COUNTRY")
public class CountryEntity extends CommonInfoEntity {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_country")
@SequenceGenerator(name="gen_country", sequenceName = "country_sequence", allocationSize = 1)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY")
    private Currency currency;


    @Enumerated(EnumType.STRING)
    @Column(name = "LANGUAGE")
    private Language language;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryEntity")
    private List<CityEntity> cityList = new ArrayList<>();
}
