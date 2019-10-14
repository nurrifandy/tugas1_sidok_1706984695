package tugas.individu.sidok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import tugas.individu.sidok.model.PoliModel;
import tugas.individu.sidok.service.PoliService;

@Controller
public class PoliController{
    @Qualifier("poliServiceImpl")

    @Autowired
    private PoliService poliService;

    @GetMapping(value = "/poli")
    public String viewPoli(Model model){
        List<PoliModel> listPoli = poliService.getListPoli();
        model.addAttribute("listPoli", listPoli);
        return "display-poli";
    }

    @PostMapping(value = "/poli/tambah")
    public String addPoli(){
        return "addPoli";
    }

    @PostMapping(value = "/jadwal/tambah/{nipDokter}")
    public String addJadwal(Model model){
        return "addJadwal";
    }

    @GetMapping(value = "/poli/dokter/{idPoli}")
    public String viewDokterPoli(Model model){
        return "viewDokterPoli";
    }

}