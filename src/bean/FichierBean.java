package bean;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@ManagedBean(name="fichierBean")
@SessionScoped
public class FichierBean {

    private Part file;
    private String fileName;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        Path folder = Paths.get("/Users/nino/IdeaProjects/ProjetPSI/web/WEB-INF/uploads");
        String filename = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
        try {
            Path file = Files.createTempFile(folder, filename + "-", "." + extension);
            InputStream input = uploadedFile.getInputstream();
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
            this.fileName = file.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileName);
    }

}
