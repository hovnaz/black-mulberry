alter table user_model
    alter column role type varchar(255) using role::varchar(255);

alter table user_model
    alter column role set default 'USER';
