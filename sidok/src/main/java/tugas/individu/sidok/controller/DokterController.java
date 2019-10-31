package tugas.individu.sidok.controller;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tugas.individu.sidok.model.DokterModel;
import tugas.individu.sidok.model.JadwalModel;
import tugas.individu.sidok.model.PoliModel;
import tugas.individu.sidok.model.SpesialisasiModel;
import tugas.individu.sidok.service.DokterService;
import tugas.individu.sidok.service.JadwalService;
import tugas.individu.sidok.service.PoliService;
import tugas.individu.sidok.service.SpesialisasiService;

@Controller
public class DokterController{
    @Qualifier("dokterServiceImpl")

    @Autowired
    private DokterService dokterService;

    @Autowired
    private SpesialisasiService spesialisasiService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private JadwalService jadwalService;

    //Beranda Dokter
    @GetMapping(value = "/")
    public String home(Model model){
        List<DokterModel> listDokter = null;
        listDokter = dokterService.getDokterList();
        model.addAttribute("listDokter", listDokter);
        return "beranda";
    }

    //Method untuk melakukan add dokter
    @GetMapping(value = "/dokter/tambah")
    public String addDokterFormPage(Model model){
        DokterModel newDokter = new DokterModel();

        List<SpesialisasiModel> spesialisasiList = spesialisasiService.getSpesialisasiList();
        
        SpesialisasiModel spesialisasi = new SpesialisasiModel();
        ArrayList<SpesialisasiModel> listSpesialisasi = new ArrayList<SpesialisasiModel>();
        listSpesialisasi.add(spesialisasi);
        newDokter.setListSpesialisasi(listSpesialisasi);

        model.addAttribute("dokter", newDokter);
        model.addAttribute("spesialisasiList", spesialisasiList);
        return "form-create-dokter";
    }

    @PostMapping(value = "/dokter/tambah", params = {"addSpesialisasi"})
    public String addRow(@ModelAttribute DokterModel dokter, Model model){
        if(dokter.getListSpesialisasi() == null){
            dokter.setListSpesialisasi(new ArrayList<SpesialisasiModel>());
        }
        
        List<SpesialisasiModel> spesialisasiList = spesialisasiService.getSpesialisasiList();
        
        dokter.getListSpesialisasi().add(new SpesialisasiModel());
        
        model.addAttribute("spesialisasiList", spesialisasiList);
        model.addAttribute("dokter", dokter);
        return "form-create-dokter";
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

    public String makeNip(Date date, Boolean jenisKelamin){
        LocalDate currDate = LocalDate.now();
        String year = Integer.toString(currDate.getYear() + 5);
        DateFormat formatDate = new SimpleDateFormat("ddmmyy");
        String dateFormat = formatDate.format(date);
        String tmpJk = "2";
        if(jenisKelamin){
            tmpJk = "1";
        }
        String finalNip = year + dateFormat + tmpJk + makeRandomString();
        return finalNip;
    }

    @PostMapping(value = "/dokter/tambah", params = "save")
    public String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model){
        DokterModel saveDokter = new DokterModel();
        saveDokter.setNama(dokter.getNama());
        saveDokter.setNikDokter(dokter.getNikDokter());
        
        Date tmpTgl = dokter.getTanggalLahir();
        saveDokter.setNipDokter(makeNip(tmpTgl, dokter.getJenisKelamin()));

        saveDokter.setJenisKelamin(dokter.getJenisKelamin());
        saveDokter.setTempatLahir(dokter.getTempatLahir());
        saveDokter.setTanggalLahir(tmpTgl);
        List<DokterModel> setDokter = new ArrayList<DokterModel>();
        setDokter.add(saveDokter);
        if(dokter.getListSpesialisasi().get(0).getIdSpesialisasi() != null){
            saveDokter.setListSpesialisasi(dokter.getListSpesialisasi());
            for(SpesialisasiModel spesial  : dokter.getListSpesialisasi()){
                spesial.setListDokter(setDokter);
            }
        }
        dokterService.addDokter(saveDokter);
        model.addAttribute("dokter", saveDokter);
        return "create-dokter-sukses";
    }

    //menampilkan detail dokter
    @GetMapping(value = "/dokter")
    public String viewDokterByNik(
        @RequestParam (value = "nikDokter") String nikDokter,
        Model model
    ){
        DokterModel dokter = dokterService.getDokterByNik(nikDokter).orElse(null);
        model.addAttribute("dokter", dokter);
        return "display-detail-dokter";
    }

    //melakukan update terhadap data dokter
    @GetMapping(value="/dokter/update/{idDokter}")
    public String changeDokterDataForm(@PathVariable Long idDokter, Model model){
        DokterModel dokter = dokterService.findDokterById(idDokter).orElse(null);
        model.addAttribute("dokter", dokter);
        return "form-update-dokter";
    }

    @PostMapping(value = "/dokter/update/{idDokter}")
    public String changeDokter(@PathVariable Long idDokter, @ModelAttribute DokterModel dokter, Model model){
        dokter.setNipDokter(makeNip(dokter.getTanggalLahir(), dokter.getJenisKelamin()));
        DokterModel newDataDokter = dokterService.updateDataDokter(dokter);
        model.addAttribute("dokter", newDataDokter);
        return "redirect:/dokter?nikDokter=" + newDataDokter.getNikDokter();
    }

    @GetMapping(value = "/dokter/delete/{idDokter}")
    public String deleteDokter(@PathVariable Long idDokter, Model model){
        DokterModel dokter = dokterService.findDokterById(idDokter).orElse(null);
        dokterService.deleteDokter(dokter);
        model.addAttribute("dokter", dokter);
        return "delete-sukses";
    }

    @GetMapping(value="/cari")
    public String landingPageForSearch(Model model){
        List<PoliModel> poli = poliService.getListPoli();
        List<SpesialisasiModel> spesialisasi = spesialisasiService.getSpesialisasiList();
        //Boolean isLanding = false;
        //model.addAttribute("isLanding", isLanding);
        model.addAttribute("poli", poli);
        model.addAttribute("spesialisasi", spesialisasi);
        return "display-form-and-search";
    }

    @GetMapping(value = "/cari", params = {"search"})
    public String findDokterFromSpesialisasiAndPoli(
        @RequestParam(value="idSpesialisasi") Long idSpesialisasi,
        @RequestParam(value="idPoli") Long idPoli,
        Model model){
            List<DokterModel> listDokter = new ArrayList<DokterModel>();
            
            SpesialisasiModel spesialis = spesialisasiService.getSpesialisasiById(idSpesialisasi).orElse(null);
            List<DokterModel> dokterSpesialis = spesialis.getListDokter();

            PoliModel poli = poliService.getPoliById(idPoli).orElse(null);
            List<JadwalModel> dokterPoli = poli.getListDokter();
            
            for (JadwalModel jadwalDokter : dokterPoli){
                DokterModel getDokter = dokterService.findDokterById(jadwalDokter.getIdDokter()).orElse(null);
                if(dokterSpesialis.contains(getDokter) && !(listDokter.contains(getDokter))){
                    listDokter.add(getDokter);
                }
            }
            List<PoliModel> poliList = poliService.getListPoli();
            List<SpesialisasiModel> spesialisasi = spesialisasiService.getSpesialisasiList();
            model.addAttribute("dokter", listDokter);
            model.addAttribute("poli", poliList);
            model.addAttribute("spesialisasi", spesialisasi);
            return "display-form-and-search";
        }

        @GetMapping(value="/cari/tugas-terbanyak")
        public String formSearchDokterInPoli(
            Model model
        ){
            Boolean isLanding = false;
            List<PoliModel> poliList = poliService.getListPoli();
            model.addAttribute("poli", poliList);
            model.addAttribute("isLanding", isLanding);
            return "cari-terbanyak";
        }

        @GetMapping(value="/cari/tugas-terbanyak", params = {"search"})
        public String displayDokterInPoli(
            @RequestParam Long idPoli,
            Model model
        ){
            PoliModel poli = poliService.getPoliById(idPoli).orElse(null);
            List<JadwalModel> dokterPoli = poli.getListDokter();
            List<JadwalModel> tmp = dokterPoli;
            int count=0;
            int n = 0;
            Long idDokter = Long.valueOf(0);

            for(JadwalModel jadwal:dokterPoli){
                for(JadwalModel hitung:tmp){
                    if (jadwal.getIdDokter()==hitung.getIdDokter()){
                        count++;
                    }
                    
                }
                if(count>n){
                    n = count;
                    idDokter = jadwal.getIdDokter();
                }
                count = 0;
            }

            DokterModel detailDokter = dokterService.findDokterById(idDokter).orElse(null);

            List<PoliModel> poliList = poliService.getListPoli();
            //List<SpesialisasiModel> spesialisasi = spesialisasiService.getSpesialisasiList();
            Boolean isLanding = true;
            model.addAttribute("isLanding", isLanding);
            model.addAttribute("detailDokter", detailDokter);
            model.addAttribute("poli", poliList);
            //model.addAttribute("spesialisasi", spesialisasi);
            return "cari-terbanyak";
        }

        @GetMapping(value = "/spesialisasi")
        public String displayBonus(Model model){
            List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
            model.addAttribute("listSpesialisasi", listSpesialisasi);
            return "bonus-spesialisasi";
        }

}