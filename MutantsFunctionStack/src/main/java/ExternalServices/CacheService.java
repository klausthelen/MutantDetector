package ExternalServices;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CacheService {

    private Jedis connection;

    public Jedis getConnection() {
        return connection;
    }

    public void setConnection(Jedis connection) {
        this.connection = connection;
    }

    public CacheService(String host, int port) {
        JedisPool pool = new JedisPool(host,port);
        this.setConnection(pool.getResource());
    }
}
