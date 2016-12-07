package com.taotao.rest.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

private Jedis jedis;

	//单机版测试
	@Test
	public void testJedisSingle(){
		//方式1
		jedis = new Jedis("192.168.1.13",6379);
		jedis.set("a", "HelloJedis");
		String str = jedis.get("a");
		System.out.println(str);
		jedis.close();
		
		//方式2
		JedisPool jPool = new JedisPool("192.168.1.13",6379);
		jedis = jPool.getResource();
	}
	
	//集群测试
	@Test
	public void testJedisCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.13",7001));
		nodes.add(new HostAndPort("192.168.1.13",7002));
		nodes.add(new HostAndPort("192.168.1.13",7003));
		nodes.add(new HostAndPort("192.168.1.13",7004));
		nodes.add(new HostAndPort("192.168.1.13",7005));
		nodes.add(new HostAndPort("192.168.1.13",7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("hello", "hellojedis");
		String str = jedisCluster.get("hello");
		System.out.println(str);
		
		
		jedisCluster.close();
		
	}
	
}
