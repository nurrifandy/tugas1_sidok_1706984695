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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import tugas.individu.sidok.model.DokterModel;

@Entity
@Table(name = "poli")

public class PoliModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoli;

    @NotNull
    @Size(max = 255)
    @Column(name = "namaPoli", nullable = false)
    private String namaPoli;

    @NotNull
    @Size(max = 255)
    @Column(name = "lokasi", nullable = false)
    private String lokasi;

    //
    @OneToMany(mappedBy = "poli", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private List<JadwalModel> listDokter;

    
    //getter setter idPoli
    public Long getIdPoli(){
        return this.idPoli;
    }

    public void setIdPoli(Long idPoli){
        this.idPoli = idPoli;
    }

    // getter setter namaPoli
    public String getNamaPoli(){
        return this.namaPoli;
    }

    public void setNamaPoli(String namaPoli){
        this.namaPoli=namaPoli;
    }

    // getter setter lokasi
    public String getLokasi(){
        return this.lokasi;
    }

    public void setLokasi(String lokasi){
        this.lokasi = lokasi;
    }

    public List<JadwalModel> getListDokter(){
        return this.listDokter;
    }

    public void setListDokter(List<JadwalModel> listDokter){
        this.listDokter = listDokter;
    }

}