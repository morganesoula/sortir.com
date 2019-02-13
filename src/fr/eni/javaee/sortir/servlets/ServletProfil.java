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
import fr.eni.javaee.sortir.bll.ParticipantManager;
import fr.eni.javaee.sortir.bll.SiteManager;
import fr.eni.javaee.sortir.bo.Participant;
import fr.eni.javaee.sortir.bo.Site;
@WebServlet(
		urlPatterns= {
				"/profil",
				"/organisateur"
		})
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProfil() {
		super();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Participant participantEnCours = (Participant) session.getAttribute("currentSessionParticipant");
		request.setAttribute("participantEnCours", participantEnCours);

		if (request.getServletPath().equals("/profil")) {
			try 
			{
				SiteManager siteManager = new SiteManager();
				Site site = siteManager.selectById(participantEnCours.getSiteRattachement().getIdSite());
				request.setAttribute("site", site);
				request.setAttribute("profil", participantEnCours);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} 

		if (request.getServletPath().equals("/organisateur")) {
			String pseudo = (String) request.getParameter("pseudo");
			ParticipantManager participantManager = new ParticipantManager();
			SiteManager siteManager = new SiteManager();

			try {
				Participant profil = participantManager.selectOne(pseudo);
				Site site = siteManager.selectById(profil.getSiteRattachement().getIdSite());
				request.setAttribute("profil", profil);
				request.setAttribute("site", site);
			} catch (BusinessException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
