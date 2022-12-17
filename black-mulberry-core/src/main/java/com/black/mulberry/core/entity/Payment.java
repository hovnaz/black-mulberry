package com.black.mulberry.core.entity;

import com.black.mulberry.data.transfer.model.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(name = "payment_date", updatable = false)
    private LocalDateTime paymentDate;
    private BigDecimal amount;
}
