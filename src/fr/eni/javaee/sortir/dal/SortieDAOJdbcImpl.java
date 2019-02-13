package fr.eni.javaee.sortir.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Etat;
import fr.eni.javaee.sortir.bo.Lieu;
import fr.eni.javaee.sortir.bo.Participant;
import fr.eni.javaee.sortir.bo.Site;
import fr.eni.javaee.sortir.bo.Sortie;
import fr.eni.javaee.sortir.bo.Ville;


public class SortieDAOJdbcImpl implements SortieDAO {

	/* REQUETES */
	private static final String SELECT_ALL = "SELECT idSortie, SORTIES.nom as nomSortie, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos, urlPhoto, "
			+ "organisateur, PARTICIPANTS.nom as nomOrganisateur, prenom, pseudo, sites_idSite,  "
			+ "lieux_idLieu, nom_lieu, rue, latitude, longitude, villes_idVille, "
			+ "etats_idEtat, libelle FROM SORTIES "
			+ "INNER JOIN PARTICIPANTS ON organisateur = idParticipant "
			+ "INNER JOIN SITES ON sites_idSite = idSite "
			+ "INNER JOIN LIEUX ON lieux_idLieu = idLieu "
			+ "INNER JOIN ETATS ON etats_idEtat = idEtat "
			+ "INNER JOIN VILLES ON villes_idVille = idVille "
			+ "ORDER BY datedebut ASC ";
	
	private static final String SELECT_ONE_BY_ID = "SELECT idSortie, SORTIES.nom as nomSortie, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos, urlPhoto, "
			+ "organisateur, PARTICIPANTS.nom as nomOrganisateur, prenom, pseudo, sites_idSite,  "
			+ "lieux_idLieu, nom_lieu, rue, latitude, longitude, villes_idVille, "
			+ "etats_idEtat, libelle FROM SORTIES "
			+ "INNER JOIN PARTICIPANTS ON organisateur = idParticipant "
			+ "INNER JOIN SITES ON sites_idSite = idSite "
			+ "INNER JOIN LIEUX ON lieux_idLieu = idLieu "
			+ "INNER JOIN ETATS ON etats_idEtat = idEtat "
			+ "INNER JOIN VILLES ON villes_idVille = idVille " 
			+ "WHERE idSortie=?";
	
	private static final String INSERT = "INSERT INTO SORTIES(nom, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos,urlPhoto, organisateur, lieux_idLieu, etats_idEtat)" 
			+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE = "UPDATE SORTIES SET nom=?, datedebut=?, duree=?, datecloture=?, nbinscriptionsmax=?, descriptioninfos=?,urlPhoto=?, organisateur=?, lieux_idLieu=?, etats_idEtat=? WHERE idSortie=?" ;
	
	private static final String UPDATE_ANNULER = "UPDATE SORTIES SET descriptioninfos=?, etats_idEtat=? WHERE idSortie=?";
	
	private static final String DELETE = "DELETE FROM SORTIES WHERE idSortie=?";
	
	
	
	/**
	 * Méthode qui sélectionne tous les éléments de la table SORTIES
	 */
	@Override
	public List<Sortie> selectAll() throws BusinessException {
		List<Sortie> listeSorties = new ArrayList<Sortie>();

		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) 
			{
				listeSorties.add(this.map(rs));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return listeSorties;
	}
	
	
	/**
	 * Méthode qui récupère tous les éléments de la table SORTIES pour un ID donné
	 */
	@Override
	public Sortie selectOneById(int idSortie) throws BusinessException {
		Sortie sortie = null;

		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ONE_BY_ID);
			pstmt.setInt(1, idSortie);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				sortie = this.map(rs);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sortie;
	}

	
	/**
	 * Méthode qui permet d'ajouter une sorties à la table SORTIES
	 */
	@Override
	public void insert(Sortie sortie) throws BusinessException {
		if (sortie == null) 
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, sortie.getNom());
			pstmt.setTimestamp(2,new Timestamp(sortie.getDateHeureDebut().getTime()));
			pstmt.setInt(3,sortie.getDuree());
			pstmt.setTimestamp(4, new Timestamp(sortie.getDateHeureFin().getTime()));
			pstmt.setInt(5,sortie.getNbParticipantMax());
			pstmt.setString(6, sortie.getDescription());
			pstmt.setString(7, sortie.getUrlPhoto());
			pstmt.setInt(8, sortie.getOrganisateur().getIdparticipant());
			pstmt.setInt(9, sortie.getIdLieu().getIdLieu());
			pstmt.setInt(10, sortie.getIdEtat().getIdEtat());
		
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next())
			{
				sortie.setIdSortie(rs.getInt(1));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

	}

	/**
	 * Méthode qui permet de modifier une sortie
	 */
	@Override
	public Sortie update(Sortie sortie) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, sortie.getNom());
			pstmt.setTimestamp(2,new Timestamp(sortie.getDateHeureDebut().getTime()));
			pstmt.setInt(3,sortie.getDuree());
			pstmt.setTimestamp(4, new Timestamp(sortie.getDateHeureFin().getTime()));
			pstmt.setInt(5,sortie.getNbParticipantMax());
			pstmt.setString(6, sortie.getDescription());
			pstmt.setString(7, sortie.getUrlPhoto());
			pstmt.setInt(8, sortie.getOrganisateur().getIdparticipant());
			pstmt.setInt(9, sortie.getIdLieu().getIdLieu());
			pstmt.setInt(10, sortie.getIdEtat().getIdEtat());
			pstmt.setInt(11, sortie.getIdSortie());
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return sortie;
	}

	/**
	 * Méthode qui permet de un état annulée d'une sortie existant 
	 */
	@Override
	public Sortie updateEtatSortie(Sortie sortie) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ANNULER);
			pstmt.setString(1, sortie.getDescription());
			pstmt.setInt(2, sortie.getIdEtat().getIdEtat());
			pstmt.setInt(3, sortie.getIdSortie());
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return sortie;
	}

	
	/**
	 * Méthode qui permet de supprimer un élément de la table SORTIES
	 */
	@Override
	public void delete(int idSortie) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, idSortie);
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 
	 * @param rs
	 * @return sortie
	 * @throws SQLException
	 */
	public Sortie map(ResultSet rs) throws SQLException {
		Sortie sortie = new Sortie();

		sortie.setIdSortie(rs.getInt("idSortie"));
		sortie.setNom(rs.getString("nomSortie"));
		sortie.setDateHeureDebut(rs.getTimestamp("datedebut"));
		sortie.setDuree(rs.getInt("duree"));
		sortie.setDateHeureFin(rs.getTimestamp("datecloture"));
		sortie.setNbParticipantMax(rs.getInt("nbinscriptionsmax"));
		sortie.setDescription(rs.getString("descriptioninfos"));
		sortie.setUrlPhoto(rs.getString("urlPhoto"));
		
		Participant organisateur = new Participant();
		organisateur.setIdparticipant(rs.getInt("organisateur"));
		organisateur.setNom(rs.getString("nomOrganisateur"));
		organisateur.setPrenom(rs.getString("prenom"));
		organisateur.setPseudo(rs.getString("pseudo"));
		Site siteRattachement = new Site();
		siteRattachement.setIdSite(rs.getInt("sites_idSite"));
		organisateur.setSiteRattachement(siteRattachement);
		sortie.setOrganisateur(organisateur);
		
		Lieu lieu = new Lieu();
		lieu.setIdLieu(rs.getInt("lieux_idLieu"));
		lieu.setNom(rs.getString("nom_lieu"));
		lieu.setRue(rs.getString("rue"));
		lieu.setLatitude(rs.getFloat("latitude"));
		lieu.setLongitude(rs.getFloat("longitude"));
		Ville ville = new Ville();
		ville.setIdVille(rs.getInt("villes_idVille"));
		lieu.setIdVille(ville);
		sortie.setIdLieu(lieu);
		
		Etat etat = new Etat();
		etat.setIdEtat(rs.getInt("etats_idEtat"));
		etat.setLibelle(rs.getString("libelle"));
		sortie.setIdEtat(etat);
		
		return sortie;
	}
	
	
	
	
}
