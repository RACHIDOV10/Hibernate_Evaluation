/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import dao.IDao;
import entities.Employe;
import entities.Tache;
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
public class EmployeService implements IDao<Employe>{
    
    @Override
    public boolean create(Employe o) {
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
    public Employe getById(int id) {
        Session session=null;
        Transaction tx=null;
        Employe employe=null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employe= (Employe) session.get(Employe.class, id);
            
            tx.commit();
          
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            
        } finally{
            if (session != null)
                session.close();
        }
        
        
        return employe;
    }

    @Override
    public List<Employe> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Employe> employes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employes =session.createQuery("from Client").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
     
        return  employes;
    }
    
     public List<Tache> tachByEmploye(Employe employe){
    
     Session session = null;
        Transaction tx = null;
        List<Tache> taches = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            taches =session.getNamedQuery("tacheByEmploye").setParameter("employe", employe).list();
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
            
      public List<Projet> projetByEmploye(Employe employe){
    
     Session session = null;
        Transaction tx = null;
        List<Projet> projets = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            projets =session.getNamedQuery("projetByEmploye").setParameter("employe", employe).list();
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
    
}
