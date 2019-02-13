package fr.eni.javaee.sortir.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Ville;

public interface VilleDAO {

	List<Ville> selectAll() throws BusinessException ;

	Ville selectOneByName(String nom_ville) throws BusinessException;
	
	Ville selectOneById(int idVille) throws BusinessException;

	void insert(Ville ville) throws BusinessException;

	Ville update(Ville ville) throws SQLException;

	void delete(int idVille) throws BusinessException;

}
