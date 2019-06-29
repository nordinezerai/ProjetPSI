package bean;

import entity.Enseignant;
import entity.Etudiant;
import entity.Tutorat;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import services.EnseignantService;
import services.EtudiantService;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.bean.*;

@ManagedBean(name="tutoratBean")
@SessionScoped
public class TutoratBean implements Serializable {

    private static final long serialVersionUID = 6L;

    private EtudiantService etudiantService = new EtudiantService();
    private EnseignantService enseignantService = new EnseignantService();

    private Collection<Tutorat> tutorats;
    private Tutorat tutorat;

    private String fileName;
    private String uploadPath="C:\\Users\\nino\\IdeaProjects\\ProjetPSI\\web\\WEB-INF\\uploads";

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
        System.out.println(fileName);
    }

    public void importData(){

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String myDB = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ="+ fileName +";" + "DriverID=22;READONLY=false";

            Connection con = DriverManager.getConnection(myDB, "", "");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select distinct Nom_APP, Prenom, NOM_TE, Prenom_TE, PROMO, ANNEE, entreprise  from [Feuil1$] where Nom_APP <> null and NOM_TE <> null");

            Collection<Etudiant> etudiants = etudiantService.getAllEtudiants();
            Collection<Enseignant> enseignants = enseignantService.getAllEnseignants();

            this.tutorats = new ArrayList<Tutorat>();

            while (rs.next()) {
                String nomEtu = rs.getString(1);
                String prenomEtu = rs.getString(2);
                String nomEns = rs.getString(3);
                String prenomEns = rs.getString(4);
                String promo = rs.getString(5);
                int annee = rs.getInt(6);
                String entreprise = rs.getString(7);

                Etudiant etu = new Etudiant(nomEtu,prenomEtu,promo);
                etu.setId(rs.getRow());
                Enseignant ens = new Enseignant(nomEns,prenomEns);
                ens.setId(rs.getRow());
                Tutorat t = new Tutorat(etu,ens,annee,entreprise);
                t.setId(rs.getRow());
                this.tutorats.add(t);

                System.out.println(t.getId() + "---" + t.getEtudiant().getNom() + "---" + t.getEnseignant().getNom());
            }

            rs.close();
            stmt.close();
            con.close();

            Files.delete(Paths.get(fileName));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void edit (Tutorat t){
        System.out.print(t.getEtudiant().getNom());
    }
}
