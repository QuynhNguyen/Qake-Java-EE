package service.tweet;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Category;
import model.Tweet;
import service.category.CategoryServiceLocal;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import dao.CategoryDao;
import dao.TweetDao;

@Stateless(mappedName="ejb/Tweet")
public class TweetServiceImpl implements TweetServiceLocal, TweetServiceRemote {

	@EJB
	TweetDao tweetDao;
	
	/*
	 * Twitter CREDENTIAL
	 */
	
	private static final String ACCESS_TOKEN = "365203120-AcdroTh5ZRMqyz1PFImrfK9Q7qAPd8PzZtQP1r7v";
	private static final String ACCESS_TOKEN_SECRET = "IWoZ8dfaKuH37zjutdBazzT1LOeKZ0r3xxuzYbNcc";
	private static final String CONSUMER_KEY = "JXNWpU3wD2wCtB1wGyfSQ";
	private static final String CONSUMER_SECRET = "vd70KbqaZBgwF04wEZkY7gF3249jHg4mgkfdrJCDY";
	
	@Override
	public void createTweet(Tweet tweet) {
		tweetDao.createTweet(tweet);
	}

	@Override
	public List<Tweet> getAllPendingTweets() {
		return tweetDao.getAllPendingTweets();
	}

	@Override
	public void deleteTweet(int id) {
		tweetDao.deleteTweet(id);
	}

	@Override
	public Tweet findTweetById(int id) {
		return tweetDao.findTweetById(id);
	}

	@Override
	public void publishTweet(int id) {
		//Change tweet database status from pending to live
		tweetDao.publishTweet(id);
		
		//Find The Tweet
		Tweet aTweet = tweetDao.findTweetById(id);
		
		//publish it to twitter
		
		publishTweetToTwitter(aTweet.getContent());
		
	}
	
	public void publishTweetToTwitter(String status){
		
		//Authentication
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(CONSUMER_KEY)
		  .setOAuthConsumerSecret(CONSUMER_SECRET)
		  .setOAuthAccessToken(ACCESS_TOKEN)
		  .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		//Create Twitter Object
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		
		//Update to twitter
		try {
			twitter.updateStatus(status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Debugging
	    System.out.println("Successfully updated the status.");
	   
	}

}
