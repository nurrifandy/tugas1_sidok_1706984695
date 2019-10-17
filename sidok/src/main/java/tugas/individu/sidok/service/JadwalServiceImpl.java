package tugas.individu.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.individu.sidok.model.JadwalModel;
import tugas.individu.sidok.repository.JadwalDb;

@Service
@Transactional
public class JadwalServiceImpl implements JadwalService{
    @Autowired
    private JadwalDb jadwalDb;

    @Override
    public void addJadwal(JadwalModel jadwal){
        jadwalDb.save(jadwal);
    }
}