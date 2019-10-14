package tugas.individu.sidok.service;

import java.util.List;
import java.util.Optional;

import tugas.individu.sidok.model.DokterModel;

public interface DokterService{

    void addDokter(DokterModel dokter);

    List<DokterModel> getDokterList();

    Optional<DokterModel> findDokterById(Long idDokter);

    List<DokterModel> findDokterByNik(String nikDokter);

    DokterModel updateDataDokter(DokterModel dokter);

    void deleteDokter(DokterModel dokter);
}