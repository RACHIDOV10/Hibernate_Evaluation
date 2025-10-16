/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import entites.Femme;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FemmeService implements IDao<Femme> {

    @Override
    public boolean create(Femme o) {
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
            if (tx != null) {
                tx.rollback();
            }
            etat = false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return etat;
    }
    
    @Override
    public Femme getById(int id) {
    
        Session session=null;
        Transaction tx=null;
        Femme femme=null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femme= (Femme) session.get(Femme.class, id);
            
            tx.commit();
          
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            
        } finally{
            if (session != null)
                session.close();
        }
        
        
        return femme;
    }

    @Override
    public List<Femme> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Femme> femmes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes =session.createQuery("from Client").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  femmes;
    }
    
    public int enfantFemme(Femme femme , Date d1 , Date d2) {
        Session session = null;
        Transaction tx = null;
        int enfants = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            enfants =(int) session.getNamedQuery("enfantFemme").setParameter("id", femme.getId()).setParameter("d1", d1).setParameter("d2", d2).uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  enfants;
    }
    
    
    public List<Femme> femmeMarriageCount(int anne) {
        Session session = null;
        Transaction tx = null;
        List<Femme> femmes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes =session.getNamedQuery("femmeMarriageCount").setParameter("anne", anne).list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  femmes;
    }
    
    

}