package com.ict.erp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.erp.vo.LevelInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/root-context.xml")
public class DataSourceTest {

	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory ssf;
	
	@Autowired
	private SqlSession ss;
	
	@Test
	public void test() {
		try {
		Connection con = ds.getConnection();
		System.out.println("테스트 완료");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	@Test
	public void ssfTest() {
		try(SqlSession ss = ssf.openSession()){
			System.out.println("Sql Session 생성 테스트 완료");
		}catch(Exception e) {
			fail(e.getMessage());

		}
	}
	@Test
	public void ssTest() {
		assertNotNull(ss);
		List<LevelInfo> liList = ss.selectList("SQL.LEVELINFO.selectLevelInfo");
		assertEquals(liList.size(), 3);
	}
	@Test
	public void insertTest() {
		LevelInfo li = new LevelInfo();
		li.setLilevel(4);
		li.setLiname("테스트");
		li.setLidesc("테스트 데이터");
//		assertEquals(ss.insert("SQL.LEVELINFO.insertLevelInfo", li), 1);
	}
	@Test
	public void updateTest() {
		LevelInfo li = new LevelInfo();
		li.setLinum(5);
		li.setLilevel(4);
		li.setLiname("updateTest");
		li.setLidesc("updateTest Date");
//		assertEquals(ss.update("SQL.LEVELINFO.updateLevelInfo", li), 1);
	}
	@Test
	public void deleteTest() {
		LevelInfo li = new LevelInfo();
		li.setLinum(5);
//		assertEquals(ss.delete("SQL.LEVELINFO.deleteLevelInfo", li), 1);
	}
	
}
