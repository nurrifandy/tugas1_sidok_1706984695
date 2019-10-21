package tugas.individu.sidok.service;

import java.util.List;
import java.util.Optional;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.model.JadwalModel;
import tugas.individu.sidok.model.PoliModel;
import tugas.individu.sidok.model.SpesialisasiModel;

public interface DokterService{

    void addDokter(DokterModel dokter);

    List<DokterModel> getDokterList();

    Optional<DokterModel> findDokterById(Long idDokter);

    Optional<DokterModel> getDokterByNik(String nikDokter);

    Optional<DokterModel> getDokterByNip(String nipDokter);

    List<DokterModel> getDokterBySpesialisasiAndJadwal(List<SpesialisasiModel> spesialisasi);

    DokterModel updateDataDokter(DokterModel dokter);

    void deleteDokter(DokterModel dokter);
}