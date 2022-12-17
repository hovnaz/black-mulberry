package com.black.mulberry.core.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_cancel")
public class OrderCancel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Order order;
    private boolean isApproved;
    @CreationTimestamp
    @Column(name = "cancel_request_date_at", nullable = false, updatable = false)
    private LocalDateTime canselRequestDateAt;
    private LocalDateTime canceledDateAt;
    private String description;
}
