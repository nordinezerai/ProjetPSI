package services;

import dao.TutoratDAO;
import entity.Tutorat;

import java.util.Collection;

public class TutoratService {

    private TutoratDAO tDAO = new TutoratDAO();

    public void saveTutorat(Collection<Tutorat> tutorats) {
        tDAO.saveTutorat(tutorats);
    }
}
