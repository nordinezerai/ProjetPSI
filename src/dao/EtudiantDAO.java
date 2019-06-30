package dao;

import entity.Etudiant;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collection;

public class EtudiantDAO {

    Session s = null;

    public Collection<Etudiant> getAllEtudiants() {
        s = HibernateUtils.getSession();

        Transaction t = s.beginTransaction();

        Collection<Etudiant> etudiants = null;

        try {
            etudiants = (Collection<Etudiant>)s.createQuery("FROM Etudiant").list();

            t.commit();
        } catch (HibernateException e) {
            if (t!=null) t.rollback();
            e.printStackTrace();
        } finally {
            s.close();
        }
        return etudiants;
    }

    public Etudiant getEtudiant(String nom, String prenom) {
        if(s == null || !s.isOpen()) s = HibernateUtils.getSession();

        Query q = s.createQuery("from Etudiant where nom=:nom and prenom=:prenom ").setString("nom", nom.trim()).setString("prenom", prenom.trim());

        Etudiant e = (Etudiant)q.uniqueResult();

        return e;
    }

    public void saveEtudiant(Etudiant e) {
        s = HibernateUtils.getSession();

        Transaction t = s.beginTransaction();

        try {
            s.save(e);
            t.commit();
        } catch (HibernateException ex) {
            if (t!=null) t.rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }

    }
}
