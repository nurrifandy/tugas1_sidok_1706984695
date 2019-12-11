package tugas.individu.sidok.controller;

import java.util.ArrayList;
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
import tugas.individu.sidok.service.DokterService;
import tugas.individu.sidok.service.PoliService;

@Controller
public class PoliController{
    @Qualifier("poliServiceImpl")

    @Autowired
    private PoliService poliService;

    @Autowired
    private DokterService dokterService;

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
        return "redirect:/poli/view/" + poli.getIdPoli();
    }

    @GetMapping(value = "/poli/dokter/{idPoli}")
    public String displayDokterInPoli(@PathVariable Long idPoli, Model model){
        PoliModel poli = poliService.getPoliById(idPoli).orElse(null);
        List<JadwalModel> listDokter = poli.getListDokter();
            
        ArrayList<DokterModel> noDuplicateDokter = new ArrayList<DokterModel>();
        for(JadwalModel dokter : listDokter){
            DokterModel getDokter = dokterService.findDokterById(dokter.getIdDokter()).orElse(null);
            if(!(noDuplicateDokter.contains(getDokter))){
                noDuplicateDokter.add(getDokter);
            }
        }

        model.addAttribute("poli", poli);
        model.addAttribute("dokter", noDuplicateDokter);
        return "display-dokter-in-poli";
    }

    @GetMapping(value = "/poli/update/{idPoli}")
    public String updatePoliForm(@PathVariable Long idPoli, Model model)
    {
        PoliModel poli = poliService.getPoliById(idPoli).orElse(null);
        model.addAttribute("poli", poli);
        return "form-update-poli";
    }

    @PostMapping(value = "/poli/update/{idPoli}")
    public String updatePoli(@PathVariable Long idPoli, @ModelAttribute PoliModel poli, Model model)
    {
        PoliModel newPoli = poliService.updateDataPoli(poli).orElse(null);
        model.addAttribute("poli", newPoli);
        return "update-poli-sukses";
    }

    @GetMapping(value = "/poli/delete/{idPoli}")
    public String deletePoli(@PathVariable Long idPoli, Model model){
        PoliModel poli = poliService.getPoliById(idPoli).orElse(null);
        poliService.deletePoli(poli);
        model.addAttribute("poli", poli);
        return "delete-poli-sukses";
    }

    @GetMapping(value = "/poli/view/{idPoli}")
    public String viewPoli(@PathVariable Long idPoli, Model model){
        PoliModel poli = poliService.getPoliById(idPoli).orElse(null);
        model.addAttribute("poli", poli);
        return "create-poli-success";
    }
}