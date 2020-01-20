package com.phnx.ratelimiter;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.phnx.ratelimiter.constants.RateLimiterConstants;
import com.phnx.ratelimiter.exception.RateLimiterException;
import com.phnx.ratelimiter.impl.RedisRateLimiter;
import com.phnx.ratelimiter.model.RateLimiterResponse;

import redis.clients.jedis.Jedis;

public class TestRateLimiter {
	
	static RateLimiterFactory rateLimiterFactory;
	
	static RedisRateLimiter redisRateLimiter;

	
	static RateLimiterResponse rateLiimiterResponse;
	
	static Jedis jedis;
	@SuppressWarnings("static-access")
	@BeforeClass
	public static void init() {

		try {
			redisRateLimiter = rateLimiterFactory.getRedisRateLimiterInstance();
		} catch (RateLimiterException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void testGetConnection() throws RateLimiterException {
		rateLiimiterResponse = redisRateLimiter.getConnection(RateLimiterConstants.END_POINT, RateLimiterConstants.RATELIMITER_KEY);
		jedis = (Jedis) rateLiimiterResponse.getResponse();
		assertEquals("Success", rateLiimiterResponse.getStatusMessage() );
	}
	@Test
	public void testCompanyAllowed() throws RateLimiterException{
		jedis = (Jedis) rateLiimiterResponse.getResponse();
		boolean b = redisRateLimiter.isAllowed("API123", jedis);
		assertEquals(true, b);
	}
	
}
