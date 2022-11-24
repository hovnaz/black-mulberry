CREATE TABLE IF NOT EXISTS product_rating
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    rating     INT    NOT NULL default 0,
    FOREIGN KEY (user_id) REFERENCES user_model (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
ALTER TABLE product_rating
    owner to postgres;
