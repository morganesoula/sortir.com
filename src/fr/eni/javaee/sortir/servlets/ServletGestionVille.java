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
import fr.eni.javaee.sortir.bll.VilleManager;
import fr.eni.javaee.sortir.bo.Participant;
import fr.eni.javaee.sortir.bo.Ville;

/**
 * Servlet implementation class ServletNouvelleVille
 */
@WebServlet(
		urlPatterns = {
				"/nouvelleVille",
				"/editerVille",
				"/supprimerVille"
		})
public class ServletGestionVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGestionVille() {
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


			if (request.getServletPath().equals("/nouvelleVille"))
			{
				request.setAttribute("title", "Ajouter");
				request.setAttribute("path", "nouvelleVille");

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formVille.jsp");
				rd.forward(request, response);
			}

			if ((request.getServletPath().equals("/editerVille")))
			{
				// Modification de la VILLE
				request.setAttribute("title", "Modifier");
				request.setAttribute("path", "editerVille");

				// Affichage de la VILLE en cours
				int idVille = Integer.parseInt(request.getParameter("idVille"));

				try 
				{
					VilleManager villeManager = new VilleManager();
					Ville villeToDisplay = villeManager.selectById(idVille);
					request.setAttribute("ville", villeToDisplay);
				} catch (BusinessException | SQLException e)
				{
					e.printStackTrace();
				}

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formVille.jsp");
				rd.forward(request, response);
			}

			if (request.getServletPath().equals("/supprimerVille"))
			{
				int idVille = Integer.parseInt(request.getParameter("idVille"));

				try 
				{
					VilleManager villeManager = new VilleManager();
					villeManager.delete(idVille);
				} catch (SQLException e) 
				{
					e.getMessage();
				} catch (BusinessException e)
				{
					e.printStackTrace();
				}

				RequestDispatcher rd = request.getRequestDispatcher("/villes");
				rd.forward(request, response);
			}

		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Affiche le formulaire d'ajout d'une ville
		if (request.getServletPath().equals("/nouvelleVille"))
		{
			request.setCharacterEncoding("UTF-8");
			Ville newVille = new Ville();

			newVille.setNom(request.getParameter("nom"));
			newVille.setCodePostal(request.getParameter("codePostal"));

			//Mise à jour en BDD
			VilleManager villeManager = new VilleManager();

			try
			{
				villeManager.insert(newVille);

			} catch (BusinessException e)
			{
				e.printStackTrace();
			}

			response.sendRedirect("./nouveauLieu");
		}

		if(request.getServletPath().equals("/editerVille"))
		{
			request.setCharacterEncoding("UTF-8");
			Ville villeUpdated = new Ville();

			villeUpdated.setIdVille(Integer.parseInt(request.getParameter("idVille")));
			villeUpdated.setNom(request.getParameter("nom"));
			villeUpdated.setCodePostal(request.getParameter("codePostal"));

			// Mise à jour en BDD
			VilleManager villeManager = new VilleManager();

			try {
				villeManager.update(villeUpdated);
			} catch (BusinessException | SQLException e)
			{
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/villes");
			rd.forward(request, response);
		}

		if (request.getServletPath().equals("/supprimerVille"))
		{

		}

	}
}
