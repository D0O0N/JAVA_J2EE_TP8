package TP_WEB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

@WebServlet(name = "ShowClient", urlPatterns = {"/ShowClient"})
public class ShowClientInState extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ShowClient</title>");
			out.println("</head>");
			out.println("<body>");
                        //out.println("<script >");
			try {	// Trouver la valeur du paramètre HTTP customerID
				String val = request.getParameter("state");
				if (val == null) {
					throw new Exception("La paramètre state n'a pas été transmis");
				}
                                DAOAdd dao = new DAOAdd(DataSourceFactory.getDataSource());
                                out.println("Choisissez votre etat : ");
                                out.println("<form action=\"/ServerTest/ShowClientInState\">");
                                out.println("<select name=\"state\">");
                                
                                for (int i = 0; i < dao.getAllStates().size(); i++) {
                                    String state = dao.getAllStates().get(i);
                                    if (val.equals(state)){
                                        out.printf("<option selected value=\"%s\">%s</option>" ,state,state );
                                    }
                                    else{
                                        out.printf("<option value=\"%s\">%s</option>" ,state,state );
                                    }
                                    
                                    
                                }
                                out.println("</select>");
                                out.println("<INPUT TYPE=\"submit\" VALUE=\"Envoyé !\">");
                                out.println("</form>");
                                
				// on doit convertir cette valeur en entier (attention aux exceptions !)
				

				
				
				// Afficher les propriétés du client	
                                out.printf("<table border = 1> <tr> <th>Id</th><th>Name</th><th>Adress</th></tr>");
                                for (int i = 0; i < dao.customersInState(val).size(); i++) {
                                    CustomerEntity customer = dao.customersInState(val).get(i);
                                    out.printf("<tr> <th>%d</th><th>%s</th><th>%s</th></tr>",
					customer.getCustomerId(),
					customer.getName(),
					customer.getAddressLine1());
                                
                                }
                                
                                out.printf("</table>");
                                
                                
				
			} catch (Exception e) {
				out.printf("Erreur : %s", e.getMessage());
			}
			out.printf("<hr><a href='%s'>Retour au menu</a>", request.getContextPath());
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex) {
			Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
		}
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
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
	}

}