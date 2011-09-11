package service.tweet;

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

}
