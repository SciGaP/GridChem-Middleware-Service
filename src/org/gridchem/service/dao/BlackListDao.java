/**
 * 
 */
package org.gridchem.service.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gridchem.service.beans.BlackListBean;
import org.gridchem.service.exceptions.BlackListException;
import org.gridchem.service.exceptions.SoftwareException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.BlackListEntry;
import org.gridchem.service.model.Software;
import org.gridchem.service.model.User;
import org.gridchem.service.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * @author dooley
 *
 */
@SuppressWarnings({ "unchecked" })
public class BlackListDao {
	
	private Session session;
	
	public BlackListDao() {
		
	}
	
	public void add(BlackListBean bean) {
		
		try {
			session = HibernateUtil.getSession();
			// populate the blacklist entry
			BlackListEntry entry = new BlackListEntry();
			entry.setCreated(new Date());
			entry.setEnabled(bean.isEnabled());
			entry.setLastUpdated(new Date());
			Software software = new SoftwareDao()._get(bean.getSoftware());
			if (software == null) {
				throw new SoftwareException("No software found matching the name: " + bean.getSoftware());
			}
			entry.setSoftware(software);
			
			User user = new UserDao()._get(bean.getUsername());
			if (user == null) {
				throw new UserException("No user found matching the usernname: " + bean.getUsername());
			}
			entry.setUser(user);
			entry.setId(new BlackListEntry.Id(user.getId(), software.getId()));
			
			_add(entry);
			
		} catch (Exception e) {
			throw new BlackListException("Failed to add blacklist entry.",e);
		}
	}

	public void _add(BlackListEntry entry) {
		
		if (entry.getUser() == null)
			throw new BlackListException("Invalid user");
		
		if (entry.getSoftware() == null) 
			throw new BlackListException("Invalid software");
		
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Transaction t = session.getTransaction();
			session.saveOrUpdate(entry);
			t.commit();
			session.flush();
		} catch (Exception e) {
			throw new BlackListException("Failed to add blacklist entry.",e);
		}
	}

	public void remove(BlackListBean bean) {
		Software sw = new SoftwareDao()._get(bean.getSoftware());
		if (sw == null) {
			throw new BlackListException("Invalid software name");
		}
		User user = new UserDao()._get(bean.getUsername()); 
		if (user == null) {
			throw new BlackListException("Invalid username");
		}
		
		_remove(_get(user.getId(), sw.getId()));
//		
//		try {
//			
//			session = HibernateUtil.getSession();
//			session.beginTransaction();
//						
//			String hql = "delete from BlackListEntry " +
//					"where user.username = :username " +
//					"and software.name = :sw ";
//			
//			session.createQuery(hql)
//				.setString("username", bean.getUsername())
//				.setString("sw",bean.getSoftware())
//				.executeUpdate();
//			
//		} catch (Exception e) {
//			throw new BlackListException("Failed to remove blacklist entry.",e);
//		}
	}
	
	public void _remove(BlackListEntry entry) {
		
		if (entry.getUser() == null)
			throw new BlackListException("Invalid user");
		
		if (entry.getSoftware() == null) 
			throw new BlackListException("Invalid software");
		
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Transaction t = session.getTransaction();
			session.delete(entry);
			t.commit();
			
		} catch (HibernateException e) {
			throw new BlackListException("Failed to remove blacklist entry.",e);
		} 
	}
	
	public void update(BlackListBean bean) {
		
		if (new SoftwareDao().get(bean.getSoftware()) == null) {
			throw new BlackListException("Invalid software name");
		}
		
		if (new UserDao()._get(bean.getUsername()) == null) {
			throw new BlackListException("Invalid username");
		}
		
		try {
			String hql = "from BlackListEntry ble " + 
				"where ble.user.username = :username " +
				"and ble.software.name = :sw";
			
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			List<BlackListEntry> entries = session.createQuery(hql)
				.setString("username", bean.getUsername())
				.setString("sw", bean.getSoftware()).list();
			
			if (entries.isEmpty()) 
				throw new BlackListException("No matching blacklist entry found.");
			
			BlackListEntry entry = entries.get(0);
			entry.setLastUpdated(new Date());
			entry.setEnabled(bean.isEnabled());
			
			session.update(entry);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			throw new BlackListException("Failed to update blacklist entry.", e);
		} 
	}

	public void _update(BlackListEntry entry) {
		
		if (entry.getUser() == null)
			throw new BlackListException("Invalid user");
		
		if (entry.getSoftware() == null) 
			throw new BlackListException("Invalid software");
		
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Transaction t = session.getTransaction();
			entry.setLastUpdated(new Date());
			session.update(entry);
			t.commit();
			
		} catch (HibernateException e) {
			throw new BlackListException("Failed to update blacklist entry.",e);
		} 
	}
	
	public BlackListEntry _get(Long userId, Long softwareId) {
		
		if (userId == null || softwareId == null) return null;
		
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			String hql = "from BlackListEntry ble " +
					"where ble.user.id = :uid " +
					"and ble.software.id = :swid";
			
			List<BlackListEntry> entries = session.createQuery(hql)
				.setLong("uid", userId)
				.setLong("swid",softwareId)
				.list();
			
			if (entries.isEmpty()) return null;
			
			return entries.get(0);
			
		} catch (Exception e) {
			throw new BlackListException("Exception looking up blacklist entry.", e);
		}
	}
	
	public BlackListEntry get(String username, String software) {
		
		if (username == null || software == null) return null;
		
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			String hql = "from BlackListEntry ble " +
					"where ble.user.username = :username " +
					"and ble.software.name = :sw";
			
			List<BlackListEntry> entries = session.createQuery(hql)
				.setString("username", username)
				.setString("sw",software)
				.list();
			
			if (entries.isEmpty()) return null;
			
			return entries.get(0);
			
		} catch (Exception e) {
			throw new BlackListException("Exception looking up blacklist entry.",e);
		}
	}
	
	public List<BlackListBean> get(String username) {
		
		List<BlackListBean> beans = new ArrayList<BlackListBean>();
		
		if (username == null) return beans;
		
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			String hql = "from BlackListEntry ble " +
					"where ble.user.username = :username";
			
			List<BlackListEntry> entries = session.createQuery(hql)
				.setString("username", username)
				.list();
			
			for (BlackListEntry blackListEntry: entries) {
				beans.add(blackListEntry.toBean());
			}
			
			return beans;
			
		} catch (Exception e) {
			throw new BlackListException("Exception looking up blacklist entry.",e);
		}
			
	}
	
	public List<BlackListEntry> _get(Long userId) {
		
		if (userId == null) return new ArrayList<BlackListEntry>();
		
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			List<BlackListEntry> results = (List<BlackListEntry>)session.createQuery("from BlackListEntry where user.id = :uid")
				.setLong("uid", userId).list();
			
			return results;
			
		} catch (HibernateException e) {
			throw new BlackListException("Failed to retrieve blacklist entries.",e);
		}
		
	}
	
	
	public static boolean userHasAccessToSoftware(String username, String software) throws BlackListException {
		
		if (new SoftwareDao().get(software) == null) {
			throw new BlackListException("Invalid software name");
		}
		
		if (new UserDao()._get(username) == null) {
			throw new BlackListException("Invalid username");
		}
		
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		try {
			
			String hql = "from BlackListEntry ble " +
					"where ble.user.username = :username " +
					"and ble.software.name = :sw ";
			
			List<BlackListEntry> results = (List<BlackListEntry>)session.createQuery(hql)
				.setString("username", username)
				.setString("sw",software)
				.list();
		
			for (BlackListEntry bl: results) {
				if (bl.isEnabled()) return false;
			}
			
			return true;
			
		} catch (HibernateException e) {
			throw new BlackListException("Exception looking up blacklist entries.",e);
		} finally {
			
		}
	}
	
	public static boolean userHasAccessToSoftware(Long userId, String software) throws BlackListException {
		
		if (new SoftwareDao().get(software) == null) {
			throw new BlackListException("Invalid software name");
		}
		
		HibernateUtil.beginTransaction();
		
		try {
			Session session = HibernateUtil.getSession();
			
			User user = (User)session.get(User.class,userId);
			if (user == null) {
				throw new BlackListException("Invalid username");
			}
			
			String hql = "from BlackListEntry ble " +
					"where ble.user.id = :userid " +
					"and ble.software.name = :sw ";
			
			List<BlackListEntry> results = (List<BlackListEntry>)session.createQuery(hql)
				.setLong("userid", userId)
				.setString("sw",software)
				.list();
			
			for (BlackListEntry bl: results) {
				if (bl.isEnabled()) return false;
			}
			
			return true;
			
		} catch (HibernateException e) {
			throw new BlackListException("Exception looking up blacklist entries.",e);
		} finally {
			
		}
	}
	
}
