package fr.eni.javaee.sortir.dal;

import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Inscription;

public interface InscriptionDAO {

	List<Inscription> selectAll() throws BusinessException;

	List<Inscription> selectByIdSortie(int idSortie) throws BusinessException;


	

}
