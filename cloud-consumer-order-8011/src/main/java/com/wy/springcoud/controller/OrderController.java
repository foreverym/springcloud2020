package com.wy.springcoud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wy.springcloud.entities.CommonResult;
import com.wy.springcloud.entities.Payment;
import com.wy.springcoud.service.PaymentFeignService;
import com.wy.springcoud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author Yang Hao
 * @description
 * @date 2020-09-14 16:51
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderController {

    //集群负载均衡
    //public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    //public static final String PAYMENT_URL = "http://localhost:8001";
//    @Resource
//    private RestTemplate restTemplate;
    @Resource
    private PaymentFeignService paymentFeignService;
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info(">>>>>>>>>>>>>>>>>");
        return paymentFeignService.getPaymentById(id);
        //return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    /**
     * openfeign-ribbon 客户端一般默认等待1秒钟
     * 在支付服务侧,模拟暂停三秒
     *
     * @return
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut() {
        //客户端默认等待1秒钟
        return paymentFeignService.paymentFeignTimeOut();
    }


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    /**
     * 每一个方法都需要配置降级方法??? 代码冗余.需要一个通用的
     *
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand //参考全局降级方法注释,payment_Global_FallbackMethod
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    public String PaymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己，o(╥﹏╥)o";
    }

    /**
     * 全局fallback 降级方法
     * <p>
     * 需要注释一些属性,没有特别指明,就用统一的方法
     * //    @HystrixCommand(fallbackMethod = "PaymentTimeOutFallbackMethod", commandProperties = {
     * //            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
     * //    })
     * <p>
     * 替换成@HystrixCommand一个注解
     *
     * @return
     */
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }


}
