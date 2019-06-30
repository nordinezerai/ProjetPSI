package dao;

import entity.Enseignant;
import entity.Etudiant;
import entity.Tutorat;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import utils.HibernateUtils;

import java.util.Collection;

public class TutoratDAO {

    Session s = null;
    EtudiantDAO eDAO = new EtudiantDAO();
    EnseignantDAO ensDAO = new EnseignantDAO();

    public void saveTutorat(Collection<Tutorat> tutorats) {
        if(s == null || !s.isOpen()) s = HibernateUtils.getSession();

        Transaction tx = s.beginTransaction();
        //on parcours tout les tutorats (modifiés ou non) de notre vue
        for(Tutorat t : tutorats){

            Etudiant e = eDAO.getEtudiant(t.getEtudiant().getNom(),t.getEtudiant().getPrenom());
            //Si un étudiant existe déja on ne crée pas de doublon sinon on l'insère en base
            if(e==null){
                eDAO.saveEtudiant(t.getEtudiant());
            }
            //on récupère l'étudiant de la BD pour obtenir l'id
            e = eDAO.getEtudiant(t.getEtudiant().getNom(),t.getEtudiant().getPrenom());

            //idem pour l'enseignant
            Enseignant ens = ensDAO.getEnseignant(t.getEnseignant().getNom(),t.getEnseignant().getPrenom());
            if(ens==null){
                ensDAO.saveEnseignant(t.getEnseignant());
            }
            ens = ensDAO.getEnseignant(t.getEnseignant().getNom(),t.getEnseignant().getPrenom());

            //on set les id récupérés de l'étudiant et de l'enseignant dans le tutorat correspondant
            Tutorat tutorat = this.getTutorat(e.getId(),ens.getId(),t.getAnnee());

            int idEtu = e.getId();
            int idEns = ens.getId();
            int annee = t.getAnnee();

            t.getEtudiant().setId(idEtu);
            t.getEnseignant().setId(idEns);

            System.out.println(idEtu+"---"+idEns+"---"+annee);
            //on verifie si le tutorat n'existe pas dans la base pour ne pas violer la contrainte d'intégrité (clé id_étu,id_ens,année unique)
            if(tutorat==null){
                try {
                    s.save(t);
                }catch(ConstraintViolationException ex){
                    ex.printStackTrace();
                }

            }
        }

        try {
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }finally {
            s.close();
        }
    }

    private Tutorat getTutorat(Integer idEtu, Integer idEns, Integer annee) {
        if(s == null || !s.isOpen()) s = HibernateUtils.getSession();

        Query q = s.createQuery("from Tutorat where id_etu=:idEtu and id_ens=:idEns and annee=:annee").setInteger("idEtu", idEtu).setInteger("idEns", idEns).setInteger("annee",annee);

        Tutorat t = (Tutorat)q.uniqueResult();

        return t;
    }


}
