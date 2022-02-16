package gloncak.jozef.hibernate.repo;

import gloncak.jozef.hibernate.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {
}
