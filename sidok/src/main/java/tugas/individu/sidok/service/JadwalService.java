package tugas.individu.sidok.service;

import java.util.List;

import tugas.individu.sidok.model.JadwalModel;
import tugas.individu.sidok.model.PoliModel;

public interface JadwalService{
    void addJadwal(JadwalModel jadwal);

    List<JadwalModel> getJadwalByModel(PoliModel poli);
}