/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgestionprojets;

import java.text.SimpleDateFormat;
import service.EmployeService;
import service.ProjetService;
import service.TacheService;
import service.EmployeTacheService;
import entities.Employe;
import entities.EmployeTache;
import entities.Projet;
import entities.Tache;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AppGestionProjets {

    public static void main(String[] args) {
        EmployeService es = new EmployeService();
        ProjetService ps = new ProjetService();
        TacheService ts = new TacheService();
        EmployeTacheService ets = new EmployeTacheService();

        Employe emp = es.getById(1);
        Employe e1 = new Employe();
        e1.setNom("Ali");
        e1.setPrenom("Benali");

        Employe e2 = new Employe();
        e2.setNom("Sara");
        e2.setPrenom("ElAmrani");

        es.create(e1);
        es.create(e2);

        Projet p1 = new Projet();
        p1.setNom("Site Web");
        p1.setDateDebut(new Date());
        p1.setDateFin(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L)); // + 1 semaine

        Projet p2 = new Projet();
        p2.setNom("Application Mobile");
        p2.setDateDebut(new Date());
        p2.setDateFin(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000L)); // + 2 semaines

        ps.create(p1);
        ps.create(p2);
        Tache t1 = new Tache();
        t1.setNom("Design UI");
        t1.setProjet(p1);

        Tache t2 = new Tache();
        t2.setNom("Développement Backend");
        t2.setProjet(p1);

        Tache t3 = new Tache();
        t3.setNom("Test Application");
        t3.setProjet(p2);

        ts.create(t1);
        ts.create(t2);
        ts.create(t3);
        EmployeTache et1 = new EmployeTache();
        et1.setEmploye(e1);
        et1.setDatDebutReelle(new Date());
        et1.setDatFinReelle(new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000L)); // +2 jours

        EmployeTache et2 = new EmployeTache();
        et2.setEmploye(e2);
        et2.setDatDebutReelle(new Date());
        et2.setDatFinReelle(new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000L)); // +5 jours

        ets.create(et1);
        ets.create(et2);

        System.out.println("taches par employe");

        for (Tache t : es.tachByEmploye(emp)) {

            System.out.println(t);

        }

        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("projets par employe");
        for (Projet p : es.projetByEmploye(emp)) {

            System.out.println(p);

        }
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("tache par projet");
        for (Tache t : ps.tachByProjet(ps.getById(1))) {

            System.out.println(t);
        }

        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("tache par date");
        for (Tache t : ts.tacheByDate(new Date("2020/02/02"), new Date("2024/02/02"))) {

            System.out.println(t);
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("tache par prix");
        for (Tache t : ts.tacheByPrix(1000)) {

            System.out.println(t);
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Projet p = ps.getById(1);
        List<Tache> taches = ps.tachByProjet(p);
        System.out.printf("Projet : %-5d Nom: %-10s   Date debut: %s%n",
                p.getId(),
                p.getNom(),
                sdf.format(p.getDateDebut()));

        System.out.println("Liste des Taches :");
        System.out.printf("%-8s %-15s %-20s %-20s %-15s%n",
                "ID", "Tâche", "Employé", "Date Début Réelle", "Date Fin Réelle");

        for (Tache tache : taches) {
            for (EmployeTache et : ts.employeTachebyTache(tache)) {
                System.out.printf("%-8s %-15s %-20s %-20s %-15s%n",
                        tache.getId(),
                        tache.getNom(),
                        et.getEmploye().getNom(),
                        sdf.format(et.getDatDebutReelle()),
                        sdf.format(et.getDatFinReelle())
                );
            }
        }

    }

}
