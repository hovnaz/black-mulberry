alter table product_comment
    add create_at timestamp not null;

alter table product_comment
    add update_at timestamp;

