package controllers;

import java.util.List;

import dao.PartenariatDao;
import models.*;
@WebServlet(urlPatterns = "/controller/partenariats/listerpartenariats/*")
public class listerPartenariats extends HttpServlet {
	
	
		/** SERVICE_LOG : Logger */
		private static final Logger SERVICE_LOG = LoggerFactory.getLogger(ChercherCovoiturage.class);

		/**
		 * Methode doGet qui recupère toutes les annonces de covoiturage quand
		 * l'utilisateur accede à l'url /gestion-transports/collaborateur/annonces/*
		 * La liste des annonce retournée et traitée pour affichage par la JSP
		 * contient toutes les annonces de covoiturages qui n'ont pas été émises par
		 * l'utilisateur courant et dont la date de départ est antérieure à la date
		 * du jour
		 * 
		 * Fonctionnement de la méthode: _ récupère l'éléments utilisateur courant
		 * (pour savoir s'il est admin, collaborateur ou chauffeur) _ crée avec les
		 * valeurs "indeterminé" les variables
		 * lieuDeDestination,lieuDeDepart,dateDeDepart NOTA BENE : ces variables
		 * correspondent aux critères utilisés dans la classe
		 * ChercherCovoiturageAvecCritères _ instancie la DAO CovoiturageDao qui
		 * appelera la méthode "recupererLesAnnoncesDisponiblesPourUtilisateur" pour
		 * récupérer une liste d'annonces de covoiturage correspondant aux critères
		 * evoqués plus haut. _stocke les variables
		 * listeDesAnnonces,lieuDeDepart,lieuDeDestination,idUtilisateur,dateDeDepart
		 * qui seront traités par la JSP
		 * "/WEB-INF/collaborateur/chercherCovoiturage.jsp" pour affichage
		 * 
		 *
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			HttpSession session = req.getSession(false);

			//Employe utilisateurCourant = (Employe) session.getAttribute("utilisateur");
			Integer pagination = (Integer) req.getParameter("pagination");
			
			
			PartenariatDao partenariatDao = new PartenariatDao();
			List<Partenariat> listeDesPartenariats = partenariatDao.recupererLesPartenariats(pagination);
			
			req.setAttribute("listeDesPartenariats", listeDesPartenariats);
			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/WEB-INF/collaborateur/listerPartenariats.jsp");
			requestDispatcher.forward(req, resp);
		}

}
