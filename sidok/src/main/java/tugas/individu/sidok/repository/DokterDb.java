package tugas.individu.sidok.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.model.JadwalModel;
import tugas.individu.sidok.model.PoliModel;
import tugas.individu.sidok.model.SpesialisasiModel;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long>{
    Optional<DokterModel> findById(Long id);
    Optional<DokterModel> findByNikDokter(String nikDokter);
    Optional<DokterModel> findByNipDokter(String nipDokter);
    List<DokterModel> findByListSpesialisasi(List<SpesialisasiModel> listSpesialisasi);
}