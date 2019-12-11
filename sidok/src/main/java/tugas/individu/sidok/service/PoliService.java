package tugas.individu.sidok.service;

import java.util.List;
import java.util.Optional;

import tugas.individu.sidok.model.PoliModel;

public interface PoliService{

    void addPoli(PoliModel poli);

    Optional<PoliModel> getPoliById(Long idPoli);
    
    List<PoliModel> getListPoli();

    Optional<PoliModel> updateDataPoli(PoliModel poli);

    void deletePoli(PoliModel poli);
}