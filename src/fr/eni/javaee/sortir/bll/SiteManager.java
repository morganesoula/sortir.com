package fr.eni.javaee.sortir.bll;

import java.sql.SQLException;
import java.util.List;
import fr.eni.javaee.sortir.BusinessException;
import fr.eni.javaee.sortir.bo.Site;
import fr.eni.javaee.sortir.dal.DAOFactory;
import fr.eni.javaee.sortir.dal.SiteDAO;

public final class SiteManager {

	private SiteDAO siteDAO;

	public SiteManager() 
	{
		this.siteDAO=DAOFactory.getSiteDAO();
	}

	public List<Site> selectAll() throws BusinessException
	{
		return this.siteDAO.selectAll();
	}
	
	public Site selectById(int idSite) throws BusinessException, SQLException
	{
		return this.siteDAO.selectById(idSite);
	}
	
	public Site update(Site site) throws BusinessException, SQLException
	{
		return this.siteDAO.update(site);
	}

	public void insert(Site siteNew) throws BusinessException 
	{
		this.siteDAO.insert(siteNew);
	}
	
	public void delete(int id) throws BusinessException
	{
		this.siteDAO.delete(id);
	}

}
