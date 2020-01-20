package com.phnx.ratelimiter;

import com.phnx.ratelimiter.exception.RateLimiterException;
import com.phnx.ratelimiter.impl.RedisRateLimiter;
/**
 * 
 *
 */
public class RateLimiterFactory {
	/**
	 * Instantiate new rate limiter factory.
	 */
	private RateLimiterFactory() {}
	
	/**
	 * Gets the RedisRateLimiterInstance
	 * 
	 * @return
	 * @throws RateLimiterException
	 */
	public static RedisRateLimiter getRedisRateLimiterInstance() throws RateLimiterException{
		return new RedisRateLimiter();
	}
}