package services;

import dao.EtudiantDAO;
import entity.Etudiant;

import java.util.Collection;

public class EtudiantService {

    EtudiantDAO etuDAO = new EtudiantDAO();

    public Collection<Etudiant> getAllEtudiants() {
        Collection<Etudiant> etudiants = etuDAO.getAllEtudiants();
        return etudiants;
    }
}
