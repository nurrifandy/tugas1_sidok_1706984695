package tugas.individu.sidok.service;

import java.util.List;

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
}