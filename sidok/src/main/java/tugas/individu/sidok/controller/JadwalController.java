package tugas.individu.sidok.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.model.JadwalModel;
import tugas.individu.sidok.model.PoliModel;
import tugas.individu.sidok.service.DokterService;
import tugas.individu.sidok.service.JadwalService;
import tugas.individu.sidok.service.PoliService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class JadwalController{
    @Autowired
    private JadwalService jadwalService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private DokterService dokterService;

    @GetMapping(value = "/jadwal/tambah/{nipDokter}")
    public String addJadwalFormPage(@PathVariable String nipDokter, Model model){
        DokterModel dokter = dokterService.getDokterByNip(nipDokter).orElse(null);

        List<JadwalModel> jadwal = dokter.getListPoli();

        List<PoliModel> poli = poliService.getListPoli();
        
        model.addAttribute("dokter", dokter);
        model.addAttribute("jadwal", jadwal);
        model.addAttribute("poli", poli);
        return "form-add-jadwal-poli";
    }

    @PostMapping(value="jadwal/tambah/{nipDokter}")
    public String postMethodForSaveJadwal(@PathVariable String nipDokter,HttpServletRequest request , Model model) {
        //TODO: process POST request
        DokterModel dokter = dokterService.getDokterByNip(nipDokter).orElse(null);
        PoliModel poli = poliService.getPoliById(Long.valueOf(request.getParameter("poli"))).orElse(null);
        String hari = request.getParameter("hari");

        JadwalModel jadwalDokter = new JadwalModel();
        jadwalDokter.setDokter(dokter);
        jadwalDokter.setHari(hari);
        jadwalDokter.setPoli(poli);
        jadwalDokter.setIdPoli(poli.getIdPoli());
        jadwalDokter.setIdDokter(dokter.getIdDokter());

        if(poli.getListDokter() == null){
            poli.setListDokter(new ArrayList<JadwalModel>());
        }

        poli.getListDokter().add(jadwalDokter);
        dokter.getListPoli().add(jadwalDokter);
        jadwalService.addJadwal(jadwalDokter);

        model.addAttribute("dokter", dokter);
        model.addAttribute("poli", poli);
        model.addAttribute("hari", hari);
        return "save-jadwal-sukses";
    }
    
}