package fr.eni.javaee.sortir.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

/**
 * Servlet implementation class ServletVisualiserRepas
 */
@WebServlet("/updateProfil")
public class ServletGestionProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int error = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGestionProfil() {
		super();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recherche d'un participant
		HttpSession session = request.getSession(true);
		Object value = session.getAttribute("currentSessionParticipant");


		if (value != null) {
			Participant participantEnCours = (Participant) value;
			try {
				
				// Affichage des Sites de rattachement disponible en BDD
				SiteManager siteManager = new SiteManager();
				List<Site> listeSites = null;
				listeSites = siteManager.selectAll();
				request.setAttribute("listeSites", listeSites);
				
				request.setAttribute("participantEnCours", participantEnCours);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formProfil.jsp");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String messageErreurMDP = "Les mots de passe ne sont pas identiques";

		//Je lis les paramètres 
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(true);
		Participant participantEnCours = (Participant) session.getAttribute("currentSessionParticipant");

		
		// On récupère les valeurs saisies à l'écran
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String pseudo = request.getParameter("pseudo");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordVerif = request.getParameter("passwordVerif");
		int idSite = Integer.parseInt(request.getParameter("idSite"));

		// On met à jour le profil
		ParticipantManager participantManager = new ParticipantManager();

		try {
			Participant participantUpdated = new Participant();
			participantUpdated.setIdparticipant(participantEnCours.getIdparticipant());
			participantUpdated.setNom(nom);
			participantUpdated.setPrenom(prenom);
			participantUpdated.setPseudo(pseudo);
			participantUpdated.setTelephone(telephone);
			participantUpdated.setEmail(email);

			
			// Récupération, par un compteur, des erreurs relatives au Mot de Passe
			if (!password.isEmpty() && passwordVerif.equals(password)) 
			{
				participantUpdated.setMotDePasse(password);
			} else if (!passwordVerif.equals(password))
			{
				error++;				
			}

			SiteManager sitemanager = new SiteManager();
			Site site = sitemanager.selectById(idSite);
			participantUpdated.setSiteRattachement(site);


			// Mise à jour de la session
			session.setAttribute("currentSessionParticipant", participantUpdated);

			
			// Gestion des erreurs sauvegardées par un compteur
			if (error != 0)
			{
				request.setAttribute("erreurMDP", messageErreurMDP);
				participantManager.modifierSansMDP(participantUpdated);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formProfil.jsp");

				//On conserve les données pré-remplies à l'affichage du message d'erreur
				request.setAttribute("participantEnCours", participantUpdated);
				rd.forward(request, response);
				
				// On réinitialise le compteur d'erreur
				error = 0;
			} else {
				
				// On met à jour le profil
				participantManager.modifier(participantUpdated);
				
				RequestDispatcher rd = request.getRequestDispatcher("/profil");
				rd.forward(request, response);
			}

		} catch (BusinessException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
