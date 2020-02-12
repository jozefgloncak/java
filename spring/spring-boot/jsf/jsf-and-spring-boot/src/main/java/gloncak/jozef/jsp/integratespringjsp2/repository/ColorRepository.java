package gloncak.jozef.jsp.integratespringjsp2.repository;

import gloncak.jozef.jsp.integratespringjsp2.model.Color;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends CrudRepository<Color, Integer> {
}
