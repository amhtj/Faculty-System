create table Messages
(
  userId        int auto_increment
    primary key,
  text      varchar(255) null,
  date      date         null,
  userId    int          null,
  teacherId int          null
);

create table Subjects
(
  userId        int auto_increment
    primary key,
  name      varchar(255) not null,
  teacherId int          null,
  `groups`  varchar(255) null
);

create table Teacher
(
  userId        int auto_increment
    primary key,
  name      varchar(255) null,
  degree    varchar(255) null,
  email     varchar(255) null,
  number    varchar(40)  null,
  location  varchar(255) null,
  visitDate date         null,
  constraint Teacher_email_uindex
    unique (email)
);

create table Users
(
  userId        int auto_increment
    primary key,
  login     varchar(255) not null,
  password  varchar(255) not null,
  isTeacher tinyint(1)   null
);