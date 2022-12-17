CREATE TABLE product_price_history
(
    id         BIGSERIAL PRIMARY KEY,
    price      DECIMAL   NOT NULL,
    create_at  TIMESTAMP NULL DEFAULT NULL,
    product_id BIGINT    NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

ALTER TABLE product_price_history
    owner to postgres;

alter table product_basket
    add create_at timestamp;
alter table product_basket
    rename column is_paid to is_actual;

alter table product_basket
    alter column is_actual set not null;

alter table product_basket
    alter column is_actual set default true;

CREATE TABLE orders
(
    id                BIGSERIAL PRIMARY KEY,
    basket_id         BIGINT       NOT NULL,
    user_id           BIGINT       NOT NULL,
    open_date_at      timestamp    not null,
    completed_date_at timestamp,
    confirmed_date_at timestamp,
    status            varchar(255) not null default 'OPEN',
    comment           TEXT,
    FOREIGN KEY (basket_id) REFERENCES product_basket (id),
    FOREIGN KEY (user_id) REFERENCES user_model (id)
);
ALTER TABLE orders
    owner to postgres;

CREATE TABLE order_cancel
(
    id                     BIGSERIAL PRIMARY KEY,
    order_id               BIGINT    not null,
    canceled_date_at       timestamp,
    cancel_request_date_at timestamp not null,
    description            TEXT      not null,
    is_approved            boolean   not null default false,
    FOREIGN KEY (order_id) REFERENCES orders (id)
);
ALTER TABLE order_cancel
    owner to postgres;

CREATE TABLE payment
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT       NOT NULL,
    order_id     BIGINT       NOT NULL,
    status       varchar(255) not null default 'UN_PAID',
    payment_date timestamp,
    amount       DECIMAL      not null,
    FOREIGN KEY (user_id) REFERENCES user_model (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);
ALTER TABLE payment
    owner to postgres;
