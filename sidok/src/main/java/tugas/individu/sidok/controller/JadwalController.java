package tugas.individu.sidok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.model.JadwalModel;
import tugas.individu.sidok.model.PoliModel;
import tugas.individu.sidok.service.DokterService;
import tugas.individu.sidok.service.JadwalService;
import tugas.individu.sidok.service.PoliService;

@Controller
public class JadwalController{
    @Autowired
    private JadwalService jadwalService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private DokterService dokterService;

    @GetMapping(value = "/jadwal/tambah/{nipDokter}")
    private String addJadwalFormPage(@PathVariable String nipDokter, Model model){
        DokterModel dokter = dokterService.getDokterByNip(nipDokter).orElse(null);

        List<JadwalModel> jadwal = dokter.getListPoli();

        List<PoliModel> poli = poliService.getListPoli();
        
        model.addAttribute("dokter", dokter);
        model.addAttribute("jadwal", jadwal);
        model.addAttribute("poli", poli);
        return "form-add-jadwal-poli";
    }
}