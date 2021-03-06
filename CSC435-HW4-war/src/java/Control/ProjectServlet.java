/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//servlets go in control package
//ProjServ
package Control;

import ControlEJB.ProjectBeanLocal;
import Model.Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author ethan
 */
public class ProjectServlet extends HttpServlet {
    
    @EJB
    ProjectBeanLocal projBean;
    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        try {
//            
            if (request.getParameter("title") != null && request.getParameter("due") != null && request.getParameter("desc") != null
                    && request.getParameter("lang") != null && request.getParameter("comp") != null && request.getParameter("group") != null && request.getParameter("contact") != null) {
            
               
                List<Project> projList = projBean.listProject(request.getParameter("title"), request.getParameter("due"), request.getParameter("lang"), request.getParameter("contact"), request.getParameter("group"), request.getParameter("comp"), request.getParameter("desc"));
          
                    String projTable = " ";
                    for (Project p : projList) {
                    projTable += "<tr><td>" + p.getTitle() + "</td><td>" + p.getDue() + "</td><td>" + p.getLang() + "</td><td>" + p.getContact() + "</td><td>" + p.getContributors() + "</td><td>" + p.getCompensation() + "</td><td>" + p.getDescription() + "</td></tr>";
                    }
                    
                    if (request.getAttribute("oauth_token") == null) {
                        response.sendRedirect("OAuthLogin");
                    } else {
                        Tweeter twitter = new Tweeter("xml");
                        twitter.initOAuth(request, response);
                        twitter.updateStatus(Object.class, "I'm working on" + request.getAttribute("title") + "come help!",null);
                    }
                
                request.setAttribute("projTable", projTable);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ProjectList.jsp");
             //   dispatcher.forward(request, response);
//             
            }
            
        } finally {
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
