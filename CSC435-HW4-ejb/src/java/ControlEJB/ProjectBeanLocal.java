/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlEJB;

import Model.Project;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Eneil
 */
@Local
public interface ProjectBeanLocal {
    
   

    public List<Project> listProject(String title, String due, String lang, String contact, String cont, String comp, String desc);

   
}
