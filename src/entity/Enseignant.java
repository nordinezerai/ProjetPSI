package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="T_ENS")
public class Enseignant implements Serializable{

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;
    @Column(name="NOM")
    private String nom;
    @Column(name="PRENOM")
    private String prenom;

    public Enseignant() {
    }

    public Enseignant(String nom, String prenom) {
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
}
