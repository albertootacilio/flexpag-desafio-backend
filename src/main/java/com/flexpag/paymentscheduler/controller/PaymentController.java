package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.model.Payment;
import com.flexpag.paymentscheduler.Service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import static com.flexpag.paymentscheduler.enums.Status.PAID;
import static com.flexpag.paymentscheduler.enums.Status.PENDING;

@RestController
@RequestMapping("payment/")
public class PaymentController {

    private PaymentService service;
    private PaymentController(PaymentService service){
        this.service =service;
    }

    @GetMapping()
    public List<Payment> listAll() {return service.findAll();}

    @GetMapping( "{id}")
    public ResponseEntity<Payment> findId(@PathVariable Long id)  {
        Payment payment =  this.service.findId(id);

        if(payment !=null){
            return ResponseEntity.ok(payment);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@RequestBody  Payment p) {
        p.setStatus(PENDING);
        p.setDate(LocalDateTime.now());
        Payment obj = this.service.save(p);
        return "Success | ID:"+obj.getId()+ " STATUS: "+obj.getStatus()+" |";
    }


    @PutMapping( "{id}")
    public ResponseEntity<Payment> update(@PathVariable Long id , @RequestBody @Valid Payment payment) {
        Payment obj = this.service.findId(id);

        if (obj.getStatus() == PAID) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        if (obj != null) {
                BeanUtils.copyProperties(payment, obj, "id");
                this.service.save(obj);
                return ResponseEntity.ok(obj);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping( "{id}")
    public ResponseEntity<Payment> delete(@PathVariable Long id) {
        Payment obj = this.service.findId(id);

        if (obj == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else if(obj.getStatus() == PAID){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        this.service.remove(id);
        return ResponseEntity.noContent().build();
    }
}
