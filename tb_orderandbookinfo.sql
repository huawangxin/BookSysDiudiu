


create table tb_orderandbookinfo_wangxin(
isbn varchar2(13) primary key,
orderdate date,
dgnumber varchar2(30),
operator varchar2(10),
checkAndAccept number,
discount varchar2(30),
typeId number,
bookName varchar2(40),
writer varchar(21),
translator varchar2(30),
publisher varchar(50),
publish_date date,
price varchar2(10)
);