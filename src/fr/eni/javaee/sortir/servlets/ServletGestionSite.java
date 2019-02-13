package fr.eni.javaee.sortir.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bll.SiteManager;
import fr.eni.javaee.sortir.bo.Participant;
import fr.eni.javaee.sortir.bo.Site;

/**
 * Servlet implementation class ServletNouveauSite
 */
@WebServlet(
		urlPatterns = {
				"/nouveauSite",
				"/editerSite",
				"/supprimerSite"
		})
public class ServletGestionSite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGestionSite() {
		super();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recherche de l'utilisateur loggué
		HttpSession session = request.getSession(true);
		Object value = session.getAttribute("currentSessionParticipant");

		if (value != null) {
			Participant participantEnCours = (Participant) value;
			request.setAttribute("participantEnCours", participantEnCours);


			if (request.getServletPath().equals("/nouveauSite"))
			{
				request.setAttribute("title", "Ajouter");
				request.setAttribute("path", "nouveauSite");

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formSite.jsp");
				rd.forward(request, response);
			}
			
			if (request.getServletPath().equals("/editerSite"))
			{
				// variables
				request.setAttribute("title", "Modifier");
				request.setAttribute("path", "editerSite");

				// Affichage du SITE en cours
				int idSortie = Integer.parseInt(request.getParameter("idSite"));
				
				try {
					SiteManager siteManager = new SiteManager();
					Site siteToDisplay = siteManager.selectById(idSortie);
					request.setAttribute("site", siteToDisplay);
				} catch (BusinessException | SQLException e) {
					e.printStackTrace();
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formSite.jsp");
				rd.forward(request, response);
			}
			
			if (request.getServletPath().equals("/supprimerSite"))
			{
				int idSite = Integer.parseInt(request.getParameter("idSite"));
				
				try {
					SiteManager siteManager = new SiteManager();
					siteManager.delete(idSite);
				} catch (BusinessException e)
				{
					e.printStackTrace();
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/sites");
				rd.forward(request, response);
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().equals("/nouveauSite"))
		{
			request.setCharacterEncoding("UTF-8");
			Site siteNew = new Site();

			siteNew.setNom(request.getParameter("nom"));

			//Mise à jour en BDD
			SiteManager siteManager = new SiteManager();

			try {
				siteManager.insert(siteNew);
			} catch (BusinessException e)
			{
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/sites");
			rd.forward(request, response);
		}

		if (request.getServletPath().equals("/editerSite"))
		{
			request.setCharacterEncoding("UTF-8");
			Site siteUpdated = new Site();

			siteUpdated.setIdSite(Integer.parseInt(request.getParameter("idSite")));
			siteUpdated.setNom(request.getParameter("nom"));

			//Mise à jour en BDD
			SiteManager siteManager = new SiteManager();

			try {
				siteManager.update(siteUpdated);
			} catch (BusinessException | SQLException e)
			{
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/sites");
			rd.forward(request, response);
		}
	}

}
