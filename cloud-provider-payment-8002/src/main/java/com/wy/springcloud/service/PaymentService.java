package com.wy.springcloud.service;


import com.wy.springcloud.entities.Payment;

/**
 * @author Yang Hao
 * @description
 * @date 2020-09-14 16:07
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}
