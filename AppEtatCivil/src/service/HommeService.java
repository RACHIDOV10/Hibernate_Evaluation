/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entites.Homme;
import entites.Femme;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Admin
 */
public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme o) {
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
    public Homme getById(int id) {
    
        Session session=null;
        Transaction tx=null;
        Homme homme=null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            homme= (Homme) session.get(Homme.class, id);
            
            tx.commit();
          
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            
        } finally{
            if (session != null)
                session.close();
        }
        
        
        return homme;
    }

    @Override
    public List<Homme> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Homme> hommes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            hommes =session.createQuery("from Client").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  hommes;
    }
    
    public List<Femme> femmeByHomme( Homme homme , Date d1 , Date d2) {
        Session session = null;
        Transaction tx = null;
        List<Femme> femmes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes =session.getNamedQuery("femmeByHomme").setParameter("homme", homme).setParameter("d1", d1).setParameter("d2", d2).list();
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