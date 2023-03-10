package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findAllById(Class<Payment> paymentClass, Long id);
}
