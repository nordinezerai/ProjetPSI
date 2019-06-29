package dao;

import entity.Etudiant;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.Collection;

public class EtudiantDAO {

    Session s = null;

    public Collection<Etudiant> getAllEtudiants() {
        s = HibernateUtils.getSession();

        Transaction t = s.beginTransaction();

        Collection<Etudiant> etudiants = null;

        try {
            etudiants = (Collection<Etudiant>)s.createQuery("FROM t_etu").list();

            t.commit();
        } catch (HibernateException e) {
            if (t!=null) t.rollback();
            e.printStackTrace();
        } finally {
            s.close();
        }
        return etudiants;
    }
}
