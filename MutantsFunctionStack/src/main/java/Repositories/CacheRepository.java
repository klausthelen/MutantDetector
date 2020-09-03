package Repositories;

import ExternalServices.CacheService;

import java.util.Set;


public class CacheRepository {

    public final CacheService cacheService = new CacheService(System.getenv("REDIS_HOST"),
            Integer.parseInt(System.getenv("REDIS_PORT")));

    public void set(String key, String value){
        cacheService.getConnection().set(key, value);
    }

    public void incr(String key){
        cacheService.getConnection().incr(key);
    }

    public String get(String key){
        String value = cacheService.getConnection().get(key);
        if(value == null){
            value = "0";
        }
        return value;
    }
    public void deleteAllKeys(){
        Set<String> keys = cacheService.getConnection().keys("*");
        for (String key : keys) {
            cacheService.getConnection().del(key);
        }
    }
}
