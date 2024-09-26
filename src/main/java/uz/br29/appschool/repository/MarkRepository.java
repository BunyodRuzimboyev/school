package uz.br29.appschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.br29.appschool.entity.Mark;

import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<Mark, UUID> {

}
