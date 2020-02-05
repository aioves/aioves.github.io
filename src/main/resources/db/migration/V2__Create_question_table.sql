drop table if exists question;
--create table
create table question
(
    id int auto_increment,
    title varchar(64),
    detail text,
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tags varchar(128),
    created_by int,
    create_date timestamp default current_timestamp(),
    update_date timestamp default current_timestamp()

);

--add comment
comment on table question is '问题表';

comment on column question.title is '问题标题';

comment on column question.detail is '问题详情';

comment on column question.create_date is '创建时间';

comment on column question.update_date is '最后修改时间';

comment on column question.created_by is '创建人';

comment on column question.comment_count is '回复数';

comment on column question.view_count is '浏览数';

comment on column question.like_count is '关注数';

comment on column question.tags is '标签';

--create index
create unique index question_id_uindex
    on question (id);

alter table question
    add constraint question_pk
        primary key (id);

