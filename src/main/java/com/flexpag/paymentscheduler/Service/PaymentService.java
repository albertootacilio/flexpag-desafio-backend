package com.flexpag.paymentscheduler.Service;

import com.flexpag.paymentscheduler.model.Payment;
import com.flexpag.paymentscheduler.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository repository;

    private PaymentService(PaymentRepository repository){this.repository =repository;}

    public List<Payment> findAll(){ return repository.findAll(); }

    public Payment findId(Long id) {return this.repository.findAllById(Payment.class,id);}

    public Payment save(Payment Payment){return repository.save(Payment);}

    public void remove(Long id) {this.repository.deleteById(id);}
}
