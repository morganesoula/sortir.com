package fr.eni.javaee.sortir.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Participant;

public interface ParticipantDAO {
	List<Participant> selectAll() throws BusinessException;

	void insert(Participant participant) throws BusinessException;

	Participant selectByPseudo(String pseudo) throws SQLException;

	Participant update(Participant participant) throws SQLException;
	
	Participant updateWithoutMDP(Participant participant) throws SQLException;

	void delete(int idParticipant) throws BusinessException;

	Participant login(String pseudo) throws SQLException;

	Participant selectById(int idParticipant) throws SQLException;

}
