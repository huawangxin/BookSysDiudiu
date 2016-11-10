package com.youdian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.net.aso.e;

import com.youdian.iframe.BookBorrowIFrame;
import com.youdian.iframe.newBookCheckIFrame;
import com.youdian.model.Back;
import com.youdian.model.BookInfo;
import com.youdian.model.BookType;
import com.youdian.model.Borrow;
import com.youdian.model.Operator;
import com.youdian.model.Order;
import com.youdian.model.OrderAndBookInfo;
import com.youdian.model.Reader;
import com.youdian.model.User;
import com.youdian.util.DBUtil;

/**
 * ������DAO ���ڽ������ݿ����ӺͲ���
 * DAO:Data Access Object ���ݿ���ʶ��� �Ѷ����ݿ�Ĳ�����װ����������������ʹ��
 */

public class Dao {
	/**
	 * �����ԣ�
	 *  dbClassName:���ݿ�������(driver) 
	 * dbURL:url,ͳһ��Դ��λ�� 
	 * dbUser:���ݿ��û���
	 * dbPwd:���ݿ�����
	 *  Connection conn�����ݿ�����
	 */
	static Connection conn ;

	public Dao() throws SQLException {
		/*
		 * �ڹ������г�ʼ�����Connection ��һ��if�жϣ��Ƿ��Ѿ���conn�����û���򴴽�
		 */
		try {
			
			if(conn!=null){
				System.out.println("�����ѽ���");
			}else {
				conn=DBUtil.getConnection();
			}
			System.out.println("���ݿ�������");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return ResultSet: ���ݿ��ѯ���
	 * @param String sql����ѯ��� 
	 * ���ж��Ƿ���conn
	 */
	private static ResultSet executeQuery(String sql) {
	
		ResultSet rs = null ;
		try{
			
		if(conn == null){
			conn = DBUtil.getConnection() ;
		}
			Statement state = conn.createStatement() ;
			 rs =state.executeQuery(sql) ;
			
			
		
		}catch (Exception e) {
			e.printStackTrace() ;
		}
	return rs;
		
	}

	/**
	 * 
	 * @param sql
	 * @return int,���²��ɹ��򷵻�-1���ɹ��򷵻�
	 * Statement stmt.executeQuery�ķ���ֵ
	 *  �ж�conn�Ƿ����
	 * @exception �� SQLException
	 */
	private static int executeUpdate(String sql) {
		int flag=-1;
		try{
			
			if(conn == null){
				conn = DBUtil.getConnection() ;
			
			}
				Statement state = conn.createStatement() ;
				
				 flag =state.executeUpdate(sql) ;
				//executeUpdate���µ�����
				return flag ;
			
			}catch (Exception e) {
				e.printStackTrace() ;
				return -1;
			}
	}

	/**
	 * �ر����ݿ�����Connection
	 * @exception��SQLException 
	 * finally ��conn�ָ���ʼֵnull
	 */
	public static void close() {
		DBUtil.closeConnection() ;
		conn = null ;
	}

	/**
	 * ����Ա��¼����
	 * @param name ��¼�û���
	 * @param password ����
	 * @return Operator
	 * @exception Exception
	 */
	public static Operator check(String name, String password) {
		try{
		Operator op = new Operator() ;
		String sql = "SELECT * FROM tb_operator_wangxin " +
				"WHERE name ='"+name+"'" +
				" AND password ='"+password+"'";
		
		int i = Dao.executeUpdate(sql) ;
		if(i>0){
			System.out.println("��ȷ");
			op.setName(name) ;
			op.setPassword(password) ;
			return op ;
		}else {
			System.out.println(i);
			System.out.println("����");
			
			return op ;
		}
		}finally{
			Dao.close() ;
		}
		
		
	}

	/*
	 * ��ѯ��𷽷�
	 */
	// ͼ������ѯ
	public static List selectBookCategory() {
		List<BookType> list = new ArrayList<BookType>() ;
		
		try{
		String sql = "SELECT * FROM tb_bookType_wangxin " ;
		ResultSet rs = Dao.executeQuery(sql) ;
		while (rs.next()) {
			BookType bt = new BookType() ;
			int id =rs.getInt("id") ;
			String type = rs.getString("type") ;
			int days = rs.getInt("days") ;
			String fk = rs.getString("fk") ;
			bt.setId(id );
			bt.setType(type );
			bt.setDays(days) ;
			bt.setFk(fk) ;
			
			list.add(bt) ;
			
			
		}
		rs.close() ;
		
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
			
		}
		return list;

	}

	// ͼ������ѯ
	public static List selectBookCategory(String bookTypeName) {
		List<BookType> list = new ArrayList<BookType>() ;
		
		try{
		String sql = "SELECT * FROM tb_bookType_wangxin "+
		"WHERE " +"type='"+bookTypeName+"'" ;
		ResultSet rs = Dao.executeQuery(sql) ;
		while (rs.next()) {
			BookType bt = new BookType() ;
			int id =rs.getInt("id") ;
			String type = rs.getString("type") ;
			int days = rs.getInt("days") ;
			String fk = rs.getString("fk") ;
			bt.setId(id );
			bt.setType(type );
			bt.setDays(days) ;
			bt.setFk(fk) ;
			
			list.add(bt) ;
		}
		rs.close() ;
		
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
			
		}
		return list;

	}

/**
 * ͼ��������ز���
*/
	// ��ͼ������������
	public static int InsertBookType(String bookTypeName, int days, String fine) {
		String idSql = "SELECT MAX(id) id FROM tb_bookType_wangxin" ;
		try{
		
		
		ResultSet rs =Dao.executeQuery(idSql) ;
		
		int id =1 ;
		if(rs.next()){
			id = rs.getInt("id") ;
		}
		rs.close() ;
		
		id++ ;
		String sql = "INSERT INTO tb_bookType_wangxin " +
				"VALUES("+id+",'"+bookTypeName+"',"+days+",'"+fine+"')" ;
		int i = Dao.executeUpdate(sql) ;
		return i ;
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			if(conn!=null){
				Dao.close() ;
			}
	}
		return 0 ;
	}
	//����ͼ����𷽷�������executeUpdate�ķ���ֵ
	public static int UpdatebookType(String id, String bookTypeName,
			String days, String fine) {
		int i=0;
		/**
		 * �߼�����
		 */
		try {

			String sql = "UPDATE tb_bookType_wangxin" +
					" SET type = '"+bookTypeName+"',days = '"+days+"',fk = '"+fine+"' " +
							"WHERE id = '"+id+"'" ;
			i=Dao.executeUpdate(sql) ;
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return i;
	}
	

	// ͼ�����ɾ������
	public static int DelbookType(long id) {
		int i=-1;
		String sql = "delete from tb_bookType_wangxin" +
				" where id = "+id ;
		System.out.println(sql);
		i = Dao.executeUpdate(sql) ;
		return i;

	}
	 public static List selectBookTypeFk(String booktypename) {
		 // ȡÿ���鳬���涨ʱ�䷣����


			List<BookType> list = new ArrayList<BookType>() ;
			
			try{
			String sql = "SELECT * FROM tb_bookType_wangxin "+
			"WHERE " +"type='"+booktypename+"'" ;
			ResultSet rs = Dao.executeQuery(sql) ;
			while (rs.next()) {
				BookType bt = new BookType() ;
				int id =rs.getInt("id") ;
				String type = rs.getString("type") ;
				int days = rs.getInt("days") ;
				String fk = rs.getString("fk") ;
				bt.setId(id );
				bt.setType(type );
				bt.setDays(days) ;
				bt.setFk(fk) ;
				
				list.add(bt) ;
			}
			rs.close() ;
			
			}catch (Exception e) {
				e.printStackTrace() ;
			}finally{
				Dao.close() ;
				
			}
			return list;

		
		 }
/**
* ͼ����Ϣ����ز���
*/
	
	//����ͼ����Ϣ����
	public static int Insertbook(String ISBN, int typeId, String bookname,
			String writer, String translator, String publisher,
			String publish_date, String price) {
		String sql = "INSERT INTO bookInfo_wangxin " +
				"VALUES('"+ISBN+"',"+typeId+",'"+bookname+"','"+writer+"','"+translator+"','"+publisher+"',to_date('"+publish_date+"','yyyy-MM-dd'),'"+price+"')" ;
		System.out.println(sql);
		return Dao.executeUpdate(sql) ;
	}

	/*
	 * ��ѯͼ�������Ϣ
	 */
	public static List selectBookInfo() {
		List<BookInfo> list = new ArrayList<BookInfo>() ;
		/*
		 * 
		 * ISBN varchar2(13) primary key,
typeId number,
bookName varchar2(40),
writer varchar(21),
translator varchar2(30),
publisher varchar(50),
publish_date date,
price varchar2(10)
);
		 */
		String sql = "SELECT * FROM bookInfo_wangxin " ;
		ResultSet rs = Dao.executeQuery(sql) ;
		try{
		while (rs.next()) {
			BookInfo bi = new BookInfo() ;
			bi.setISBN(rs.getString("ISBN")) ;
			bi.setTypeId(rs.getInt("typeId")) ;
			bi.setBookName(rs.getString("bookName")) ;
			bi.setWriter(rs.getString("writer")) ;
			bi.setPublisher(rs.getString("publisher")) ;
			bi.setPublish_date(rs.getString("publish_date")) ;
			bi.setPrice(rs.getString("price")) ;
			bi.setTranslator(rs.getString("translator")) ;
			list.add(bi) ;
		}
		rs.close() ;
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return list;
	}
/**
 * ��ѯͼ����Ϣ
 * @param ISBN
 * @return List ͼ����Ϣ
 */
	public static List selectBookInfo(String ISBN) {
		
		List<BookInfo> list = new ArrayList<BookInfo>() ;
		try{
		String sql = "SELECT * FROM bookInfo_wangxin " +
				"WHERE ISBN = '"+ISBN+"'" ;
		ResultSet rs = Dao.executeQuery(sql) ;
		while (rs.next()) {
			BookInfo  bi = new BookInfo() ;
			bi.setISBN(rs.getString("ISBN")) ;
			bi.setTypeId(rs.getInt("typeId")) ;
			bi.setWriter(rs.getString("writer")) ;
			bi.setBookName(rs.getString("bookName")) ;
			bi.setTranslator(rs.getString("translator")) ;
			bi.setPublisher(rs.getString("publisher")) ;
			bi.setPublish_date(rs.getString("publish_date")) ;
			bi.setPrice(rs.getString("price")) ;
			
			list.add(bi) ;
		}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
	 return list;
	
	}

	/**
	 * �޸�ͼ����Ϣ����
	 */
	public static int Updatebook(String ISBN, int typeId, String bookname,
			String writer, String translator, String publisher,
			String publish_date, String price) {
		int i =-1 ;
		try {
			/*
			 * create table bookInfo_wangxin(
ISBN varchar2(13) primary key,
typeId number,
bookName varchar2(40),
writer varchar(21),
translator varchar2(30),
publisher varchar(50),
publish_date date,
price varchar2(10)
);
			 */
			String sql = "UPDATE bookInfo_wangxin" +
					" SET typeId ="+typeId+"," +
					"bookName ='"+bookname+"'," +
					"writer = '"+writer+"'," +
					"translator ='"+translator+"'," +
					"publisher = '"+publisher+"'," +
					"publish_date = '"+publish_date+"'," +
					"price ='"+price+"'" +
					" WHERE ISBN ='"+ISBN+"' ";
			System.out.println(sql);
			i=Dao.executeUpdate(sql) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return i;
	}

	/**
	 * �Զ�����Ϣ��ִ�е���ز���
	 */
	// ��tb_reader��������
	public static int InsertReader(String readerName, String gender, int age,
			String job, String identityCard, String vipDate, String tel,
			int maxborrow, String keepMoney, String bztime, String readercode) {
		try{
		String sql = "INSERT INTO tb_reader_wangxin " +
				"VALUES('"+readerName+"','"+gender+"',"+age+",'"+job+"','" +
						""+identityCard+"',to_date('"+vipDate+"','yyyy-MM-dd'),'" +
						""+tel+"',"+maxborrow+",'" +
								""+keepMoney+"',to_date('"+bztime+"','yyyy-MM-dd'),'"+readercode+"')" ;
		System.out.println(sql);
		return Dao.executeUpdate(sql) ;
		}finally{
			Dao.close() ;
		}
	}

	// ��tb_reader��ȫ����Ϣ��ѯ����
	public static List selectReader() {
		List<Reader> list =  new ArrayList<Reader>() ;
		try {

			String sql = "SELECT * FROM tb_reader_wangxin" ;
			ResultSet rs = Dao.executeQuery(sql) ;
			while(rs.next()){
				Reader re = new Reader() ;
				re.setReadername(rs.getString("readername")) ;
				re.setGender(rs.getString("gender")) ;
				re.setAge(rs.getInt("age")) ;
				re.setJob(rs.getString("job")) ;
				re.setIdentityCard(rs.getString("identityCard")) ;
				re.setVipDate(rs.getString("vipDate")) ;
				re.setTel(rs.getString("tel")) ;
				re.setMaxborrow(rs.getInt("maxborrow")) ;
				re.setKeepMoney(rs.getString("keepMoney")) ;
				re.setBztime(rs.getString("bztime")) ;
				re.setReadercode(rs.getString("readercode")) ;
				
				list.add(re) ;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return list;
	}

	// ͨ��ISBN��ѯ��tb_reader������
	public static List selectReader(String readerISBN) {
		List<Reader> list =  new ArrayList<Reader>() ;
		try {

			String sql = "SELECT * " +
					"FROM tb_reader_wangxin  " +
					"WHERE  readercode='"+readerISBN+"'" ;
			ResultSet rs = Dao.executeQuery(sql) ;
			while(rs.next()){
				Reader re = new Reader() ;
				re.setReadername(rs.getString("readername")) ;
				re.setGender(rs.getString("gender")) ;
				re.setAge(rs.getInt("age")) ;
				re.setJob(rs.getString("job")) ;
				re.setIdentityCard(rs.getString("identityCard")) ;
				re.setVipDate(rs.getString("vipDate")) ;
				re.setTel(rs.getString("tel")) ;
				re.setMaxborrow(rs.getInt("maxborrow")) ;
				re.setKeepMoney(rs.getString("keepMoney")) ;
				re.setBztime(rs.getString("bztime")) ;
				re.setReadercode(rs.getString("readercode")) ;
				
				list.add(re) ;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return list;
	}

	// ����tb_reader���е�����
	public static int UpdateReader(String readerName, String gender, int age,
			String job, String identityCard, String vipDate, String tel,
			int maxBorrow, String keepMoney, String bztime, String readerCode) {
		int i =-1 ;
		try {
			/*
			 * readername varchar2(10),
gender varchar2(2),
age number,
job varchar2(20),
identityCard varchar2(30),
vipDate date,
tel varchar2(50),
maxborrow number(10),
keepMoney varchar2(100), 
bztime date, 
readercode varchar2(30) 
);
			 */
			
			String sql = "UPDATE tb_reader_wangxin" +
					" SET readername ='"+readerName+"'," +
					"gender = '"+gender+"'," +
					"age ="+age+"," +
					"job = '"+job+"'," +
					"identityCard = '"+identityCard+"'," +
					"vipDate = '"+vipDate+"'," +
					" tel ='"+tel+"'," +
					"maxborrow ="+maxBorrow+"," +
					"keepMoney ='"+keepMoney+"'," +
					"bztime = '"+bztime+"'" +
							"WHERE readercode = '"+readerCode+"'" ;
			System.out.println(sql);
			i=Dao.executeUpdate(sql) ;
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return i;
	}

	// ͨ��readerCoderɾ��tb_reader�е�����
	public static int DelReader(String readerCoder) {
		int i =-1 ;
		String sql = "delete from tb_reader_wangxin" +
		" where readercode = "+readerCoder+"" ;
		System.out.println(sql);
		i = Dao.executeUpdate(sql) ;
		return i;
	}

	/*
	 * ��ͼ�鶩����Ϣ�����
	 */

	/*
	 * ���빺�鶩��
	 * @return int i :executeUpdate�ķ���ֵ����ʾ�Ƿ���³ɹ�
	 *
	 */
	public static int InsertBookOrder(String ISBN, String dgdate, int dgnumber,
			String operator, int checkAndAccept, String zk) {
		int i=-1;
		try{
			String sql = "INSERT INTO tb_order_wangxin " +
					"VALUES('"+ISBN+"','"+dgdate+"',"+dgnumber+",'"
					+operator+"',"+checkAndAccept+",'"+zk+"')";
			System.out.println(sql);
			i= Dao.executeUpdate(sql) ;
			}finally{
				Dao.close() ;
			}
		return i;

	}
	//��ѯ���鶩��
	public static List selectBookOrder() {
		List<OrderAndBookInfo>list = new ArrayList<OrderAndBookInfo>() ;
		try {
			String sql = "SELECT a.*,b.* " +
					"FROM tb_order_wangxin a,bookInfo_wangxin b " +
					"WHERE a.ISBN=b.ISBN  " ;
			ResultSet rs = Dao.executeQuery(sql) ;
			while (rs.next()) {
				OrderAndBookInfo order = new OrderAndBookInfo() ;
				
				order.setBookName(rs.getString("bookName"));
				order.setPrice(rs.getString("price"));
				order.setPublish_date(rs.getString("publish_date"));
				order.setPublisher(rs.getString("publisher"));
				order.setTypeId(rs.getInt("typeId"));
				order.setWriter(rs.getString("writer"));
				order.setTranslator(rs.getString("translator"));
				
				order.setISBN(rs.getString("ISBN")) ;
				order.setOrderdate(rs.getString("dgdate")) ;
				order.setDgnumber(rs.getInt("Dgnumber")) ;
				order.setOperator(rs.getString("operator")) ;
				order.setCheckAndAccept(rs.getInt("checkAndAccept")) ;
				order.setDiscount(rs.getString("zk")) ;
				
				list.add(order) ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return list;
	}
//ͨ��ISBN��ѯ���鶩��
	public static List selectBookOrder(String ISBN) {
		List<OrderAndBookInfo>list = new ArrayList<OrderAndBookInfo>() ;
		try {
			String sql = "SELECT a.*,b.* " +
					"FROM tb_order_wangxin a,bookInfo_wangxin b " +
					"WHERE a.ISBN=b.ISBN  " +
					"AND a.ISBN="+ISBN ;
			ResultSet rs = Dao.executeQuery(sql) ;
			while (rs.next()) {
				OrderAndBookInfo order = new OrderAndBookInfo() ;
				
				order.setBookName(rs.getString("bookName"));
				order.setPrice(rs.getString("price"));
				order.setPublish_date(rs.getString("publish_date"));
				order.setPublisher(rs.getString("publisher"));
				order.setTypeId(rs.getInt("typeId"));
				order.setWriter(rs.getString("writer"));
				order.setTranslator(rs.getString("translator"));
				
				order.setISBN(rs.getString("ISBN")) ;
				order.setOrderdate(rs.getString("dgdate")) ;
				order.setDgnumber(rs.getInt("Dgnumber")) ;
				order.setOperator(rs.getString("operator")) ;
				order.setCheckAndAccept(rs.getInt("checkAndAccept")) ;
				order.setDiscount(rs.getString("zk")) ;
				
				list.add(order) ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return list;
	}
//���¹��鶩��
	public static int UpdateCheckBookOrder(String ISBN) {
		int i=-1;
		try{
		String sql = "UPDATE tb_order_wangxin " +
		" SET checkAndAccept =0"+
				"WHERE ISBN = '"+ISBN+"'" ;
		System.out.println(sql);
		i=Dao.executeUpdate(sql) ;
		if(i>0){
			return 1;
		}
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
	}
		return 0;

	}

	/*
	 * �Խ��ı���в���
	 */
	// ����ͼ��������ݿ��������
	public static int InsertBookBorrow(String bookISBN, String readerISBN
			,int operatorId, String borrowDate, String backDate) {
		/*
		 * create table tb_borrow_wangxin(
id number primary key,
bookISBN varchar2(13),
operatorID number,
readerISBN varchar2(13),

borrowDate varchar2(50),
backDate varchar2(50)
);
		 */

		int i=-1;
		int id=0;
		try{
			String idSql="SELECT MAX(id) id FROM tb_borrow_wangxin";
			Connection conn=DBUtil.getConnection();
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery(idSql);
			
			if(rs.next()){
				id=rs.getInt("id");
			}
			id++;
			rs.close();
			
			String sql = "INSERT INTO tb_borrow_wangxin " +
					"VALUES("+id+",'"+bookISBN+"',"+operatorId+",'"
					+readerISBN+"',1,'"+borrowDate+"','"+backDate+"')";
			System.out.println(sql);
			i= Dao.executeUpdate(sql) ;
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				Dao.close() ;
			}
		return i;
	}

	// �鿴���Ĺ���ͼ��
	public static List selectBorrow(String readerISBN) {
		int id=0;
		List<Borrow>list = new ArrayList<Borrow>() ;
		try {
			String idSql="SELECT MAX(id)  id FROM tb_borrow_wangxin";
			Connection conn=DBUtil.getConnection();
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery(idSql);
			
			if(rs.next()){
				id=rs.getInt("id");
			}
			id++;
			rs.close();
			String sql = "SELECT * " +
					"FROM tb_borrow_wangxin " +
					"WHERE readerISBN="+readerISBN;
			rs = Dao.executeQuery(sql) ;
			while (rs.next()) {
				Borrow borrow=new Borrow();
				borrow.setId(id);
				borrow.setBookISBN(rs.getString("bookISBN"));
				borrow.setOperatorID(rs.getInt("operatorID"));
				borrow.setReaderISBN(rs.getString("readerISBN"));
				borrow.setBorrowDate(rs.getString("borrowDate"));
				borrow.setDate(rs.getString("backDate"));
				
				list.add(borrow);
				
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return list;
	}

	/*
	 * ��ѯ�������ݣ�tb_bookinfo tb_reader tb_borrow֮��Ĳ�ѯ
	 */
	// ��ѯ�������
	public static List selectBookBack(String readercode) {
		int id=0;
		List<Back>list = new ArrayList<Back>() ;
		try {
			String sql = "SELECT a.*,b.*,c.* " +
			" FROM tb_borrow_wangxin a," +
					"tb_bookinfo_wangxin b ,tb_reader_wangxin c" +
			" WHERE a.readerISBN=c.readercode AND " +
					"b.ISBN=a.bookISBN  AND " +
					"c.readercode='"+readercode+"'";
			System.out.println(sql);
			ResultSet rs = Dao.executeQuery(sql) ;
			while (rs.next()) {
				Back borrow=new Back();
				
				borrow.setReaderISBN(rs.getString("readerISBN"));
				borrow.setOperatorID(rs.getInt("operatorID"));
				borrow.setBackDate(rs.getString("backDate"));
				borrow.setBookISBN(rs.getString("bookISBN"));
				borrow.setBorrowDate(rs.getString("borrowDate"));
				borrow.setBookName(rs.getString("bookName"));
				borrow.setId(rs.getInt("id"));
				borrow.setIsback(rs.getInt("isback"));
				borrow.setReaderID(rs.getString("readercode"));
				borrow.setReaderName(rs.getString("ReaderName"));
				borrow.setTypeId(rs.getInt("TypeId"));
				
				System.out.println("3");
				list.add(borrow);
				
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
		return list;
	}
	// �黹ͼ�����
	public static int  UpdateBookBack(String bookISBN, 
			String readerISBN, int id) {

		int i =-1 ;
		String sql = "delete from tb_borrow_wangxin " +
		" where readerISBN = '"+readerISBN+"'" ;
		System.out.println(sql);
		i = Dao.executeUpdate(sql) ;
		System.out.println(i);
		return i;

	}
	//��ѯͼ����Ϣ
	public static List selectbookserch() {
		
		List<BookInfo> list = new ArrayList<BookInfo>() ;
		try{
		String sql = "SELECT * FROM bookInfo_wangxin ";
		ResultSet rs = Dao.executeQuery(sql) ;
		while (rs.next()) {
			BookInfo  bi = new BookInfo() ;
			bi.setISBN(rs.getString("ISBN")) ;
			bi.setTypeId(rs.getInt("typeId")) ;
			bi.setWriter(rs.getString("writer")) ;
			bi.setBookName(rs.getString("bookName")) ;
			bi.setTranslator(rs.getString("translator")) ;
			bi.setPublisher(rs.getString("publisher")) ;
			bi.setPublish_date(rs.getString("publish_date")) ;
			bi.setPrice(rs.getString("price")) ;
			
			list.add(bi) ;
		}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
	 return list;
	
	}
	//ģ����ѯ
	public static List selectbookmohu(String bookname) {
		
		List<BookInfo> list = new ArrayList<BookInfo>() ;
		try{
		String sql = "SELECT * FROM bookInfo_wangxin " +
				"WHERE bookName='"+bookname+"'";
		ResultSet rs = Dao.executeQuery(sql) ;
		while (rs.next()) {
			BookInfo  bi = new BookInfo() ;
			bi.setISBN(rs.getString("ISBN")) ;
			bi.setTypeId(rs.getInt("typeId")) ;
			bi.setWriter(rs.getString("writer")) ;
			bi.setBookName(rs.getString("bookName")) ;
			bi.setTranslator(rs.getString("translator")) ;
			bi.setPublisher(rs.getString("publisher")) ;
			bi.setPublish_date(rs.getString("publish_date")) ;
			bi.setPrice(rs.getString("price")) ;
			
			list.add(bi) ;
		}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
	 return list;
	
	}
	//��������ģ����ѯ
	public static List selectbookmohuwriter(String writer) {
		
		List<BookInfo> list = new ArrayList<BookInfo>() ;
		try{
		String sql = "SELECT * FROM bookInfo_wangxin " +
				"WHERE writer='"+writer+"'";
		ResultSet rs = Dao.executeQuery(sql) ;
		while (rs.next()) {
			BookInfo  bi = new BookInfo() ;
			bi.setISBN(rs.getString("ISBN")) ;
			bi.setTypeId(rs.getInt("typeId")) ;
			bi.setWriter(rs.getString("writer")) ;
			bi.setBookName(rs.getString("bookName")) ;
			bi.setTranslator(rs.getString("translator")) ;
			bi.setPublisher(rs.getString("publisher")) ;
			bi.setPublish_date(rs.getString("publish_date")) ;
			bi.setPrice(rs.getString("price")) ;
			
			list.add(bi) ;
		}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
	 return list;
	
	}

	// �����û�
	public static int Insertoperator(String name, String gender,
			int age,String identityCard, String workdate, 
			String tel, String password) {
		/*create table tb_operator_wangxin(
	id number primary key,
	name varchar2(12),
	gender varchar2(2),
	age number,
	identityCard varchar2(30),
	workdate date,
	tel varchar(50),
	admin number,
	password varchar2(10)); */
		int id=1;
		try{
			String idSql="SELECT MAX(id) id FROM tb_operator_wangxin";
			Connection conn=DBUtil.getConnection();
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery(idSql);
			if(rs.next()){
				id=rs.getInt("id");
			}
			id++;
			rs.close();
			
			String sql = "INSERT INTO tb_operator_wangxin " +
			"VALUES("+id+",'"+name+"','"+gender+"',"+age+",'"+
			identityCard+"','"+workdate+"'," +
			"'"+tel+"',"+id+",'"+password+"')";
			System.out.println(sql);
			if(Dao.executeUpdate(sql)>0){
				return 1;
			}
			
			}  catch (Exception e) {
				e.printStackTrace();
			}finally{
				Dao.close() ;
			}
			return 0;
		}

	// ��ѯ�û�
	public static List selectuser() {
		
		List<User> list = new ArrayList<User>() ;
		try{
		String sql = "SELECT * FROM tb_operator_wangxin ";
		ResultSet rs = Dao.executeQuery(sql) ;
		while (rs.next()) {
			User  bi = new User() ;
			bi.setAdmin(rs.getInt("admin"));
			bi.setAge(rs.getInt("age"));
			bi.setGender(rs.getString("gender"));
			bi.setId(rs.getInt("id"));
			bi.setIdentityCard(rs.getString("identityCard"));
			bi.setName(rs.getString("name"));
			bi.setPassword(rs.getString("password"));
			bi.setTel(rs.getString("tel"));
			bi.setWorkdate(rs.getString("workdate"));
			
			list.add(bi) ;
		}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			Dao.close() ;
		}
	 return list;
	
	}

	// ɾ���û�
	public static int Deluser(int id) {
		int i=0;
		try
		{
		String sql = "delete from tb_operator_wangxin " +
		" where id = "+id+"" ;
		System.out.println(sql);
		
		i=Dao.executeUpdate(sql) ;
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			Dao.close();
		}
		return i;
	}

	// �����û�
	public static int Updateuser(int id, String name, String gender, int age,
			String identityCard, String workdate, String tel, String password) {
		int i=-1;
		try{
			String sql = "UPDATE tb_operator_wangxin  " +
			"SET name='"+name+"',gender='"+gender+"',age="
			+age+",identityCard='"+
			identityCard+"',workdate='"+workdate+"',tel=" +
			"'"+tel+"',admin="+1+",password='"+password+"' WHERE id="+id+"";
			System.out.println(sql);
			i=Dao.executeUpdate(sql) ;
		if(i>0){
			System.out.println(i);
			
			return 1 ;
		}
		}finally{
			Dao.close() ;
		}
	return i;
	}
//�޸�����
	public static int Updatepass(String password, 
			String name) {
	
		int i=-1;
		try{
			String sql = "UPDATE tb_operator_wangxin  " +
			" SET password ='"+password+"', name='"+name+"'";
			System.out.println(sql);
			i=Dao.executeUpdate(sql) ;
		if(i>0){
			System.out.println(i);
			
			return 1 ;
		}
		}finally{
			Dao.close() ;
		}
	return i;
	}
/*
 * date���� 
 * ��ѯ����� �����ַ������������Ǽ�da	
 */
}
