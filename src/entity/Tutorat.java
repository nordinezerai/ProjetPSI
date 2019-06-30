package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name="t_tutorat",uniqueConstraints=@UniqueConstraint(columnNames= {"id_etu","id_ens","annee"}))
public class Tutorat implements Serializable{

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_etu")
    private Etudiant etudiant;

    @OneToOne
    @JoinColumn(name = "id_ens")
    private Enseignant enseignant;

    @Column(name="annee")
    private Integer annee;

    @Column(name="entreprise")
    private String entreprise;

    @Transient
    private int scoreSimilarity;

    public Tutorat(){

    }

    public Tutorat(Etudiant etudiant,Enseignant enseignant,int annee,String entreprise) {
        this.etudiant = etudiant;
        this.enseignant = enseignant;
        this.annee = annee;
        this.entreprise = entreprise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
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

    public int getScoreSimilarity() {
        return scoreSimilarity;
    }

    public void setScoreSimilarity(int scoreSimilarity) {
        this.scoreSimilarity = scoreSimilarity;
    }
}
