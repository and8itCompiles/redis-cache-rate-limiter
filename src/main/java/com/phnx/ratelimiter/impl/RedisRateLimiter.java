package com.phnx.ratelimiter.impl;

import org.apache.log4j.Logger;

import com.phnx.ratelimiter.constants.RateLimiterConstants;
import com.phnx.ratelimiter.exception.RateLimiterException;
import com.phnx.ratelimiter.model.RateLimiterResponse;
import com.phnx.ratelimiter.service.AbstractRateLimiter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisRateLimiter extends AbstractRateLimiter {
	
	/** The Constant logger. */
	public static final Logger LOGGER =  Logger.getLogger(RedisRateLimiter.class);

	@Override
	public boolean isAllowed(String key, Jedis jedis) throws RateLimiterException {
		boolean allowed = false;
		try{
			Long nowSeconds = System.currentTimeMillis() / 1000;
			jedis.zremrangeByScore(key, 0, nowSeconds-RateLimiterConstants.TIME_WINDOW);
			Long count = jedis.zcount(key, "-inf", "+inf");
			System.out.println("count "+count);
			allowed = count < RateLimiterConstants.MAX_CALLS;
			System.out.println("is allowed "+allowed);
			if (allowed) {
				jedis.zadd(key, nowSeconds, "" + System.currentTimeMillis());
			}
		}catch (Exception e) {
			throw new RateLimiterException(e.getMessage());
		}
		return allowed;
	}

	@Override
	public RateLimiterResponse getConnection(String host, String key) throws RateLimiterException {
		RateLimiterResponse rateLimiterResponse = new RateLimiterResponse();
		boolean useSsl = true;
		try {
			JedisShardInfo jedisShardInfo = new JedisShardInfo(host, 6380, useSsl);
			jedisShardInfo.setPassword(key); 
			Jedis jedis = new Jedis(jedisShardInfo);
			rateLimiterResponse.setResponse(jedis);
			rateLimiterResponse.setStatusMessage("Success");
		} catch (Exception e) {
			throw new RateLimiterException(e.getMessage());
		}
		return rateLimiterResponse;
	}

}
