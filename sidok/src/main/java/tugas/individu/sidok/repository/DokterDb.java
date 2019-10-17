package tugas.individu.sidok.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.individu.sidok.model.DokterModel;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long>{
    Optional<DokterModel> findById(Long id);
    Optional<DokterModel> findByNikDokter(String nikDokter);
    Optional<DokterModel> findByNipDokter(String nipDokter);
}