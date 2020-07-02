for MySQL Server 8.0.20.0

create database userdatabase character set='utf8' collate='utf8_hungarian_ci';

use userdatabase;

create table userlogindata (
	id integer auto_increment primary key,
    fname varchar(30),
    lname varchar(30),
    email varchar(50) NOT NULL,
    regdate datetime
);

insert into userlogindata (id, fname, lname, email, regdate) values 
(1,'some','body', 'this@gmail.com', '2012-10-11 10:31:11'),
(2,'me','here', 'that@gmail.com', '2020-06-11 15:13:01'),
(3, 'you','there', 'other@gmail.com', '2018-07-15 16:14:16');