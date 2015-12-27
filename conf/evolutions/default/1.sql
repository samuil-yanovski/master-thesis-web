# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  key                       bigint not null,
  name                      varchar(255),
  constraint pk_category primary key (key))
;

create table contacts (
  key                       bigint not null,
  skype                     varchar(255),
  phone                     varchar(255),
  constraint pk_contacts primary key (key))
;

create table credentials (
  key                       bigint not null,
  email                     varchar(255),
  password                  bytea,
  google_plus_id            varchar(255),
  facebook_id               varchar(255),
  constraint uq_credentials_email unique (email),
  constraint pk_credentials primary key (key))
;

create table device (
  key                       bigint not null,
  token                     varchar(255),
  owner_key                 bigint,
  constraint uq_device_token unique (token),
  constraint pk_device primary key (key))
;

create table event (
  key                       bigint not null,
  name                      varchar(255),
  date                      timestamp,
  constraint pk_event primary key (key))
;

create table graduation_date (
  key                       bigint not null,
  name                      varchar(255),
  date                      timestamp,
  constraint pk_graduation_date primary key (key))
;

create table student (
  key                       bigint not null,
  name                      varchar(255),
  faculty_number            varchar(255),
  avatar                    varchar(255),
  contacts_key              bigint,
  credentials_key           bigint,
  thesis_key                bigint,
  constraint pk_student primary key (key))
;

create table teacher (
  key                       bigint not null,
  name                      varchar(255),
  avatar                    varchar(255),
  contacts_key              bigint,
  credentials_key           bigint,
  constraint pk_teacher primary key (key))
;

create table thesis (
  key                       bigint not null,
  title                     varchar(255),
  description               varchar(255),
  category_key              bigint,
  author_key                bigint,
  graduate_key              bigint,
  constraint pk_thesis primary key (key))
;

create table token (
  key                       bigint not null,
  auth                      varchar(255),
  refresh                   varchar(255),
  expiration_date           timestamp,
  owner_key                 bigint,
  constraint pk_token primary key (key))
;


create table credentials_event (
  credentials_key                bigint not null,
  event_key                      bigint not null,
  constraint pk_credentials_event primary key (credentials_key, event_key))
;

create table event_credentials (
  event_key                      bigint not null,
  credentials_key                bigint not null,
  constraint pk_event_credentials primary key (event_key, credentials_key))
;
create sequence category_seq;

create sequence contacts_seq;

create sequence credentials_seq;

create sequence device_seq;

create sequence event_seq;

create sequence graduation_date_seq;

create sequence student_seq;

create sequence teacher_seq;

create sequence thesis_seq;

create sequence token_seq;

alter table device add constraint fk_device_owner_1 foreign key (owner_key) references credentials (key);
create index ix_device_owner_1 on device (owner_key);
alter table student add constraint fk_student_contacts_2 foreign key (contacts_key) references contacts (key);
create index ix_student_contacts_2 on student (contacts_key);
alter table student add constraint fk_student_credentials_3 foreign key (credentials_key) references credentials (key);
create index ix_student_credentials_3 on student (credentials_key);
alter table student add constraint fk_student_thesis_4 foreign key (thesis_key) references thesis (key);
create index ix_student_thesis_4 on student (thesis_key);
alter table teacher add constraint fk_teacher_contacts_5 foreign key (contacts_key) references contacts (key);
create index ix_teacher_contacts_5 on teacher (contacts_key);
alter table teacher add constraint fk_teacher_credentials_6 foreign key (credentials_key) references credentials (key);
create index ix_teacher_credentials_6 on teacher (credentials_key);
alter table thesis add constraint fk_thesis_category_7 foreign key (category_key) references category (key);
create index ix_thesis_category_7 on thesis (category_key);
alter table thesis add constraint fk_thesis_author_8 foreign key (author_key) references teacher (key);
create index ix_thesis_author_8 on thesis (author_key);
alter table thesis add constraint fk_thesis_graduate_9 foreign key (graduate_key) references student (key);
create index ix_thesis_graduate_9 on thesis (graduate_key);
alter table token add constraint fk_token_owner_10 foreign key (owner_key) references credentials (key);
create index ix_token_owner_10 on token (owner_key);



alter table credentials_event add constraint fk_credentials_event_credenti_01 foreign key (credentials_key) references credentials (key);

alter table credentials_event add constraint fk_credentials_event_event_02 foreign key (event_key) references event (key);

alter table event_credentials add constraint fk_event_credentials_event_01 foreign key (event_key) references event (key);

alter table event_credentials add constraint fk_event_credentials_credenti_02 foreign key (credentials_key) references credentials (key);

# --- !Downs

drop table if exists category cascade;

drop table if exists contacts cascade;

drop table if exists credentials cascade;

drop table if exists credentials_event cascade;

drop table if exists device cascade;

drop table if exists event cascade;

drop table if exists event_credentials cascade;

drop table if exists graduation_date cascade;

drop table if exists student cascade;

drop table if exists teacher cascade;

drop table if exists thesis cascade;

drop table if exists token cascade;

drop sequence if exists category_seq;

drop sequence if exists contacts_seq;

drop sequence if exists credentials_seq;

drop sequence if exists device_seq;

drop sequence if exists event_seq;

drop sequence if exists graduation_date_seq;

drop sequence if exists student_seq;

drop sequence if exists teacher_seq;

drop sequence if exists thesis_seq;

drop sequence if exists token_seq;

