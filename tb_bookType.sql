


create table tb_bookType_wangxin(
id number primary key,
booktypename varchar2(20),
days number,
fine varchar2(10)
);



--SELECT a.ISBN AS bISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate, b.backDate, c.readername AS rName, c.readercode AS rISBN FROM tb_bookInfo a INNER JOIN tb_borrow b ON a.ISBN = b.bookISBN INNER JOIN tb_reader c ON b.readerISBN = c.readercode WHERE (c.readercode = '1234567890123')";
--SELECT a.ISBN AS bISBN ,a.bookname,a.typeId,b.id,b.operatorId,to_char(b.borrowDate,'yyyy-mm-dd HH24:MI:SS'),b.backDate,c.readername AS rName,c.readercode AS rISBN from tb_bookInfo a INNER JOIN tb_borrow b ON a.ISBN=b.bookISBN INNER JOIN tb_reader c ON b.readerISBN=c.readercode WHERE(c.readercode='1234567890123');
--update tb_borrow set isback=0 where bookISBN='9787115111302' and readerISBN='1234567890123';