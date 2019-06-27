package utils;

import entity.Etudiant;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtils;

//Main qui sert à tester les fonctionnalitées du code sans avoir à demarrer tomcat
public class Main {
    public static void main(String[] args){
        HibernateUtils h = new HibernateUtils();
        Session s = null;
        if(s == null || !s.isOpen()) s = HibernateUtils.getSession();
//        Query q = s.createQuery("from t_etu where nom='toto'");
//
//        Etudiant u = (Etudiant)q.uniqueResult();
//        System.out.print(u.getNom());
    }
}
