package fr.eni.javaee.sortir;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.sortir.bo.Participant;


/**
 * Servlet Filter implementation class FiltreRessourcesInterdites
 */
@WebFilter(
		urlPatterns= {"/villes", "/sites"},
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.INCLUDE, DispatcherType.FORWARD,
				DispatcherType.ERROR })

//, servletNames = { "ServletGestionSortie", "ServletNouveauLieu",
//		"ServletNouvelleVille", "ServletProfil", "ServletSites", "ServletSorties",
//		"ServletUpdateProfil", "ServletVilles" }

public class FiltreRole implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltreRole() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		Object value = session.getAttribute("currentSessionParticipant");
		Participant participantEnCours = (Participant) value;

		if (participantEnCours !=null && participantEnCours.isAdministrateur()) {
			// Laissons passer la requ�te vers la ressource qui est autoris�e
			chain.doFilter(request, response);
		} else {
			// renvoyons une 403 � l'utilisateur
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
