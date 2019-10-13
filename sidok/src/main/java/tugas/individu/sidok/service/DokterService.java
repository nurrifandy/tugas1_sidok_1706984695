package tugas.individu.sidok.service;

import java.util.List;

import tugas.individu.sidok.model.DokterModel;

public interface DokterService{

    void addDokter(DokterModel dokter);

    List<DokterModel> getDokterList();
}