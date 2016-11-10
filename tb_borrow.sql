


create table tb_borrow_wangxin(
id number primary key,
bookISBN varchar2(13),
operatorID number,
readerISBN varchar2(13),
isback number,
borrowDate date,
backDate date,
borrowBookName varchar2(30)
);



--create table tb_borrow(
--id number primary key,
--bookISBN varchar2(13),
--operatorID number,
--readerISBN varchar2(13),
--isback number,
--borrowDate varchar2(30),
--backDate varchar2(30),
--borrowBookName varchar2(30)
--);

--insert into tb_borrow(id,bookISBN,readerISBN,operatorId,borrowDate) values(booksys_diudiu.nextval,'9787115111302','1234567890123',1243,to_date('2013-05-05 04:05:59','yyyy-mm-dd HH24:MI:SS'));
--insert into tb_borrow(id,borrowDate) values(booksys_diudiu.nextval,to_date('2008-05-31 18:31:34','yyyy-MM-dd HH24:mi:ss'));
--insert into tb_borrow(id,bookISBN,readerISBN,operatorId,borrowDate,backDate) values(booksys_diudiu.nextval,'22222222','111111111',1235,to_date('2013-05-05 04:05:59','yyyy-mm-dd HH24:MI:SS'),to_date('2013-05-05 04:09:59','yyyy-mm-dd HH24:MI:SS');
--insert into tb_borrow(id,bookISBN,readerISBN,operatorId,borrowDate,backDate) values(booksys_diudiu.nextval,'222222','11111',1133,to_date('2013-05-05 04:05:59','yyyy-mm-dd HH24:MI:SS'),to_date('2013-05-05 04:37:42','yyyy-mm-dd HH24:MI:SS'));