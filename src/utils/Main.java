package utils;

import entity.Enseignant;
import entity.Etudiant;
import entity.Tutorat;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    String s = "Mohamed";
    String s2 = "Mohamed";
    //restoreDataBase();
    System.out.println(LevenshteinDistance.computeLevenshteinDistance(s,s2));
    }

    public static void restoreDataBase(){
        HibernateUtils h = new HibernateUtils();

        Session session = null;
        Transaction transaction = null;

        try {

            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            transaction.begin();

            Etudiant etu0 = new Etudiant("DOS SANTOS","Jérémie","2018");
            Etudiant etu = new Etudiant("DIAGNE","Awa","2018");
            Etudiant etu2 = new Etudiant("KAPLAN","Fabien","2018");
            Etudiant etu3 = new Etudiant("BENAMAR","Merouane","2018");
            Etudiant etu4 = new Etudiant("AIT SI AMER","Melissa","2018");
            Etudiant etu5 = new Etudiant("GOULOU","Touré","2018");
            Etudiant etu6 = new Etudiant("KEZOUI","Kenza","2018");
            Etudiant etu7 = new Etudiant("AMROUCHE","Belkacem","2018");
            Etudiant etu8 = new Etudiant("SIDIBE","Yoro","2018");
            Etudiant etu9 = new Etudiant("AMIACH","Hugo","2018");
            Etudiant etu10 = new Etudiant("CARPENTIER","Antoine","2018");
            Etudiant etu11 = new Etudiant("ICHAY","Jérémy","2018");
            Etudiant etu12 = new Etudiant("IKNI","Yannis","2018");
            Etudiant etu13 = new Etudiant("DOS SANTOS","Nicolas","2018");
            Etudiant etu14 = new Etudiant("ADJEODA","Firdaws","2018");
            Etudiant etu15 = new Etudiant("SADAOUI","Kamilia","2018");
            Etudiant etu16 = new Etudiant("BESCOS HUETE","Ruben","2018");
            Etudiant etu17 = new Etudiant("BABOU","Zina","2018");
            Etudiant etu18 = new Etudiant("DANI","Sofian","2018");
            Etudiant etu19 = new Etudiant("TANDIA","Ganda","2018");
            Etudiant etu20 = new Etudiant("KRANIQI","Nora","2018");
            Etudiant etu21 = new Etudiant("ADMANE","Amine","2018");
            Etudiant etu22 = new Etudiant("SI-MOHAMMEDI","Sarah","2018");
            Etudiant etu23 = new Etudiant("SERSOUR","Yasmine","2018");
            Etudiant etu24 = new Etudiant("HILAIRE","Julien","2018");
            Etudiant etu25 = new Etudiant("YAHIAOUI","Mourad","2018");
            Etudiant etu26 = new Etudiant("VIDO","Uriel","2018");
            Etudiant etu27 = new Etudiant("KRYLOV","Kirill","2018");
            Etudiant etu28 = new Etudiant("SI-SALAH","Thinhinane","2018");
            Etudiant etu29 = new Etudiant("BENKANIA","Mustapha","2018");
            Etudiant etu30 = new Etudiant("DEMOLLIERE","Clément","2018");
            Etudiant etu31 = new Etudiant("JAA","Yassine","2018");
            Etudiant etu32 = new Etudiant("MA","Quentin","2018");
            Etudiant etu33 = new Etudiant("ROCHE GERALD","Prashan","2018");

            session.save(etu);
            session.save(etu2);
            session.save(etu3);
            session.save(etu4);
            session.save(etu5);
            session.save(etu6);
            session.save(etu7);
            session.save(etu8);
            session.save(etu9);
            session.save(etu10);
            session.save(etu11);
            session.save(etu12);
            session.save(etu13);
            session.save(etu14);
            session.save(etu15);
            session.save(etu16);
            session.save(etu17);
            session.save(etu18);
            session.save(etu19);
            session.save(etu20);
            session.save(etu21);
            session.save(etu22);
            session.save(etu23);
            session.save(etu24);
            session.save(etu25);
            session.save(etu26);
            session.save(etu27);
            session.save(etu28);
            session.save(etu29);
            session.save(etu30);
            session.save(etu31);
            session.save(etu32);
            session.save(etu33);

            Enseignant ens0 = new Enseignant("Azhar-Arnal","Juliette");
            Enseignant ens = new Enseignant("Ben Hamida Mrabet","Sana");
            Enseignant ens2 = new Enseignant("BENDRAOU","Reda");
            Enseignant ens3 = new Enseignant("Couronné","Olivier");
            Enseignant ens4 = new Enseignant("DELBOT","François");
            Enseignant ens5 = new Enseignant("GERVAIS","Marie Pierre");
            Enseignant ens6 = new Enseignant("GUEHIS-SAADAOUI","Sonia");
            Enseignant ens7 = new Enseignant("Hanen","Claire");
            Enseignant ens8 = new Enseignant("Hardouin Ceccantini","Cécile");
            Enseignant ens9 = new Enseignant("Hillah","Lom");
            Enseignant ens10 = new Enseignant("HYON","Emmanuel");
            Enseignant ens11 = new Enseignant("Legond- Aubry","Fabrice");
            Enseignant ens12 = new Enseignant("Mesnager","Laurent");
            Enseignant ens13 = new Enseignant("Poizat","Pascal");
            Enseignant ens14 = new Enseignant("PRADAT PEYRE","Jean François");
            Enseignant ens15 = new Enseignant("Rukoz-Castillo","Marta");
            Enseignant ens16 = new Enseignant("Zetlaoui","Mélanie");

            session.save(ens);
            session.save(ens2);
            session.save(ens3);
            session.save(ens4);
            session.save(ens5);
            session.save(ens6);
            session.save(ens7);
            session.save(ens8);
            session.save(ens9);
            session.save(ens10);
            session.save(ens11);
            session.save(ens12);
            session.save(ens13);
            session.save(ens14);
            session.save(ens15);
            session.save(ens16);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();

            }
        }
    }
}
