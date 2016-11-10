/*图书信息表*/
drop table bookInfo_wangxin
select * from bookInfo_wangxin
create table bookInfo_wangxin(
ISBN varchar2(13) primary key,
typeId number,
bookName varchar2(40),
writer varchar(21),
translator varchar2(30),
publisher varchar(50),
publish_date varchar2(50),
price varchar2(10)
);
select *  from tb_operator_wangjian
SELECT * FROM tb_reader_wangxin WHERE  readercode=2222222222222
/*图书分类信息表*/
delete from tb_bookType_wangxin where id = 1
select * from tb_bookType_wangxin;
create table tb_bookType_wangxin(
id number primary key,
type varchar2(20),
days number,
fk varchar2(10)
);
/*图书借阅信息表*/
drop table tb_borrow_wangxin
select * from tb_borrow_wangxin 
create table tb_borrow_wangxin(
id number primary key,
bookISBN varchar2(13),
operatorID number,
readerISBN varchar2(13),
 isback number,
borrowDate varchar2(50),
backDate varchar2(50)
);
commit
/*操作信息表*/
drop table tb_operator_wangxin
SELECT * FROM tb_operator_wangxin
create table tb_operator_wangxin(
id number primary key,
name varchar2(12),
gender varchar2(2),
age number,
identityCard varchar2(30),
workdate varchar2(50),
tel varchar(50),
admin number,
password varchar2(10)
);

SELECT *  FROM tb_borrow_wangxin a,tb_bookinfo_wangxin b ,tb_reader_wangxin c 
WHERE a.readerISBN='1111111111111' AND b.ISBN=a.bookISBN  
AND c.readercode='1111111111111'

/*图书订购信息表*/
drop table tb_order_wangxin
create table tb_order_wangxin(
ISBN varchar2(13) primary key,
dgdate varchar2(50),
dgnumber number,
operator varchar2(6),
checkAndAccept number,
zk varchar2(30)
);
SELECT * from tb_borrow_wangxin
SELECT * from tb_reader_wangxin
/*读者信息表*/
create table tb_reader_wangxin(
readername varchar2(10),
gender varchar2(2),
age number,
job varchar2(20),
identityCard varchar2(30),
vipDate varchar2(20),
tel varchar2(50),
maxborrow number(10),
keepMoney varchar2(100),
bztime varchar2(50), 
readercode varchar2(30)
);

/*库存信息表*/
create table tb_stockpile_wangxin(
ISBN varchar2(13) primary key,
amount number
);

delete from tb_operator_wangxin
where id=1001
commit
/*建立sequence*/
create sequence booksys_diudiu_wangxin
increment by 1
start with 1
nomaxvalue
nocycle
nocache;
select * from tb_operator_wangxin
/*给操作信息表插入数据*/
insert into tb_operator_wangxin(id,name,gender,age,identityCard,workdate,tel,admin,password) 
values(1001,'diudiu','m',23,'620302198910030521',null,'13911111111',1,'123456');
insert into tb_operator_wangxin(id,name,gender,age,identityCard,workdate,tel,password) 
values(booksys_diudiu.nextval,'"+name+"','"+gender+"',"+age+",'"+identityCard+"',
to_date('"+workdate+"','yyyy-mm-dd'),'"+tel+"','"+password+"')";