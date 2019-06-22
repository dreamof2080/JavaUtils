package com.jeffrey.util.hessian;

import com.caucho.hessian.client.HessianProxyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-base.xml"})
public class HessianTest {

    @Test
    public void doSomething() {
        String url = "http://localhost:8080/hessian/doSomething";
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            // 第一次调用
            HessianService service = (HessianService) factory.create(HessianService.class,url);
            service.doSomething("test");
            service.doSomething(null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
