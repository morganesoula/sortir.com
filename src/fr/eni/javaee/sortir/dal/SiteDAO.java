package fr.eni.javaee.sortir.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Site;

public interface SiteDAO {

	List<Site> selectAll() throws BusinessException;

	void insert(Site site) throws BusinessException;

	Site update(Site site) throws BusinessException;

	void delete(int idSite) throws BusinessException;

	Site selectById(int idSite) throws SQLException;

}
