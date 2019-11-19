package dao;
import model.CityEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CityDaoImpl implements CityDao {
    @PersistenceContext
    private EntityManager entityManager;

   @Override
    public void updateCityPopulation(int cityId, int cityPopulation) {
        entityManager.createNamedStoredProcedureQuery("update_city_population")
                .setParameter("p_id", cityId)
                .setParameter("p_population", cityPopulation).execute();
    }


    @Override
    public void save(CityEntity entity) {
        if (Objects.isNull(entity)) throw new IllegalArgumentException("Please set a city");
        if (Objects.isNull(entity.getId())) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }


    @Override
    public Collection<CityEntity> findAll() {
    Collection<CityEntity> cityEntities=
            entityManager.createNativeQuery(
                    "SELECT NAME"+
                            "SQUARE"+
                            "TYPE"+
                            "CAPITAL"+
                            "POPULATION"+
                            "COUNTRY_FK_ID"+
                            "FROM CITY",
                    CityEntity.class).getResultList();

        return null;
    }

    @Override
    public Optional<CityEntity> findById(Integer id) {
       final CityEntity cityEntity=
               entityManager.find(CityEntity.class, id);
       if(Objects.isNull(cityEntity))throw new NullPointerException("City was not found");
        return Optional.empty();
    }

    @Override
    public void delete(CityEntity entity) {
if(Objects.isNull(entity))throw new NullPointerException("city must be set");
entityManager.remove(entity);
    }
}

