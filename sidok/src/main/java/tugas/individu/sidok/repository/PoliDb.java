package tugas.individu.sidok.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.individu.sidok.model.PoliModel;

@Repository
public interface PoliDb extends JpaRepository<PoliModel, Long>{
    Optional<PoliModel> findById(Long id);
}