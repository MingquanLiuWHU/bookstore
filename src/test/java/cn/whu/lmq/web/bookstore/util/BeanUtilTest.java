package cn.whu.lmq.web.bookstore.util;

import cn.whu.lmq.web.bookstore.bean.Store;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class BeanUtilTest {

    //测试简单类的复制
    @Test
    public void copyTest(){
        Store store1 = new Store();
        store1.setId(1);
        store1.setAddress("地址1");
        store1.setDescription(null);
        store1.setReceiveAccount("1");
        store1.setCreateTime(new Date());
        Store store2 = new Store();
        store2.setId(2);
        store2.setDescription("描述2");
        store2.setUpdateTime(new Date());
        BeanUtils.copyProperties(store1,store2);
        System.out.println("id："+store2.getId());
        System.out.println("名称："+store2.getStoreName());
        System.out.println("描述："+store2.getDescription());
    }

    @Test
    public void copyTest2(){
        Store store1 = new Store();
        store1.setId(1);
        store1.setAddress("地址1");
        store1.setDescription(null);
        store1.setReceiveAccount("1");
        store1.setCreateTime(new Date());
        Store store2 = new Store();
        store2.setId(2);
        store2.setDescription("描述2");
        store2.setUpdateTime(new Date());
        CopyUtil.copyNotNullFields(store1,store2);
        System.out.println("id："+store2.getId());
        System.out.println("名称："+store2.getStoreName());
        System.out.println("描述："+store2.getDescription());
    }
}
