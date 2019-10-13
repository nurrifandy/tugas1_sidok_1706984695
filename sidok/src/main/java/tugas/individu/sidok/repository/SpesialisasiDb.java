package tugas.individu.sidok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.individu.sidok.model.SpesialisasiModel;

@Repository
public interface SpesialisasiDb extends JpaRepository<SpesialisasiModel, Long>{
    
}