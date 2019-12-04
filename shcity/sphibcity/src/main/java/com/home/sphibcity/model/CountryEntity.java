package com.home.sphibcity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "COUNTRY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryEntity extends CommonInfoEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_country")
    @SequenceGenerator(name = "gen_country", sequenceName = "country_sequence", allocationSize = 1)
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

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryEntity")
    private List<CityEntity> cityList = new ArrayList<>();
}
