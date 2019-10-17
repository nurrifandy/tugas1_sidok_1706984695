package tugas.individu.sidok.model;

import java.io.Serializable;

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


class JadwalJagaDokter implements Serializable{
    
    private Long idJadwal;

    private Long dokterId;
    
    private Long poliId;

    private String hari;

    public int hashCode(){
        return idJadwal.intValue();
    }

    public boolean equals(Object object){
        if(object instanceof JadwalJagaDokter){
            JadwalJagaDokter otherId = (JadwalJagaDokter) object;
            return (otherId.dokterId == this.dokterId) && (otherId.poliId== this.poliId) && (otherId.hari.equals(this.hari)); 
        }
        return false;
    }
}



@Entity
@Table(name = "jadwalBertugas")
@IdClass(JadwalJagaDokter.class)
public class JadwalModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJadwal;

    @Id
    private Long dokterId;

    @Id
    private Long poliId;

    @NotNull
    @Size(max=9)
    @Column(name = "hari")
    private String hari;

    @ManyToOne
    @JoinColumn(name = "dokterId", updatable = false, insertable = false, referencedColumnName = "idDokter")
    private DokterModel dokter;

    @ManyToOne
    @JoinColumn(name = "poliId", updatable = false, insertable = false, referencedColumnName = "idPoli")
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