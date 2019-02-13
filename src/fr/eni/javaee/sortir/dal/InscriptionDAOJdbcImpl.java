package fr.eni.javaee.sortir.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Inscription;
import fr.eni.javaee.sortir.bo.Participant;
import fr.eni.javaee.sortir.bo.Sortie;

public class InscriptionDAOJdbcImpl implements InscriptionDAO {


	/* REQUETES */
	private static final String SELECT_BY_ID_SORTIE = 
			"SELECT INSCRIPTIONS.date_inscription, idSortie, organisateur, PARTICIPANTS.nom as nomOrganisateur, prenom, pseudo "
			+ "FROM INSCRIPTIONS "
			+ "INNER JOIN SORTIES ON sorties_idSortie=idSortie "
			+ "INNER JOIN PARTICIPANTS ON participants_idParticipant=idParticipant "
			+ "WHERE idSortie=?";
	

	/**
	 * Méthode qui sélectionne tous les éléments de la table INSCRIPTIONS
	 */
	@Override
	public List<Inscription> selectByIdSortie(int idSortie) throws BusinessException {
		List<Inscription> listesInscriptions = new ArrayList<Inscription>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_SORTIE);
			pstmt.setInt(1, idSortie);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				listesInscriptions.add(this.map(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listesInscriptions;
		
	}
	
	
	



	private Inscription map(ResultSet rs) throws SQLException {
		Inscription inscription = new Inscription();
		inscription.setDateInscription(rs.getTimestamp("date_inscription"));
		
		Sortie sortie = new Sortie();
		sortie.setIdSortie(rs.getInt("idSortie"));
		
		inscription.setIdSortie(sortie);
		Participant organisateur = new Participant();
		organisateur.setIdparticipant(rs.getInt("organisateur"));
		organisateur.setNom(rs.getString("nomOrganisateur"));
		organisateur.setPrenom(rs.getString("prenom"));
		organisateur.setPseudo(rs.getString("pseudo"));
		
		inscription.setIdParticipant(organisateur);
		
		return inscription;
	}





	@Override
	public List<Inscription> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
