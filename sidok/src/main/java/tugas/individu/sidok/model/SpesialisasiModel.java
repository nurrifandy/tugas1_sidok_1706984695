package tugas.individu.sidok.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import tugas.individu.sidok.model.DokterModel;

@Entity
@Table(name = "spesialisasi")

public class SpesialisasiModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpesialisasi;

    @NotNull
    @Size(max = 255)
    @Column(name = "namaSpesialisasi", nullable = false)
    private String namaSpesialisasi;

    @NotNull
    @Size(max = 255)
    @Column(name = "gelarSpesialisasi", nullable = false)
    private String gelarSpesialisasi;

    @ManyToMany(mappedBy = "listSpesialisasi", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private List<DokterModel> listDokter;

    //getter setter idSpesialisasi
    public Long getIdSpesialisasi(){
        return this.idSpesialisasi;
    }

    public void setIdSpesialisasi(Long idSpesialisasi){
        this.idSpesialisasi = idSpesialisasi;
    }

    //getter setter namaSpesialisasi
    public String getNamaSpesialisasi(){
        return this.namaSpesialisasi;
    }

    public void setNamaSpesialisasi(String namaSpesialisasi){
        this.namaSpesialisasi = namaSpesialisasi;
    }

    //getter setter gelarSpesialisasi
    public String getGelarSpesialisasi(){
        return this.gelarSpesialisasi;
    }

    public void setGelarSpesialisasi(String gelarSpesialisasi){
        this.gelarSpesialisasi = gelarSpesialisasi;
    }

    public List<DokterModel> getListDokter(){
        return this.listDokter;
    }

    public void setListDokter(List<DokterModel> listDokter){
        this.listDokter = listDokter;
    }
}