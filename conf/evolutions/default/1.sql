# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tool (
  tool_id                   bigserial not null,
  tool_name                 varchar(255),
  tool_description          text,
  condition                 varchar(255),
  user_id                   bigint,
  constraint pk_tool primary key (tool_id))
;

create table users (
  id                        bigserial not null,
  username                  varchar(255),
  password                  varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  password_hash             varchar(255),
  date_created              timestamp,
  constraint pk_users primary key (id))
;

alter table tool add constraint fk_tool_user_1 foreign key (user_id) references users (id);
create index ix_tool_user_1 on tool (user_id);



# --- !Downs

drop table if exists tool cascade;

drop table if exists users cascade;

