package com.demo.spring_web.service.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring_web.dao.impl.AbstractDao;
import com.demo.spring_web.model.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepository extends AbstractDao<String, PersistentLogin> implements PersistentTokenRepository {

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		// TODO Auto-generated method stub
		System.out.println("Creating Token for user : {" + token.getUsername() + "}");
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		persist(persistentLogin);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		// TODO Auto-generated method stub
		System.out.println("Fetch Token if any for seriesId : {" + seriesId + "}");
		try {
			Criteria crit = createEntityCriteria();
			crit.add(Restrictions.eq("series", seriesId));
			PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), persistentLogin.getLast_used());
		} catch (Exception e) {
			System.out.println("Token not found...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		// TODO Auto-generated method stub
		System.out.println("Removing Token if any for user : {" + username + "}");
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
        if (persistentLogin != null) {
            System.out.println("rememberMe was selected");
            delete(persistentLogin);
        }
	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub
		System.out.println("Updating Token for seriesId : {" + seriesId + "}");
        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        update(persistentLogin);
	}

}
