package service.tweet;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.Category;
import model.Tweet;
import service.category.CategoryServiceLocal;
import dao.CategoryDao;
import dao.TweetDao;

@Stateless(mappedName="ejb/Tweet")
public class TweetServiceImpl implements TweetServiceLocal, TweetServiceRemote {

	@EJB
	TweetDao tweetDao;
	
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

}
