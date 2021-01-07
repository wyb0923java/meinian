package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.SetmealService;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.redis.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.RedisPipeline;
import redis.clients.jedis.exceptions.JedisDataException;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

//    上传照片
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){

        Jedis jedis = null;
       try {
            String filename = UUID.randomUUID().toString().replaceAll("-","")+imgFile.getOriginalFilename();
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);

            //更换几张图片保存几张 到Redis中的SETMEAL_PIC_RESOURCES
           jedis = jedisPool.getResource();
           jedis.sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);


           return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,filename);
        } catch (IOException e) {
            e.printStackTrace();
           return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }finally {
           jedis.close();
       }
    }

//    增
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] tids){

        Jedis jedis = null;
        try {
            setmealService.add(setmeal,tids);

            //点击添加时，图片保存到Redis中的SETMEAL_PIC_DB_RESOURCES
            jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());


            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.ADD_SETMEAL_FAIL);
        }finally{
            jedis.close();
        }
    }

}
