package tugas.individu.sidok.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "jadwalBertugas")

public class JadwalModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJadwal;

    @NotNull
    @Column(name = "dokterId")
    private Long dokterId;

    @NotNull
    @Column(name = "poliId")
    private Long poliId;

    @NotNull
    @Size(max=9)
    @Column(name = "hari")
    private String hari;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dokterId", updatable = false, insertable = false, referencedColumnName = "idDokter")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokterModel dokter;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "poliId", updatable = false, insertable = false, referencedColumnName = "idPoli")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoliModel poli;

    public Long getIdJadwal(){
        return this.idJadwal;
    }

    public void setIdJadwal(Long idJadwal){
        this.idJadwal = idJadwal;
    }

    public Long getIdDokter(){
        return this.dokterId;
    }

    public void setIdDokter(Long dokterId){
        this.dokterId = dokterId;
    }

    public Long getIdPoli(){
        return this.poliId;
    }

    public void setIdPoli(Long poliId){
        this.poliId = poliId;
    }

    public String getHari(){
        return this.hari;
    }

    public void setHari(String hari){
        this.hari = hari;
    }

    public DokterModel getDokter(){
        return this.dokter;
    }

    public void setDokter(DokterModel dokter){
        this.dokter = dokter;
    }

    public PoliModel getPoli(){
        return this.poli;
    }

    public void setPoli(PoliModel poli){
        this.poli = poli;
    }
}