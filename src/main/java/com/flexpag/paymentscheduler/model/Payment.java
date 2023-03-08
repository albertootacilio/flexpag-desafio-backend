package com.flexpag.paymentscheduler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "payment" )
public class Payment {
    private static final long serialVersion= 1L;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name="date")
    private LocalDateTime date;


}
