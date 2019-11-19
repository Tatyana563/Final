package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.enumeration.Type;

import javax.persistence.*;

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
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="CITY")
public class CityEntity extends CommonInfoEntity {
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COUNTRY_ID")
    private CountryEntity countryEntity;
}
