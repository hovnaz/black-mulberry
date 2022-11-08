alter table product
    rename column cat_id to category_id;
alter table category_product
    rename column cat_par_id to category_parent_id;