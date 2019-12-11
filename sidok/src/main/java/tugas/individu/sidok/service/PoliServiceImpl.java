package tugas.individu.sidok.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.individu.sidok.model.PoliModel;
import tugas.individu.sidok.repository.PoliDb;

@Service
@Transactional
public class PoliServiceImpl implements PoliService{
    @Autowired
    private PoliDb poliDb;

    @Override
    public List<PoliModel> getListPoli(){
        return poliDb.findAll();
    }

    @Override
    public void addPoli(PoliModel poli){
        poliDb.save(poli);
    }

    @Override
    public Optional<PoliModel> getPoliById(Long idPoli){
        return poliDb.findById(idPoli);
    }

    @Override
    public Optional<PoliModel> updateDataPoli(PoliModel poli){
        PoliModel currPoli = poliDb.findById(poli.getIdPoli()).orElse(null);
        try{
            currPoli.setNamaPoli(poli.getNamaPoli());
            currPoli.setLokasi(poli.getLokasi());
            poliDb.save(currPoli);
            return Optional.of(currPoli);
        }catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deletePoli(PoliModel poli){
        poliDb.delete(poli);
    }
}