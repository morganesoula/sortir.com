package fr.eni.javaee.sortir.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Sortie;

public interface SortieDAO {

	List<Sortie> selectAll() throws BusinessException;
	
	Sortie selectOneById(int idSortie) throws BusinessException;
	
	void delete(int idSortie) throws BusinessException;

	void insert(Sortie sortie) throws BusinessException;

	Sortie updateEtatSortie(Sortie sortie) throws SQLException;
	
	Sortie update(Sortie sortie) throws SQLException;

}
