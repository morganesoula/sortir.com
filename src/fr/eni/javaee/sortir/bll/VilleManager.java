package fr.eni.javaee.sortir.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Ville;
import fr.eni.javaee.sortir.dal.DAOFactory;
import fr.eni.javaee.sortir.dal.VilleDAO;

public class VilleManager {
	
	private VilleDAO villeDAO;
	
	public VilleManager() 
	{
		this.villeDAO = DAOFactory.getVilleDAO();
	}
	
	public List<Ville> selectAll() throws BusinessException
	{
		return this.villeDAO.selectAll();
	}
	
	public Ville selectById(int idVille) throws BusinessException, SQLException 
	{
		return this.villeDAO.selectOneById(idVille);
	}
	
	public void insert(Ville ville) throws BusinessException 
	{
		this.villeDAO.insert(ville);
	}
	
	public void delete(int id) throws SQLException, BusinessException
	{
		this.villeDAO.delete(id);
	}
	
	public Ville update(Ville ville) throws BusinessException, SQLException 
	{
		return this.villeDAO.update(ville);
	}

}
