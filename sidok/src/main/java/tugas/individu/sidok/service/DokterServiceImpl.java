package tugas.individu.sidok.service;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.model.SpesialisasiModel;
import tugas.individu.sidok.model.JadwalModel;
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

    public String makeRandomString(){
        int n = 2;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom random = new SecureRandom();

        StringBuilder string = new StringBuilder(n);

        for(int i=0 ; i<n ; i++){
            int randomCharAt = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(randomCharAt);
            string.append(randomChar);
        }
        return string.toString();
    }

    public String buildNip(Date date, Boolean jenisKelamin){
        String year = Integer.toString(date.getYear() + 1900 + 5);
        DateFormat formatDate = new SimpleDateFormat("ddmmyy");
        String dateFormat = formatDate.format(date);
        String tmpJk = "2";
        if(jenisKelamin){
            tmpJk = "1";
        }
        String finalNip = year + dateFormat + tmpJk + makeRandomString();
        return finalNip;
    }

    @Override
    public DokterModel updateDataDokter(DokterModel dokter){
        DokterModel currentDokter = dokterDb.findById(dokter.getIdDokter()).orElse(null);
        
        Date tmpDate = dokter.getTanggalLahir();
        Boolean tmpJk = dokter.getJenisKelamin();
        String nip = buildNip(tmpDate, tmpJk);
        
        try{
            currentDokter.setNama(dokter.getNama());
            currentDokter.setNikDokter(dokter.getNikDokter());
            currentDokter.setTanggalLahir(dokter.getTanggalLahir());
            currentDokter.setTempatLahir(dokter.getTempatLahir());
            currentDokter.setNipDokter(nip);
            currentDokter.setJenisKelamin(dokter.getJenisKelamin());
            dokterDb.save(currentDokter);
            return currentDokter;
        }catch (NullPointerException nullException){
            return null;
        }
    }

}