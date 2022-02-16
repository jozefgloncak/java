package gloncak.jozef.hibernate.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

/**
 * Specific native repository for reading relationship between person and employer table
 */
@Repository
public class EmployerPersonJoinTableRepository {

    @Autowired
    EntityManager em;

    public Integer countEmployerPersonJoinTable() {
        Object countObj = em.createNativeQuery("select count(*) from employeer_bidirect_persons").getSingleResult();
        if (countObj instanceof BigInteger count)
            return count.intValue();
        return 0;

    }
}
