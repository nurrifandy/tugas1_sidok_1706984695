package tugas.individu.sidok.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.format.annotation.DateTimeFormat;

import tugas.individu.sidok.model.SpesialisasiModel;
import tugas.individu.sidok.model.PoliModel;

@Entity
@Table(name = "dokter")

public class DokterModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "nipDokter", nullable = false, unique = true)
    private String nipDokter;
    
    @NotNull
    @Size(max = 255)
    @Column(name = "nikDokter", nullable = false, unique = true)
    private String nikDokter;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggalLahir", nullable = false)
    private Date tanggalLahir;
    
    @NotNull
    @Size(max = 255)
    @Column(name = "tempatLahir", nullable = false)
    private String tempatLahir;
    
    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private Boolean jenisKelamin;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private List<JadwalModel> listPoli;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "spesialisasiDokter",
        joinColumns = @JoinColumn(name = "idDokter"),
        inverseJoinColumns = @JoinColumn(name = "idSpesialisasi")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<SpesialisasiModel> listSpesialisasi;

    // getter setter idDokter
    public Long getIdDokter(){
        return this.idDokter;
    }

    public void setIdDokter(Long idDokter){
        this.idDokter = idDokter;
    }

    // getter setter nama
    public String getNama(){
        return this.nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    // getter setter nipDokter
    public String getNipDokter(){
        return this.nipDokter;
    }

    public void setNipDokter(String nipDokter){
        this.nipDokter= nipDokter;
    }

    // getter setter nikDokter
    public String getNikDokter(){
        return this.nikDokter;
    }

    public void setNikDokter(String nikDokter){
        this.nikDokter = nikDokter;
    }

    // getter setter tanggalLahir
    public Date getTanggalLahir(){
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir){
        this.tanggalLahir = tanggalLahir;
    }

    // getter setter tampatLahir
    public String getTempatLahir(){
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir){
        this.tempatLahir = tempatLahir;
    }

    // getter setter jenisKelamin
    public Boolean getJenisKelamin(){
        return this.jenisKelamin;
    }

    public void setJenisKelamin(Boolean jenisKelamin){
        this.jenisKelamin=jenisKelamin;
    }

    // setter getter poliModel
    public List<JadwalModel> getListPoli(){
        return this.listPoli;
    }

    public void seLlistPoli(List<JadwalModel> listPoli){
        this.listPoli = listPoli;
    }

    // getter setter spesialisasiModel
    public List<SpesialisasiModel> getListSpesialisasi(){
        return this.listSpesialisasi;
    }

    public void setListSpesialisasi(List<SpesialisasiModel> listSpesialisasi){
        this.listSpesialisasi = listSpesialisasi;
    }
}