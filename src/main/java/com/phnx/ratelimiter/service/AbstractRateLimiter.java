package com.phnx.ratelimiter.service;

import com.phnx.ratelimiter.exception.RateLimiterException;
import com.phnx.ratelimiter.model.RateLimiterResponse;

import redis.clients.jedis.Jedis;

public abstract class AbstractRateLimiter implements IRateLimiter{
	/**
	 * 
	 * @param key
	 * @param jedis
	 * @return
	 */
	public abstract boolean isAllowed(String key, Jedis jedis) throws RateLimiterException;
	/**
	 * 
	 * @param host
	 * @param key
	 * @return
	 */
	public abstract RateLimiterResponse getConnection(String host, String key) throws RateLimiterException;

}
