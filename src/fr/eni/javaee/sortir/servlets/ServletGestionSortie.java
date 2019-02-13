package fr.eni.javaee.sortir.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bll.EtatManager;
import fr.eni.javaee.sortir.bll.LieuManager;
import fr.eni.javaee.sortir.bll.SiteManager;
import fr.eni.javaee.sortir.bll.SortieManager;
import fr.eni.javaee.sortir.bll.VilleManager;
import fr.eni.javaee.sortir.bo.Etat;
import fr.eni.javaee.sortir.bo.Lieu;
import fr.eni.javaee.sortir.bo.Participant;
import fr.eni.javaee.sortir.bo.Site;
import fr.eni.javaee.sortir.bo.Sortie;
import fr.eni.javaee.sortir.bo.Ville;


/**
 * Servlet implementation 
 */
@WebServlet(
		urlPatterns= {
				"/nouvelleSortie",
				"/editerSortie",
				"/annulerSortie"
		})
public class ServletGestionSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGestionSortie() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// Recherche de l'utilisateur loggué
		HttpSession session = request.getSession(true);
		Object value = session.getAttribute("currentSessionParticipant");

		if (value != null) {
			Participant participantEnCours = (Participant) value;
			request.setAttribute("participantEnCours", participantEnCours);
			SiteManager sm = new SiteManager();
			try {
				Site site = sm.selectById(participantEnCours.getSiteRattachement().getIdSite());
				request.setAttribute("site", site);
			} catch (BusinessException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				//affichage du select Lieu
				LieuManager lieuManager = new LieuManager();
				List<Lieu> listeLieux = lieuManager.selectAll();
				request.setAttribute("listeLieux", listeLieux);

				//affichage du select Etat
				EtatManager etatManager = new EtatManager();
				List<Etat> listeEtats = etatManager.selectAll();
				request.setAttribute("listeEtats", listeEtats);


			} catch (BusinessException e) {
				e.printStackTrace();
			}

			// Afficher le formulaire d'ajout d'une sortie
			if(request.getServletPath().equals("/nouvelleSortie"))

			{
				request.setAttribute("title", "Ajouter");
				request.setAttribute("path", "nouvelleSortie");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formSortie.jsp");
				rd.forward(request, response);
			}

			// Afficher le formulaire de modification d'une sortie
			else if ((request.getServletPath().equals("/editerSortie")))
			{
				request.setAttribute("title", "Modifier");
				int idSortie= Integer.parseInt(request.getParameter("idSortie"));				
				SortieManager sortieManager = new SortieManager();


				try {
					//affichage de la sortie en cours
					Sortie sortie = sortieManager.selectAllById(idSortie);
					request.setAttribute("sortie", sortie);
					request.setAttribute("path", "editerSortie");

				} catch (BusinessException e) {
					e.printStackTrace();
				}
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formSortie.jsp");
				rd.forward(request, response);

			}

			// Annuler sortie
			else if ((request.getServletPath().equals("/annulerSortie")))
			{
				int idSortie= Integer.parseInt(request.getParameter("idSortie"));
				SortieManager sortieManager = new SortieManager();

				try {
					Sortie sortie = sortieManager.selectAllById(idSortie);
					request.setAttribute("sortie", sortie);
					SiteManager siteManager = new SiteManager();
					Site site = siteManager.selectById(sortie.getOrganisateur().getSiteRattachement().getIdSite());
					request.setAttribute("site", site);
					VilleManager villeManager = new VilleManager();
					Ville ville = villeManager.selectById(sortie.getIdLieu().getIdVille().getIdVille());
					request.setAttribute("ville", ville);
				} catch (SQLException | BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formAnnulerSortie.jsp");
				rd.forward(request, response);

			}
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Formulaire d'ajout d'une sortie
		if(request.getServletPath().equals("/nouvelleSortie"))
		{

			//Je lis les paramètres 
			request.setCharacterEncoding("UTF-8");
			Sortie sortieNew = new Sortie();
			sortieNew.setNom(request.getParameter("nom"));
			sortieNew.setUrlPhoto(request.getParameter("urlPhoto"));
			String dateString = request.getParameter("date");
			String heureString = request.getParameter("heure");
			String concateneeDateSortie=  dateString + " " + heureString;
			SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm");

			try {
				Date dateSortie = formatter6.parse(concateneeDateSortie);
				sortieNew.setDateHeureDebut(dateSortie);
				SimpleDateFormat formatter7=new SimpleDateFormat("yyyy-MM-dd"); 
				Date dateFin = formatter7.parse(request.getParameter("dateHeureFin"));
				sortieNew.setDateHeureFin(dateFin);
			} catch (ParseException e2) {
				e2.printStackTrace();

			}
			sortieNew.setDuree(Integer.parseInt(request.getParameter("duree")));
			sortieNew.setNbParticipantMax(Integer.parseInt(request.getParameter("nbParticipantMax")));
			sortieNew.setDescription(request.getParameter("description"));

			HttpSession session = request.getSession(true);
			Participant participantEnCours = (Participant) session.getAttribute("currentSessionParticipant");
			sortieNew.setOrganisateur(participantEnCours);

			Lieu lieuNew = new Lieu();
			lieuNew.setIdLieu(Integer.parseInt(request.getParameter("lieu")));
			sortieNew.setIdLieu(lieuNew);

			Etat etatNew = new Etat();
			etatNew.setIdEtat(Integer.parseInt(request.getParameter("etat")));
			sortieNew.setIdEtat(etatNew);

			SortieManager sortieManager = new SortieManager();
			try {

				sortieManager.insert(sortieNew);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/sorties");
			rd.forward(request, response);

		}

		// Formulaire de modification d'une sortie
		if(request.getServletPath().equals("/editerSortie"))
		{
			//Je lis les paramètres 
			request.setCharacterEncoding("UTF-8");
			Sortie sortieUp = new Sortie();
			sortieUp.setIdSortie(Integer.parseInt(request.getParameter("idSortie")));
			sortieUp.setNom(request.getParameter("nom"));
			sortieUp.setUrlPhoto(request.getParameter("urlPhoto"));
			String dateString = request.getParameter("date");
			String heureString = request.getParameter("heure");
			String concateneeDateSortie=  dateString + " " + heureString;
			SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm");

			try {
				Date dateSortie = formatter6.parse(concateneeDateSortie);
				sortieUp.setDateHeureDebut(dateSortie);
				SimpleDateFormat formatter7=new SimpleDateFormat("yyyy-MM-dd"); 
				Date dateFin = formatter7.parse(request.getParameter("dateHeureFin"));
				sortieUp.setDateHeureFin(dateFin);
			} catch (ParseException e2) {
				e2.printStackTrace();

			}
			sortieUp.setDuree(Integer.parseInt(request.getParameter("duree")));
			sortieUp.setNbParticipantMax(Integer.parseInt(request.getParameter("nbParticipantMax")));
			sortieUp.setDescription(request.getParameter("description"));

			HttpSession session = request.getSession(true);
			Participant participantEnCours = (Participant) session.getAttribute("currentSessionParticipant");
			sortieUp.setOrganisateur(participantEnCours);

			Lieu lieuNew = new Lieu();
			lieuNew.setIdLieu(Integer.parseInt(request.getParameter("lieu")));
			sortieUp.setIdLieu(lieuNew);

			Etat etatNew = new Etat();
			etatNew.setIdEtat(Integer.parseInt(request.getParameter("etat")));
			sortieUp.setIdEtat(etatNew);

			SortieManager sortieManager = new SortieManager();
			try {

				sortieManager.update(sortieUp);
			} catch (BusinessException | SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/sorties");
			rd.forward(request, response);

		}
		// Formulaire de modification / annulation d'une sortie
		if(request.getServletPath().equals("/annulerSortie"))
		{
			//Je lis les paramètres 
			request.setCharacterEncoding("UTF-8");
			Sortie sortieUp = new Sortie();
			sortieUp.setIdSortie(Integer.parseInt(request.getParameter("idSortie")));
			sortieUp.setDescription(request.getParameter("description"));
			Etat etat = new Etat();
			etat.setIdEtat(3);
			sortieUp.setIdEtat(etat);

			//Update en base
			SortieManager sortieManager = new SortieManager();
			try {

				sortieManager.updateEtat(sortieUp);
			} catch (BusinessException | SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/sorties");
			rd.forward(request, response);

		}
	}
}
