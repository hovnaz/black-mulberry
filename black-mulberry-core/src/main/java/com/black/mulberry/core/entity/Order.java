package com.black.mulberry.core.entity;

import com.black.mulberry.data.transfer.model.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private ProductBasket basket;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime openDateAt;
    private LocalDateTime completedDateAt;
    private LocalDateTime confirmedDateAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String comment;
}
