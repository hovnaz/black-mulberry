CREATE TABLE IF NOT EXISTS category_parent
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL
    );
ALTER TABLE category_parent
    owner to postgres;

CREATE TABLE IF NOT EXISTS category_product
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
),
    cat_par_id BIGINT NOT NULL,
    FOREIGN KEY
(
    cat_par_id
) REFERENCES category_parent
(
    id
)
    );
ALTER TABLE category_product
    owner to postgres;

CREATE TYPE user_role AS ENUM ('USER', 'ADMIN');

CREATE TABLE user_model
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    surname   VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    phone     VARCHAR(255) NOT NULL,
    role      user_role    NOT NULL DEFAULT 'USER',
    password  VARCHAR(255),
    create_at TIMESTAMP    NOT NULL
);
ALTER TABLE user_model
    owner to postgres;


CREATE TABLE product
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    price       DECIMAL      NOT NULL,
    stock       INT     DEFAULT NULL,
    pic_url     VARCHAR(255) NOT NULL,
    create_at   TIMESTAMP NULL DEFAULT NULL,
    description TEXT,
    is_delete   BOOLEAN DEFAULT FALSE,
    cat_id      BIGINT       NOT NULL,
    user_id     BIGINT       NOT NULL,
    FOREIGN KEY (cat_id) REFERENCES category_product (id),
    FOREIGN KEY (user_id) REFERENCES user_model (id)
);
ALTER TABLE product
    owner to postgres;

CREATE TABLE product_comment
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT  NOT NULL,
    product_id BIGINT  NOT NULL,
    content    TEXT    NOT NULL,
    is_delete  BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES user_model (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
ALTER TABLE product_comment
    owner to postgres;

CREATE TABLE product_basket
(
    id      BIGSERIAL PRIMARY KEY,
    is_paid BOOLEAN DEFAULT FALSE,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_model (id)
);
ALTER TABLE product_basket
    owner to postgres;

CREATE TABLE product_basket_item
(
    id                BIGSERIAL PRIMARY KEY,
    quantity          INT    NOT NULL,
    product_id        BIGINT NOT NULL,
    product_basket_id BIGINT NOT NULL,
    FOREIGN KEY (product_basket_id) REFERENCES product_basket (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
ALTER TABLE product_basket_item
    owner to postgres;
