package com.home.sphibcity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import com.sun.xml.internal.ws.message.stream.StreamHeader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "COUNTRY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryEntity extends CommonInfoEntity implements Serializable {

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


    public CountryEntity(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        /*List<Integer>cityIds1 = new ArrayList<>();
        for (CityEntity cityEntity: that.cityList) {
            cityIds1.add(cityEntity.getId());
        }*/

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                currency == that.currency &&
                language == that.language &&
                Objects.equals(description, that.description);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, currency, language, description);
    }
}
