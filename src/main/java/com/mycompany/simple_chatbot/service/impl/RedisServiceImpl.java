/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service.impl;

import com.mycompany.simple_chatbot.config.ConfigManager;
import com.mycompany.simple_chatbot.service.RedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author lesan
 */
public class RedisServiceImpl implements RedisService {

    private String message;
    private JedisPool pool;
    private ConfigManager config = ConfigManager.getInstance();
    
    private static final RedisServiceImpl INSTANCE = new RedisServiceImpl();
    
    private RedisServiceImpl() {
        // Private constructor to prevent instantiation.
        pool = new JedisPool(config.getRedisHost(), 6379);
    }
    
    public static RedisServiceImpl getInstance() {
        return INSTANCE;
    }
    
    @Override
    public String getValueByKey(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }

    @Override
    public Boolean putValueByKey(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(key, value);
        }
        return true;
    }

    @Override
    public Boolean putValueByKetWithTimeInSec(String key, String value, Long timeInSec) {
        try (Jedis jedis = pool.getResource()) {
            jedis.setex(key, timeInSec, value);
        }
        return true;
    }
    
}
