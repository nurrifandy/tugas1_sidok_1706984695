package tugas.individu.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tugas.individu.sidok.model.JadwalModel;

public interface JadwalDb extends JpaRepository<JadwalModel, Long>{
    
}