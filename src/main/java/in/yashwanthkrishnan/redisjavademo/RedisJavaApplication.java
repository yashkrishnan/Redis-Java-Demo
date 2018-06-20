package in.yashwanthkrishnan.redisjavademo;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class RedisJavaApplication {

    public static void main(String[] args) {
        // Connecting to Redis server on localhost
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println("\nConnection to server successfully");

        // Check whether server is running or not
        System.out.println("\nServer is running (PING) : (" + jedis.ping() +")");

        // Set the data in redis string
        jedis.set("project-name", "Redis Java Demo");

        // Get the stored data and print it
        System.out.println("\nStored string in redis : " + jedis.get("project-name"));

        // Store data in redis List
        jedis.lpush("demo-list", "Data 1");
        jedis.lpush("demo-list", "Data 2");
        jedis.lpush("demo-list", "Data 3");

        System.out.println("\nStored item in redis demo-list : ");
        // Get the stored data and print it
        List<String> demoList = jedis.lrange("demo-list", 0, 2);
        for (String item : demoList) {
            System.out.println(item);
        }

        /*boolean existsInList = jedis.sismember("demo-list", "Data 1");*/

        //Store data in redis Set
        jedis.sadd("demo-set", "Data 1");
        jedis.sadd("demo-set", "Data 2");
        jedis.sadd("demo-set", "Data 1");

        Set<String> demoSet = jedis.smembers("demo-set");
        // Get the stored data and print it
        System.out.println("\nStored item in redis demo-set : ");
        for (String item : demoSet) {
            System.out.println(item);
        }

        boolean existsInSet = jedis.sismember("demo-set", "Data 1");

        // Get the stored data and print it
        Set<String> keys = jedis.keys("*");

        System.out.println("\nList of stored keys: ");
        for (String key : keys) {
            System.out.println(key);
        }


        try {
            Thread.sleep(100);
            new Thread().join();
            jedis.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
