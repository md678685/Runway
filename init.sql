drop table if exists users;
drop table if exists user_avatars;

create table users
(
    id                bigint       not null
        constraint users_pkey primary key,
    username          varchar(255) not null
        constraint users_username_key unique,
    password          varchar(255),
    email             varchar(255) not null
        constraint users_email_key unique,
    email_verified    boolean default false,
    is_active         boolean default true,
    is_admin          boolean default false,
    is_staff          boolean default false,
    full_name         varchar(255),
    gh_username       varchar(255),
    irc_nick          varchar(255),
    mc_username       varchar(255),
    current_avatar_id bigint
);

alter table users
    owner to root;

create table user_avatars
(
    id         bigint not null
        constraint user_avatars_pkey primary key,
    image_file varchar(255),
    remote_url varchar(255),
    source     varchar(255),
    user_id    bigint
        constraint user_avatars_user_id_fkey references users on delete cascade
);

alter table user_avatars
    owner to root;

alter table users
    add constraint users_current_avatar_id_fkey foreign key (current_avatar_id) references user_avatars on delete set null;
