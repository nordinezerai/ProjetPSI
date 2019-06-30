package dao;

import entity.Enseignant;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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

    public Enseignant getEnseignant(String nom, String prenom) {
        if(s == null || !s.isOpen()) s = HibernateUtils.getSession();

        Query q = s.createQuery("from Enseignant where nom=:nom and prenom=:prenom ").setString("nom", nom.trim()).setString("prenom", prenom.trim());

        Enseignant e = (Enseignant)q.uniqueResult();

        return e;
    }

    public void saveEnseignant(Enseignant ens) {
        s = HibernateUtils.getSession();

        Transaction t = s.beginTransaction();

        try {
            s.save(ens);
            t.commit();
        } catch (HibernateException ex) {
            if (t!=null) t.rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }
}
