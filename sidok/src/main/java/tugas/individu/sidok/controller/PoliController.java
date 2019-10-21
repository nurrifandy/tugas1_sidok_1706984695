package tugas.individu.sidok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.model.JadwalModel;
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

    @GetMapping(value="poli/tambah")
    public String poliForm(Model model){
        PoliModel newPoli = new PoliModel();
        model.addAttribute("poli", newPoli);
        return "form-create-poli";
    }

    @PostMapping(value = "/poli/tambah")
    public String addPoli(@ModelAttribute PoliModel poli, Model model){
        poliService.addPoli(poli);
        model.addAttribute("poli", poli);
        return "create-poli-success";
    }

    @GetMapping(value = "/poli/dokter/{idPoli}")
    public String displayDokterInPoli(@PathVariable Long idPoli, Model model){
        PoliModel poli = poliService.getPoliById(idPoli).orElse(null);
        List<JadwalModel> dokter = poli.getListDokter();
        model.addAttribute("dokter", dokter);
        return "display-dokter-in-poli";
    }
}