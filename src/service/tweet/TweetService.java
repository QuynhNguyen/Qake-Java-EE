package service.tweet;

import java.util.List;

import model.Tweet;


public interface TweetService {
	public void createTweet(Tweet tweet);
	public List<Tweet> getAllPendingTweets();
	public void deleteTweet(int id);
	public Tweet findTweetById(int id);
	public void publishTweet(int id);
	public void updateTweet(int id, String content);
}
