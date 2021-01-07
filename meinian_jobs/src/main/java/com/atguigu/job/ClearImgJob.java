package com.atguigu.job;

import com.atguigu.redis.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        Jedis jedis = jedisPool.getResource();
        Set<String> sdiff = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        for (String s : sdiff){
            //1、清理七牛云服务器上的立即图片
            QiniuUtils.deleteFileFromQiniu(s);
            //2、从SETMEAL_PIC_RESOURCES对应的集合里面清除垃圾图片
            jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);
        }

    }
}
