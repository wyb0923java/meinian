package com.atguigu;

import com.atguigu.utils.QiniuUtils;
import org.junit.Test;

/*************************************************
                时间: 2020-12-28
                作者: 刘  辉
                描述: 
  ************************************************/
public class QiniuUtilsTest {

    @Test
    public void test01(){
        QiniuUtils.upload2Qiniu("D://comguiguMeiNian//day05//图片//陈法蓉.jpg", "chenfasong.jpg");
    }
    @Test
    public void test02(){
     QiniuUtils.deleteFileFromQiniu("chenfasong.jpg");
    }
}
