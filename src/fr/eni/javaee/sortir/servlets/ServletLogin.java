package fr.eni.javaee.sortir.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import fr.eni.javaee.sortir.bll.ParticipantManager;
import fr.eni.javaee.sortir.bo.Participant;

/**
 * Servlet implementation of login
 */
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ParticipantManager participantManager = new ParticipantManager();
	Participant participant;

	public ServletLogin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = null;
		String cookieSelected = null;

		cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("loginCookie")) {
					cookieSelected = c.getValue();
				}
			}
		}
		request.setAttribute("login", cookieSelected);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(request, response);
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message = "Erreur d'identifiant/mot de passe";

		try {

			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String remember = request.getParameter("remember");

			// Tentative de connexion
			participant = participantManager.login(login);

			if (participant != null) {
				if (checkPassword(password, participant.getMotDePasse())) {
					HttpSession session = request.getSession();
					session.setAttribute("currentSessionParticipant", participant);

					if ("ON".equals(remember)) {
						Cookie cookie = new Cookie("loginCookie", request.getParameter("login"));
						cookie.setMaxAge(24 * 60 * 60);
						response.addCookie(cookie);
					}

					RequestDispatcher rd = request.getRequestDispatcher("/sorties");
					rd.forward(request, response);
				} else {
					response.sendRedirect("/SortirENI/login?message=" + URLEncoder.encode(message, "UTF-8"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	
	/**
	 * Méthode qui permet de vérifier que le MdP en base de donnée est bien le même que celui qui est saisi
	 * 
	 * @param password
	 * @param hashedPassword
	 * @return
	 */
	private boolean checkPassword(String password, String hashedPassword) {
		boolean isValid = false;

		if (password == null) {
			throw new IllegalArgumentException("Merci de renseigner un mot de passe correct");
		} else if (password.length() == 0) {
			throw new IllegalArgumentException("Mot de passe trop court");
		}
		isValid = BCrypt.checkpw(password, hashedPassword);
		return isValid;
	}

}
