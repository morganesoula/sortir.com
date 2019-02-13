package fr.eni.javaee.sortir.bll;

import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Inscription;
import fr.eni.javaee.sortir.dal.DAOFactory;
import fr.eni.javaee.sortir.dal.InscriptionDAO;

public class InscriptionManager {
	
	private InscriptionDAO inscriptionDAO;
	
	public InscriptionManager() 
	{
		this.inscriptionDAO = DAOFactory.getInscriptionDAO();
	}
	
	public List<Inscription> selectAll() throws BusinessException
	{
		return this.inscriptionDAO.selectAll();
	}
	
	public List<Inscription> selectById(int idSortie) throws BusinessException
	{
		return this.inscriptionDAO.selectByIdSortie(idSortie);
	}
	
	
	

}
