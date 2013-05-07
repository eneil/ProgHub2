/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlEJB;

import javax.ejb.Stateless;

import Model.Project;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Eneil
 */
@Stateless
public class ProjectBean implements ProjectBeanLocal {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<Project> listProject(String title, String due, String lang, String contact, String cont, String comp, String desc) {
        List<Project> allProj = new ArrayList<Project>();
        Project tmpProj  = new Project();
        
        tmpProj.setTitle(title);
        tmpProj.setDue(due);
        tmpProj.setLang(lang);
        tmpProj.setContact(contact);
        tmpProj.setContributors(cont);
        tmpProj.setCompensation(comp);
        tmpProj.setDescription(desc);
        em.persist(tmpProj);
        try {
            allProj = em.createQuery("SELECT p FROM Project p").getResultList();
        } catch(NoResultException e) {}
        
        return allProj;
        
    }
    
   
}
