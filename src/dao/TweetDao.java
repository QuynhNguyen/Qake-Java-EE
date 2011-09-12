package dao;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<Tweet> getAllPendingTweets(){
		return em.createQuery("SELECT tweet FROM Tweet tweet WHERE tweet.status = :pending").setParameter("pending", "pending").getResultList();
	}
	
	public void deleteTweet(int id){
		em.remove(em.find(Tweet.class, id));
	}
	
	public Tweet findTweetById(int id){
		return em.find(Tweet.class, id);
	}
	
	public void publishTweet(int id){
		em.createQuery("UPDATE Tweet tweet SET tweet.status = :status WHERE tweet.id = :id ").setParameter("status", "live").setParameter("id", id).executeUpdate();
	}
}
