package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.org.apache.xml.internal.dtm.DTMDOMException;

public class BoardDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언한다.
	// 2. 정적 멤버로 선언한다. -static으로 선언

	private static BoardDAO instance = new BoardDAO();// static:메서드 영역에 만들어진다아

	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야한다. -private으로 생성자 생성
	// 외부에서 new를 사용하지 못하게 하는 접근 기법.
	private BoardDAO() {
	}

	// 4. 생성자 대신에 싱글톤 객체를 return 해주는 getInstance() 메서드를 만들어 주자.
	public static BoardDAO getInstance() {// static에 있는 instance를 받아줘야하기때문에 static
		if (instance == null) {// 객체생성했기때문에 null일리가 없지만 혹시나모르니까아...
			instance = new BoardDAO();
		}
		return instance; // 참조변수 리턴!
	}

	public Connection openConn() {

		try {
			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();

			// 2.lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.

			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");

			// 3.DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}// openConn() end

	//Board1 테이블의 전체 리스트를 조회하는 메서드
	public List<BoardDTO> getBoardList(){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			con = openConn();
			sql = "select * from board1 order by board_no desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				list.add(dto);
			}
			//open객체 닫기
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	} //getBoardList() end
	
	//board1테이블에 게시글을 추가하는 메서드
	public int insertBoard(BoardDTO dto) {
		int result=0, count = 0;
		
		try {
			con = openConn();
			//commit을 끝내지않고! 문제가 생기면 rollback! - 추가나 삭제시에 사용
			con.setAutoCommit(false);
			sql = "select max(board_no) from board1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1; //첫번째 인덱스에 있는 값을 int형으로 가져와라..
			}else {
				count = 1; //게시판 첫글일떈 max: null값 ->count 초기값을 1로 두고 else문 지워버려도된다ㅏㅇ..
			}
			
			sql="insert into board1 values(?,?,?,?,?,default,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			result = pstmt.executeUpdate();
			con.commit(); //완전히 DB에 저장하는 메서~ㅡㄷ
			
			//open객체 닫기
			/*rs.close();pstmt.close();
			con.close();*/
			
			
			
		} catch (SQLException e) {
			//처리도중에 문제가 발생한 경우 이전상태로 되돌리는 메서드
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}//insertBoard() 메서드 end
	
	//board1 테이블의 글번호에 해당하는 글을 가져오는 메서드
	public BoardDTO boardCont(int no) {
		BoardDTO dto = new BoardDTO();
		try {
			
			con = openConn();
			//조회수증가
			sql = "update board1 set board_hit = board_hit + 1 where board_no=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			//상세내역 쿼리문 작성.
			sql= "select * from board1 where board_no=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}//boardCont() 메서드 end
	

	
	public int boardCheck(int no, String pwd) {
		int res = 0;
		BoardDTO dto = new BoardDTO();
		
		
		try {
			con=openConn();
			sql="select * from board1 where board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString("board_pwd"))) {
					res=1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}//boardCheck() end
	
	public BoardDTO getUpdateList(int no) {
		BoardDTO dto = new BoardDTO();
		con =openConn();
		sql ="select * from board1 where board_no=?";
		try {
			con =openConn();
			sql ="select * from board1 where board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}//getUpdateList() end
	
	public BoardDTO boardUpdate(int no) {
		BoardDTO dto = new BoardDTO();
		
		try {
			con = openConn();
			con.setAutoCommit(false);
			sql ="select * from board1 where board_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			try { 
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return dto;
	}//boardUpdate() end
	
	public int boardUpdateOk(BoardDTO dto) {
		int res = 0;
		
		try {
			con = openConn();
			sql = "update board1 set board_writer=?, board_title=?,board_cont=?,board_pwd=? where board_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(5, dto.getBoard_no());
			pstmt.setString(1, dto.getBoard_writer());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_pwd());
			res= pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}//boardUpdateOk() end;
	
	public int boardDelete(int no) {
		int res=0;
		
		try {
			con = openConn();
			con.setAutoCommit(false);
			sql = "delete from board1 where board_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			res= pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			//처리도중에 문제가 발생한 경우 이전상태로 되돌리는 메서드
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
		return res;
		
	}//boardDelete() end
	
	//검색해서 나온값을 List에 저장 반환~~~
	public List<BoardDTO> boardSearch(String ff, String fn){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		openConn();
		
		try {
			if(ff.equals("title")) {
				sql="select * from board1 where board_title like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+fn+"%");
						
			}else if(ff.equals("content")) {
				sql="select * from board1 where board_content like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+fn+"%");
			}else if(ff.equals("title_content")) {
				sql="select * from board1 where board_title like ? or board_cont like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+fn+"%");
				pstmt.setString(2, "%"+fn+"%");
			}else if(ff.equals("writer")) {
				sql="select * from board1 where board_writer like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+fn+"%");
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				
				list.add(dto);
			}
			
			rs.close();pstmt.close();con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}//boardSearch() end;
}
