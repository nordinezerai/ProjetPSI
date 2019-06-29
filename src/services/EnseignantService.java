package services;

import dao.EnseignantDAO;
import entity.Enseignant;

import java.util.Collection;

public class EnseignantService {

    EnseignantDAO ensDAO = new EnseignantDAO();

    public Collection<Enseignant> getAllEnseignants() {
        Collection<Enseignant> enseignants = ensDAO.getAllEnseignants();
        return enseignants;
    }
}
