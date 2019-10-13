package tugas.individu.sidok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PoliController{

    @GetMapping(value = "/poli")
    public String viewPoli(){
        return "viewPoli";
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