package com.wy.springcloud.service;


import com.wy.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yang Hao
 * @description
 * @date 2020-09-14 16:07
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);

    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

    public String paymentInfo_TimeOutHandler(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id") Integer id);

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id);

    public String paymentCircuitBreakersss_fallback(@PathVariable("id") Integer id);

}
