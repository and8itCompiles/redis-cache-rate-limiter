package com.phnx.ratelimiter.constants;

public class RateLimiterConstants {
	
	private RateLimiterConstants() {
		
	}
	
	public static final Long MAX_CALLS = 10L;
	public static final int TIME_WINDOW = 30; //seconds
	
	/** The Constant END_POINT. */
	public static final String END_POINT = "redisCache******.redis.cache.windows.net";
	/** The Constant MASTER_KEY. */
	public static final String RATELIMITER_KEY = "7luT*********************************Y=";
}
