package bean;

import java.io.Serializable;
import javax.faces.bean.*;


@ManagedBean(name="etudiantBean")
@SessionScoped
public class EtudiantBean implements Serializable {
	
	private static final long serialVersionUID = 4L;
    
    private String nom;
    private String prenom;
    private String promo;

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
