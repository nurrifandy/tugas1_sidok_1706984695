package tugas.individu.sidok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.individu.sidok.model.JadwalModel;
import tugas.individu.sidok.model.PoliModel;

@Repository
public interface JadwalDb extends JpaRepository<JadwalModel, Long>{
    List<JadwalModel> findByPoli(PoliModel poli);
}