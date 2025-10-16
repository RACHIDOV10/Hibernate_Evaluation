/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.EmployeTache;
import entities.Projet;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import uttil.HibernateUtil;

/**
 *
 * @author Admin
 */
public class EmployeTacheService implements IDao<EmployeTache> {

    @Override
    public boolean create(EmployeTache o) {
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

    public List<EmployeTache> findAll() {
        Session session = null;
        Transaction tx = null;
        List<EmployeTache> lignes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            lignes = session.createQuery("from LigneCommandeProduit").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lignes;
    }

    @Override
    public EmployeTache getById(int id) {
        Session session=null;
        Transaction tx=null;
        EmployeTache employeTache=null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employeTache= (EmployeTache) session.get(EmployeTache.class, id);
            
            tx.commit();
          
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            
        } finally{
            if (session != null)
                session.close();
        }
        
        
        return employeTache;
    }

    @Override
    public List<EmployeTache> getAll() {
        Session session = null;
        Transaction tx = null;
        List<EmployeTache> employeTaches = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employeTaches =session.createQuery("from Client").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  employeTaches;
    }

}
