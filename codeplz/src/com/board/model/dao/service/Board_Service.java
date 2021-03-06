package com.board.model.dao.service;
import com.board.model.dao.Board_Dao;
import com.board.model.vo.Board;
import static com.common.connect.JDBCTemplate.*;

import java.net.ConnectException;
import java.sql.*;
import java.util.*;




public class Board_Service {
	
	public ArrayList<Board> selectList(int currentPage, int limit){
		Connection result = getConnection();
		
		ArrayList<Board> list = new Board_Dao().selectList(result, currentPage , limit);
		
		System.out.println(list);
		close(result);
		return list;
		
	}

	public int getListCount() {
		Connection result =getConnection();
		int listCount = new Board_Dao().getListCount(result);
		close(result);
		return listCount;
	}

	public Board Detail(int index) {
		Connection result = getConnection();
		Board b = new Board_Dao().Detail(result, index);
		int num  = 0;
		// Detail 에서 호출 했을 경우를 탐색
		StackTraceElement[] a = new Throwable().getStackTrace();
		
		if(b !=null && a[1].getClassName().contains("Detail")){
			num = new Board_Dao().updateCount(result , index);
		}
		if(num > 0) commit(result);
		else rollback(result);
		
		return b;
	}
	
}
