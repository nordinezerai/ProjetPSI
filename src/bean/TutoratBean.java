package bean;

import entity.Enseignant;
import entity.Etudiant;
import entity.Tutorat;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import services.EnseignantService;
import services.EtudiantService;
import services.TutoratService;
import utils.LevenshteinDistance;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean(name="tutoratBean")
@SessionScoped
public class TutoratBean {

    private EtudiantService etudiantService = new EtudiantService();
    private EnseignantService enseignantService = new EnseignantService();
    private TutoratService tutoratService = new TutoratService();

    private Collection<Tutorat> tutorats = new ArrayList<Tutorat>();
    private Tutorat tutorat;

    private Collection<Etudiant> etudiants;
    private Collection<Enseignant> enseignants;

    private String fileName;
    private String uploadPath="C:\\Users\\nino\\IdeaProjects\\ProjetPSI\\web\\WEB-INF\\uploads";

    boolean uploaded;

    public Collection<Tutorat> getTutorats() {
        return tutorats;
    }

    public void setTutorats(Collection<Tutorat> tutorats) {
        this.tutorats = tutorats;
    }

    public Tutorat getTutorat() {
        return tutorat;
    }

    public void setTutorat(Tutorat tutorat) {
        this.tutorat = tutorat;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Collection<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Collection<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Collection<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Collection<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    //méthode qui crée une copie du fichier choisi dans un dossier temporaire
    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        Path folder = Paths.get(uploadPath);
        String filename = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
        try {
            Path file = Files.createTempFile(folder, filename + "-", "." + extension);
            InputStream input = uploadedFile.getInputstream();
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
            this.fileName = file.toString();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.uploaded=true;

        System.out.println(fileName);
    }

    //méthode qui va lire les données dans le fichier temporaire pour ensuite remplir le tableau des tutorats
    public void importData(){
        if(uploaded) {
            try {
                //ne pas oublier d'avoir les jars contenant cette classe
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                String myDB = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=" + fileName + ";" + "DriverID=22;READONLY=false";

                Connection con = DriverManager.getConnection(myDB, "", "");
                Statement stmt = con.createStatement();
                //Le nom des colonnes du fichier Excel doivent avoir les mêmes noms que les colonnes de la requête
                ResultSet rs = stmt.executeQuery("select distinct Nom_APP, Prenom, NOM_TE, Prenom_TE, PROMO, ANNEE, entreprise  from [Feuil1$] where Nom_APP <> null and NOM_TE <> null");

                etudiants = etudiantService.getAllEtudiants();
                enseignants = enseignantService.getAllEnseignants();

                this.tutorats = new ArrayList<Tutorat>();

                //on parcours toutes les lignes du recordset
                while (rs.next()) {
                    String nomEtu = rs.getString(1);
                    String prenomEtu = rs.getString(2);
                    String nomEns = rs.getString(3);
                    String prenomEns = rs.getString(4);
                    String promo = rs.getString(5);
                    int annee = rs.getInt(6);
                    String entreprise = rs.getString(7);

                    //on remplit nos objet ac les informations du fichier Excel
                    Etudiant etu = new Etudiant(nomEtu, prenomEtu, promo);
                    etu.setId(rs.getRow());
                    Enseignant ens = new Enseignant(nomEns, prenomEns);
                    ens.setId(rs.getRow());
                    Tutorat t = new Tutorat(etu, ens, annee, entreprise);
                    t.setId(rs.getRow());

                    //on set le score de la similarité en comparant les prenoms/noms avec ceux de la table de références t_ens et t_etu
                    int scoreSimilarity = 10;
                    int s = 0;
                    for (Etudiant e : etudiants) {
                        String eprenom = e.getPrenom();
                        String enom = e.getNom();
                        for (Enseignant en : enseignants) {
                            String enprenom = en.getPrenom();
                            String ennom = en.getNom();
                            s = LevenshteinDistance.computeLevenshteinDistance(eprenom.trim() + enom.trim() + enprenom.trim() + ennom.trim(), prenomEtu.trim() + nomEtu.trim() + prenomEns.trim() + nomEns.trim());
                            if (scoreSimilarity > s) {
                                scoreSimilarity = s;
                            }
                        }
                    }

                    t.setScoreSimilarity(scoreSimilarity);
                    //on remplit notre liste de tutorats qui va nous servir à afficher notre tableau de tutorats dans la vue
                    this.tutorats.add(t);

                    System.out.println(scoreSimilarity + "--" + t.getId() + "---" + t.getEtudiant().getNom() + "---" + t.getEnseignant().getNom());
                }

                rs.close();
                stmt.close();
                con.close();

            //Files.delete(Paths.get(fileName));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } /*catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Tutorat modifié");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Modification annulée");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onUploadEdit(){
        if(!uploaded) {
            FacesMessage msg = new FacesMessage("Veuillez importer un fichier !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public void validate(){
        tutoratService.saveTutorat(tutorats);

        FacesMessage msg = new FacesMessage("Tutorat(s) ajouté(s) !");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
