package tugas.individu.sidok.service;

import java.util.List;

import tugas.individu.sidok.model.PoliModel;

public interface PoliService{

    void addPoli(PoliModel poli);
    
    List<PoliModel> getListPoli();
}