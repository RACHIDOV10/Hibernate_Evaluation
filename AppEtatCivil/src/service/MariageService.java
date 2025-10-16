/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.Mariage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import dao.IDao;
import java.util.List;

public class MariageService implements IDao<Mariage> {

    @Override
    public boolean create(Mariage o) {
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
            if (tx != null) tx.rollback();
            etat = false;
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return etat;
    }

    @Override
    public Mariage getById(int id) {
    
        Session session=null;
        Transaction tx=null;
        Mariage mariage=null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            mariage= (Mariage) session.get(Mariage.class, id);
            
            tx.commit();
          
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            
        } finally{
            if (session != null)
                session.close();
        }
        
        
        return mariage;
    }

    @Override
    public List<Mariage> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Mariage> mariages = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            mariages =session.createQuery("from Client").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  mariages;
    }
    
    
}
