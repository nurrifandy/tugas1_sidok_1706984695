package tugas.individu.sidok.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.model.SpesialisasiModel;
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

    @Override
    public Optional<DokterModel> getDokterByNik(String nikDokter){
        return dokterDb.findByNikDokter(nikDokter);
    }

    @Override
    public Optional<DokterModel> getDokterByNip(String nipDokter){
        return dokterDb.findByNipDokter(nipDokter);
    }

    @Override
    public Optional<DokterModel> findDokterById(Long idDokter){
        return dokterDb.findById(idDokter);
    }
    
   
    @Override
    public List<DokterModel> getDokterBySpesialisasiAndJadwal(List<SpesialisasiModel> listSpesialisasi){
        return dokterDb.findByListSpesialisasi(listSpesialisasi);
    }
    @Override
    public void deleteDokter(DokterModel dokter){
        dokterDb.delete(dokter);
    }

    @Override
    public DokterModel updateDataDokter(DokterModel dokter){
        DokterModel currentDokter = dokterDb.findById(dokter.getIdDokter()).orElse(null);
                
        try{
            currentDokter.setNama(dokter.getNama());
            currentDokter.setNikDokter(dokter.getNikDokter());
            currentDokter.setTanggalLahir(dokter.getTanggalLahir());
            currentDokter.setTempatLahir(dokter.getTempatLahir());
            currentDokter.setNipDokter(dokter.getNipDokter());
            currentDokter.setJenisKelamin(dokter.getJenisKelamin());
            dokterDb.save(currentDokter);
            return currentDokter;
        }catch (NullPointerException nullException){
            return null;
        }
    }

}