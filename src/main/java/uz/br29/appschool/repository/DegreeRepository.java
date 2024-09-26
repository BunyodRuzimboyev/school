package uz.br29.appschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.br29.appschool.entity.Degree;

import java.util.UUID;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, UUID> {

}
