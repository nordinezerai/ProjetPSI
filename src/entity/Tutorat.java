package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="T_TUTORAT")
public class Tutorat implements Serializable{

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;
    @Column(name="ID_ENS")
    private Integer idEns;
    @Column(name="ID_ETU")
    private Integer idEtu;
    @Column(name="ANNEE")
    private Integer annee;
    @Column(name="ENTREPRISE")
    private String entreprise;

    public Tutorat() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEns() {
        return idEns;
    }

    public void setIdEns(Integer idEns) {
        this.idEns = idEns;
    }

    public Integer getIdEtu() {
        return idEtu;
    }

    public void setIdEtu(Integer idEtu) {
        this.idEtu = idEtu;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
}
