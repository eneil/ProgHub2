/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ControlEJB.ProjectBeanLocal;
import Model.Project;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author ethan
 */
@WebService(serviceName = "ProjectService")
public class ProjectService {
    @EJB
    private ProjectBeanLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "listProject")
    public List<Project> listProject(@WebParam(name = "title") String title, @WebParam(name = "due") String due, @WebParam(name = "lang") String lang, @WebParam(name = "contact") String contact, @WebParam(name = "cont") String cont, @WebParam(name = "comp") String comp, @WebParam(name = "desc") String desc) {
        return ejbRef.listProject(title, due, lang, contact, cont, comp, desc);
    }
    
}
