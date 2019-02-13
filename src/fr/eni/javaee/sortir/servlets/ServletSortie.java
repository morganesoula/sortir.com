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
import fr.eni.javaee.sortir.bll.InscriptionManager;
import fr.eni.javaee.sortir.bll.ParticipantManager;
import fr.eni.javaee.sortir.bll.SiteManager;
import fr.eni.javaee.sortir.bll.SortieManager;
import fr.eni.javaee.sortir.bo.Participant;
import fr.eni.javaee.sortir.bo.Site;
import fr.eni.javaee.sortir.bo.Sortie;
@WebServlet(
		urlPatterns= {
				"/sortie"
		})
public class ServletSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSortie() {
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
		int idSortie = Integer.parseInt(request.getParameter("idSortie"));
		SortieManager sortieManager = new SortieManager();

		try {
			//affichage de la sortie en cours
			Sortie sortie = sortieManager.selectAllById(idSortie);
			request.setAttribute("sortie", sortie);
			
			InscriptionManager inscriptionManager = new InscriptionManager();
			request.setAttribute("listeInscriptions", inscriptionManager.selectById(idSortie));
		
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sortie.jsp");
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
