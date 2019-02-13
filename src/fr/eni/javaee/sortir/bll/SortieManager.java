package fr.eni.javaee.sortir.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Sortie;
import fr.eni.javaee.sortir.dal.DAOFactory;
import fr.eni.javaee.sortir.dal.SortieDAO;

public final class SortieManager {

	private SortieDAO sortieDAO;

	public SortieManager() 
	{
		this.sortieDAO=DAOFactory.getSortieDAO();
	}

	public List<Sortie> selectAll() throws BusinessException
	{
		return this.sortieDAO.selectAll();
	}
	
	public Sortie selectAllById(int idSortie) throws BusinessException
	{
		return this.sortieDAO.selectOneById(idSortie);
	}
	
	public Sortie update(Sortie sortie) throws BusinessException, SQLException
	{
		return this.sortieDAO.update(sortie);
	}
	
	public Sortie updateEtat(Sortie sortie) throws BusinessException, SQLException
	{
		return this.sortieDAO.updateEtatSortie(sortie);
	}
	
	public void insert(Sortie sortie) throws BusinessException
	{
		this.sortieDAO.insert(sortie);
	}

	public void supprimer(int id) throws BusinessException
	{
		this.sortieDAO.delete(id);
	}

}
