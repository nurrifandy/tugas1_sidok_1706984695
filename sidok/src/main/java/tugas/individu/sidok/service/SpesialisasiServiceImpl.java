package tugas.individu.sidok.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.individu.sidok.model.SpesialisasiModel;
import tugas.individu.sidok.repository.SpesialisasiDb;

@Service
@Transactional

public class SpesialisasiServiceImpl implements SpesialisasiService{
    @Autowired
    private SpesialisasiDb spesialisasiDb;

    @Override
    public List<SpesialisasiModel> getSpesialisasiList(){
        return spesialisasiDb.findAll();
    }

    @Override
    public Optional<SpesialisasiModel> getSpesialisasiById(Long idSpesialisasi){
        return spesialisasiDb.findById(idSpesialisasi);
    }
}