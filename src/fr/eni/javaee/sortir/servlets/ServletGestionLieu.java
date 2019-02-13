package fr.eni.javaee.sortir.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bll.LieuManager;
import fr.eni.javaee.sortir.bll.VilleManager;
import fr.eni.javaee.sortir.bo.Lieu;
import fr.eni.javaee.sortir.bo.Participant;
import fr.eni.javaee.sortir.bo.Ville;

/**
 * Servlet implementation class ServletNouveauLieu
 */
@WebServlet("/nouveauLieu")
public class ServletGestionLieu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGestionLieu() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Affichage du formulaire d'ajout d'un lieu
		if(request.getServletPath().equals("/nouveauLieu"))
		{
			HttpSession session = request.getSession(true);
			Object value = session.getAttribute("currentSessionParticipant");

			if (value != null) {
				Participant participantEnCours = (Participant) value;
				request.setAttribute("participantEnCours", participantEnCours);

				try {
					// Affichage du select Ville
					VilleManager villeManager = new VilleManager();
					List<Ville> listeVilles = villeManager.selectAll();
					request.setAttribute("listeVilles", listeVilles);
				} catch (BusinessException e)
				{
					e.printStackTrace();
				}

				request.setAttribute("title", "Ajouter");

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formLieu.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Affiche le formulaire d'ajout d'un lieu
		if(request.getServletPath().equals("/nouveauLieu"))
		{
			request.setAttribute("title", "Ajouter");

			request.setCharacterEncoding("UTF-8");

			Lieu newLieu = new Lieu();
			newLieu.setNom(request.getParameter("nom"));
			newLieu.setRue(request.getParameter("rue"));
			try {
				newLieu.setLatitude(Float.valueOf(request.getParameter("latitude")));
				newLieu.setLongitude(Float.valueOf(request.getParameter("longitude")));
			} catch (NullPointerException nfe)
			{
				newLieu.setLatitude(1F);
				newLieu.setLongitude(1F);
			}


			Ville newVille = new Ville();
			try
			{
				newVille.setIdVille(Integer.parseInt(request.getParameter("idVille")));
			} catch (NumberFormatException nfe)
			{
				newVille.setIdVille(1);
			}

			newLieu.setIdVille(newVille);

			LieuManager lieuManager = new LieuManager();
			try 
			{
				lieuManager.ajouter(newLieu);
			} catch (BusinessException e)
			{
				e.printStackTrace();
			}

			response.sendRedirect("./nouvelleSortie");
		}		
	}

}
