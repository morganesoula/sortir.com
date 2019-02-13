package fr.eni.javaee.sortir.dal;

import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Lieu;

public interface LieuDAO {

	List<Lieu> selectAll() throws BusinessException;
	
	Lieu selectOneById(int idLieu) throws BusinessException;

	void insert(Lieu lieu) throws BusinessException;

	void update(Lieu lieu) throws BusinessException;

	void delete(int idLieu) throws BusinessException;	

}
