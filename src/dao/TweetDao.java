package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tweet;

@Stateless
public class TweetDao {

	@PersistenceContext
	EntityManager em;
	
	public void createTweet(Tweet tweet){
		em.persist(tweet);
	}
	
}
