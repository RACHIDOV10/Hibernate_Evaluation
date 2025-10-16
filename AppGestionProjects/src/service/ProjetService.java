/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.Projet;
import entities.Tache;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import uttil.HibernateUtil;

/**
 *
 * @author Admin
 */
public class ProjetService implements IDao<Projet>{
    
    @Override
    public boolean create(Projet o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            etat = false;
        } finally{
            if (session != null)
                session.close();
        }
        return etat;
    }
    
    
  

    @Override
    public Projet getById(int id) {
        Session session=null;
        Transaction tx=null;
        Projet projet=null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            projet= (Projet) session.get(Projet.class, id);
            
            tx.commit();
          
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            
        } finally{
            if (session != null)
                session.close();
        }
        
        
        return projet;
    }

    @Override
    public List<Projet> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Projet> projets = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            projets =session.createQuery("from Client").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  projets;

    
    
    }
    
    public List<Tache> tachByProjet(Projet projet){
    
     Session session = null;
        Transaction tx = null;
        List<Tache> taches = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            taches =session.getNamedQuery("tacheByProjet").setParameter("projet", projet).list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  taches;
    
    
    }
    
}
