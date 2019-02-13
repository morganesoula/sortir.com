package fr.eni.javaee.sortir.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Etat;

public interface EtatDAO {

	List<Etat> selectAll() throws BusinessException;

	void insert(Etat etat) throws BusinessException;

	Etat update(Etat etat) throws BusinessException, SQLException;

	void delete(int idEtat) throws BusinessException;

	Etat selectById(int idEtat) throws SQLException;

}
