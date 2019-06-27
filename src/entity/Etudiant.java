package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="T_ETU")
public class Etudiant implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;
    @Column(name="NOM")
    private String nom;
    @Column(name="PRENOM")
    private String prenom;
    @Column(name="PROMO")
    private String promo;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
}
