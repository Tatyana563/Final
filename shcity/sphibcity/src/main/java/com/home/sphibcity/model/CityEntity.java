package com.home.sphibcity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.home.sphibcity.model.enumeration.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "changePopulation",
                procedureName = "update_city_population",
                parameters = {
                        @StoredProcedureParameter(name = "p_id",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_population",
                                type = Integer.class,
                                mode = ParameterMode.IN)
                }
        )
)

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="CITY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityEntity extends CommonInfoEntity implements Serializable {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_city")
    @SequenceGenerator(name="seq_city", sequenceName = "city_sequence", allocationSize = 1)
private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

@Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private Type type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CAPITAL")
    private Boolean capital;

    @Column(name = "COUNTRY_ID", insertable = false, updatable = false)
    private Integer countryId;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "COUNTRY_ID")
    private CountryEntity countryEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(description, that.description) &&
                Objects.equals(capital, that.capital) &&
                countryEntity != null ?
                    Objects.equals(countryEntity.getId(), that.countryEntity != null ? that.countryEntity.getId() : null)
                    : countryEntity == that.countryEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description, capital, countryId);
    }
}
