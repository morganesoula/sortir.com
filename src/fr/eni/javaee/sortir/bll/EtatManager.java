package fr.eni.javaee.sortir.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Etat;
import fr.eni.javaee.sortir.dal.DAOFactory;
import fr.eni.javaee.sortir.dal.EtatDAO;

public final class EtatManager {

	private EtatDAO etatDAO;

	public EtatManager() 
	{
		this.etatDAO=DAOFactory.getEtatDAO();
	}

	public List<Etat> selectAll() throws BusinessException
	{
		return this.etatDAO.selectAll();
	}
	
	public Etat selectAllById(int idEtat) throws BusinessException, SQLException
	{
		return this.etatDAO.selectById(idEtat);
	}

	public void supprimer(int id) throws BusinessException
	{
		this.etatDAO.delete(id);
	}

}
