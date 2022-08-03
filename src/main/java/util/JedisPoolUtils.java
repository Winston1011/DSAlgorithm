package util;

/**
 * @author Winston DZ
 * @E-mail 1071442894@qq.com
 * @date 2021/12/27
 **/

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {

    private static final JedisPool jedisPool;

    static {

        //创建properties文件
        Properties pro = new Properties();
        //读取配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("application.properties");
        //加载配置文件
        try {

            pro.load(is);
        } catch (IOException e) {

            e.printStackTrace();
        }
        //创建连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        //最大允许连接数量
        config.setMaxTotal(Integer.parseInt(pro.getProperty("redis.maxActive")));
        //最大的空闲连接数量
        config.setMaxIdle(Integer.parseInt(pro.getProperty("redis.maxIdle")));

        //创建JedisPool连接池对象
        jedisPool = new JedisPool(config, pro.getProperty("redis.host"), Integer.parseInt(pro.getProperty("redis.port")),
                Integer.parseInt(pro.getProperty("redis.timeout")), pro.getProperty("redis.pass"));

    }

    //返回数据库连接池
    public static JedisPool getJedisPool() {

        return jedisPool;
    }

    //返回Jedis连接
    public static Jedis getJedis() {

        return jedisPool.getResource();
    }

    //关闭资源,归还到连接池中
    public static void getClose(Jedis jedis) {

        //归还连接
        if (jedis != null) {

            jedis.close();
        }
    }

    public static void getClose(JedisPool jedisPool, Jedis jedis) {

        //归还连接池
        if (jedisPool != null) {

            jedisPool.close();
        }
        //归还连接
        getClose(null, jedis);
    }
}
