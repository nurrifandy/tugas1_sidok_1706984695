package tugas.individu.sidok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.repository.DokterDb;

@Service
@Transactional

public class DokterServiceImpl implements DokterService{
    @Autowired
    private DokterDb dokterDb;

    @Override
    public void addDokter(DokterModel dokter){
        dokterDb.save(dokter);
    }
    
    @Override
    public List<DokterModel> getDokterList(){
        return dokterDb.findAll();
    }

}