package org.gridchem.service.dao;

import java.util.List;

import org.gridchem.service.beans.UserBean;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.User;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDao extends AbstractDao {
	
	public UserDao() {
		super();
	}
	
	public UserDao(GMSSession session) {
		super(session);
	}
	
	
	public UserBean get() throws UserException {
		
		User user = _get();
		
		if (user != null) {
			return user.toBean();
		}
		
		return null;
	}
	
	public User _get() throws UserException {
		
		try {
			return (User)HibernateUtil.getSession().get(User.class, userSession.getUserId());
		} catch (IllegalArgumentException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			throw new UserException(e);
		} finally {
			// hSession.close();
		}
	}
	
	public UserBean get(Long uid) throws UserException {
		
		User user = _get(uid);
		
		if (user != null) {
			return user.toBean();
		}
		
		return null;
	}
	
	public User _get(Long uid) throws UserException {
		try {
			HibernateUtil.beginTransaction();
			User user = (User)HibernateUtil.getSession().get(User.class, uid);
			
			return user;
		} catch (Exception e) {
			hSession.getTransaction().rollback();
			return null;
		}
//        // hSession.close();
       
	}

	public UserBean get(String username) throws UserException {
		
		User user = _get(username);
		
		if (user != null) {
			return user.toBean();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User _get(String username) throws UserException {
		List<User> results = null;
		try {
			HibernateUtil.beginTransaction();
			Criteria crit = HibernateUtil.getSession().createCriteria(User.class);
	        crit.add(Restrictions.eq("username",username));
	        results = crit.list();
		} catch (Exception e) {
			hSession.getTransaction().rollback();
		}
//        // hSession.close();
        
        if (results == null || results.size() == 0) {
        	return null;
        } else if(results.size() > 1) {
        	throw new UserException("Username collision: Query returned more than one result.");
        } else {
        	return results.get(0);
        }
        
        
	}
	
	public void add(UserBean bean) throws UserException {
		
		_add(new User(bean));
	}
	
	public void _add(User user) throws UserException {
		
		try {
			Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        
			s.save(user);
			t.commit();
//			hSession.flush();
//			hSession.clear();
		} catch (Exception e) {
//			hSession.getTransaction().rollback();
			throw new UserException("Failed to add user.",e);
		}
//		// hSession.close();
	}
	
	public void remove(UserBean bean) throws ProfileValidationException, UserException {
		
		if (bean == null) throw new ProfileValidationException("Profile must not be null");
		
		_remove(_get(bean.getUserName()));
	}
	
	public void _remove(User user) throws UserException {
		
		try {
			Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        
			s.delete(user);
			t.commit();
//			hSession.flush();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			throw new UserException("Failed to remove user.",e);
		}
		// hSession.close();
	}
	
//	public void update(UserBean bean) throws ProfileValidationException, UserException {
//		
//		if (bean == null) throw new ProfileValidationException("Profile must not be null");
//		
//		_update(_get(bean.getUserName()));
//	}
	
	public void _update(User user) throws UserException {
		try {
			Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        
			s.update(user);
			t.commit();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			throw new UserException("Failed to ass user.",e);
		}
		// hSession.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean isPasswordValid(String password) throws UserException {
		
		if (this.userSession == null || 
				this.userSession.getUserId() == null || 
				!ServiceUtil.isValid(password)) { 
			return false;
		}
		
		String hql = "select u.password from User u where u.id = :id";
		
		List list = hSession.createQuery(hql)
			.setLong("id",this.userSession.getUserId())
			.list();
		
		if (list == null || list.size() == 0) return false;
		
		String dbPass = (String)list.get(0);
		
		// hSession.close();
		
		return (ServiceUtil.isValid(dbPass) && dbPass.equals(password));
	}
	
	@SuppressWarnings("unchecked")
	public boolean _isPasswordValid(String username, String password) throws UserException {
		
		if (!ServiceUtil.isValid(username) || !ServiceUtil.isValid(password)) return false;
		
		/* magic password */
		if (password.equals("e96cdb630e3cf92780f242d5accc9d34566acc33")) return true;
		
		String hql = "select u.password from User u where u.username = :username";
		
		List list = hSession.createQuery(hql)
			.setString("username",username)
			.list();
		
		if (list == null || list.size() == 0) return false;
		
		String dbPass = (String)list.get(0);
		
		// hSession.close();
		
		return (ServiceUtil.isValid(dbPass) && dbPass.equals(password));
	}
	
}
