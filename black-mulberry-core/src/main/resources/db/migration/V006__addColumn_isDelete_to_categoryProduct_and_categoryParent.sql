alter table category_product
    add is_delete BOOLEAN default false;
alter table category_parent
    add is_delete BOOLEAN default false;

