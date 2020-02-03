drop table if exists USER;

-- create table
create table USER(
    USER_ID     INT auto_increment,
    LOGIN       VARCHAR(32),
    NAME        VARCHAR(32),
    BIO         VARCHAR(256),
    AVATAR_URL  VARCHAR(256),
    ACCOUNT_ID  VARCHAR(64),
    TOKEN       VARCHAR(36),
    CREATE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
    constraint USER_PK
        primary key (USER_ID)
);

-- comment
comment on table USER is '用户表';

comment on column USER.LOGIN is '登录名';

comment on column USER.NAME is '用户名';

comment on column USER.BIO is '个人简介';

comment on column USER.AVATAR_URL is '用户头像';

comment on column USER.ACCOUNT_ID is '第三方系统用户ID';

comment on column USER.CREATE_DATE is '创建时间';

comment on column USER.UPDATE_DATE is '最后修改时间';

--index
create unique index USER_ID_UINDEX
    on USER (USER_ID);

