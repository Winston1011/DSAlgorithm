import org.junit.Test;
import redis.clients.jedis.Jedis;
import util.JedisPoolUtils;

/**
 * @author Winston DZ
 * @E-mail 1071442894@qq.com
 * @date 2021/12/27
 **/
public class JedisTest {

    @Test //注解JUnit单元测试 使用 Maven 工具的话，就直接在测试包中测试既可
    public void test1() {
        //获取Jedis连接
        Jedis jedis = JedisPoolUtils.getJedis();
        //操作数据
//        jedis.set("username","liside");
        //获取控制台打印数据
        System.out.println(jedis.get("username"));
        //关闭资源(归还)
        JedisPoolUtils.getClose(jedis);
        //打开服务端Redis和客服端Redis,运行
    }
}
