package bean;

import java.io.Serializable;
import javax.faces.bean.*;


@ManagedBean(name="enseignantBean")
@SessionScoped
public class EnseignantBean implements Serializable {

    private static final long serialVersionUID = 3L;

    private String nom;
    private String prenom;

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
