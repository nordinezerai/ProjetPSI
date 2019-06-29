package dao;

import entity.Enseignant;
import entity.Etudiant;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.Collection;

public class EnseignantDAO {

    Session s = null;

    public Collection<Enseignant> getAllEnseignants() {
        s = HibernateUtils.getSession();

        Transaction t = s.beginTransaction();

        Collection<Enseignant> enseignants = null;

        try {
            enseignants = (Collection<Enseignant>)s.createQuery("FROM Enseignant").list();

            t.commit();
        } catch (HibernateException e) {
            if (t!=null) t.rollback();
            e.printStackTrace();
        } finally {
            s.close();
        }
        return enseignants;
    }
}
