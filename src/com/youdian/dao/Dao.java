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
 * 公共类DAO 用于进行数据库连接和操作
 * DAO:Data Access Object 数据库访问对象 把对数据库的操作封装起来，方便其他人使用
 */

public class Dao {
	/**
	 * 类属性：
	 *  dbClassName:数据库驱动器(driver) 
	 * dbURL:url,统一资源定位符 
	 * dbUser:数据库用户名
	 * dbPwd:数据库密码
	 *  Connection conn：数据库连接
	 */
	static Connection conn ;

	public Dao() throws SQLException {
		/*
		 * 在构造器中初始化获得Connection 用一个if判断，是否已经有conn，如果没有则创建
		 */
		try {
			
			if(conn!=null){
				System.out.println("连接已建立");
			}else {
				conn=DBUtil.getConnection();
			}
			System.out.println("数据库已连接");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return ResultSet: 数据库查询结果
	 * @param String sql：查询语句 
	 * 先判断是否有conn
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
	 * @return int,更新不成功则返回-1，成功则返回
	 * Statement stmt.executeQuery的返回值
	 *  判断conn是否存在
	 * @exception ： SQLException
	 */
	private static int executeUpdate(String sql) {
		int flag=-1;
		try{
			
			if(conn == null){
				conn = DBUtil.getConnection() ;
			
			}
				Statement state = conn.createStatement() ;
				
				 flag =state.executeUpdate(sql) ;
				//executeUpdate更新的条数
				return flag ;
			
			}catch (Exception e) {
				e.printStackTrace() ;
				return -1;
			}
	}

	/**
	 * 关闭数据库连接Connection
	 * @exception：SQLException 
	 * finally 对conn恢复初始值null
	 */
	public static void close() {
		DBUtil.closeConnection() ;
		conn = null ;
	}

	/**
	 * 管理员登录方法
	 * @param name 登录用户名
	 * @param password 密码
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
			System.out.println("正确");
			op.setName(name) ;
			op.setPassword(password) ;
			return op ;
		}else {
			System.out.println(i);
			System.out.println("错误");
			
			return op ;
		}
		}finally{
			Dao.close() ;
		}
		
		
	}

	/*
	 * 查询类别方法
	 */
	// 图书类别查询
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

	// 图书类别查询
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
 * 图书类别表相关操作
*/
	// 给图书类别插入数据
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
	//更新图书类别方法，返回executeUpdate的返回值
	public static int UpdatebookType(String id, String bookTypeName,
			String days, String fine) {
		int i=0;
		/**
		 * 逻辑代码
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
	

	// 图书类别删除方法
	public static int DelbookType(long id) {
		int i=-1;
		String sql = "delete from tb_bookType_wangxin" +
				" where id = "+id ;
		System.out.println(sql);
		i = Dao.executeUpdate(sql) ;
		return i;

	}
	 public static List selectBookTypeFk(String booktypename) {
		 // 取每种书超过规定时间罚款金额


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
* 图书信息表相关操作
*/
	
	//插入图书信息方法
	public static int Insertbook(String ISBN, int typeId, String bookname,
			String writer, String translator, String publisher,
			String publish_date, String price) {
		String sql = "INSERT INTO bookInfo_wangxin " +
				"VALUES('"+ISBN+"',"+typeId+",'"+bookname+"','"+writer+"','"+translator+"','"+publisher+"',to_date('"+publish_date+"','yyyy-MM-dd'),'"+price+"')" ;
		System.out.println(sql);
		return Dao.executeUpdate(sql) ;
	}

	/*
	 * 查询图书相关信息
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
 * 查询图书信息
 * @param ISBN
 * @return List 图书信息
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
	 * 修改图书信息方法
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
	 * 对读者信息表执行的相关操作
	 */
	// 给tb_reader插入数据
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

	// 将tb_reader的全部信息查询出来
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

	// 通过ISBN查询出tb_reader的数据
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

	// 更新tb_reader表中的数据
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

	// 通过readerCoder删除tb_reader中的数据
	public static int DelReader(String readerCoder) {
		int i =-1 ;
		String sql = "delete from tb_reader_wangxin" +
		" where readercode = "+readerCoder+"" ;
		System.out.println(sql);
		i = Dao.executeUpdate(sql) ;
		return i;
	}

	/*
	 * 对图书订购信息表操作
	 */

	/*
	 * 输入购书订单
	 * @return int i :executeUpdate的返回值，表示是否更新成功
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
	//查询购书订单
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
//通过ISBN查询购书订单
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
//更新购书订单
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
	 * 对借阅表进行操作
	 */
	// 借阅图书后往数据库插入数据
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

	// 查看借阅过的图书
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
	 * 查询还书内容，tb_bookinfo tb_reader tb_borrow之间的查询
	 */
	// 查询还书情况
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
	// 归还图书操作
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
	//查询图书信息
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
	//模糊查询
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
	//根据作者模糊查询
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

	// 插入用户
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

	// 查询用户
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

	// 删除用户
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

	// 更新用户
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
//修改密码
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
 * date问题 
 * 查询语句中 引用字符串类型总忘记加da	
 */
}
