package com.dl.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dl.dao.WordDetailDao;
import com.dl.entity.WordDetail;
import com.dl.utils.ReflectionUtil;
import com.dl.utils.db.JdbcUtil;


/**
 * 
 * 文件名称：EmployeeDaoImpl.java<br>
 * 摘要：数据层操作类，只需要数据库连接即可，不负责关闭<br>
 * -------------------------------------------------------<br>
 * 作者：胡毅<br>
 * 完成日期：2015年6月29日<br>
 */
public class WordDetailDaoImpl extends BaseDaoImpl<WordDetail, Serializable> implements WordDetailDao{
	
	private JdbcUtil jdbcutil = null;
	
	public WordDetailDaoImpl(JdbcUtil jdbcutil){
		this.jdbcutil = jdbcutil;
	}

	@Override
	public List<WordDetail> findAll() throws Exception {
		List<WordDetail> list = new ArrayList<WordDetail>();
		String sql = "select * from  [wordindex].[dbo].[wordDetail]";
		PreparedStatement statm = jdbcutil.getPreparedStatement(sql);
		ResultSet rs = statm.getResultSet();
		while (rs.next()) {
			WordDetail e = new WordDetail();
			e.setWord(rs.getString("word"));
			e.setWordF(rs.getString("wordF"));
			e.setWordC(rs.getString("wordC"));
			e.setWordD(rs.getString("wordD"));
			e.setWordEnglish(rs.getString("wordEnglish"));
			list.add(e);
		}
		jdbcutil.close(statm, rs);
		return list;
	}

	@Override
	public List<WordDetail> findOne(String id) throws Exception {
		List<WordDetail> list = new ArrayList<WordDetail>();
		String sql = "select * from [wordindex].[dbo].[wordDetail]ordDetail where id ='"+id+"'";
		PreparedStatement statm = jdbcutil.getPreparedStatement(sql);
		ResultSet rs = statm.getResultSet();
		while (rs.next()) {
			WordDetail e = new WordDetail();
			e.setWord(rs.getString("word"));
			e.setWordF(rs.getString("wordF"));
			e.setWordC(rs.getString("wordC"));
			e.setWordD(rs.getString("wordD"));
			e.setWordEnglish(rs.getString("wordEnglish"));
			list.add(e);
		}
		jdbcutil.close(statm, rs);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findMapByKey(String id) throws Exception {
		ReflectionUtil<WordDetail> e = new ReflectionUtil<WordDetail>();
		e.getFeilds(new WordDetail());
		List<String> fields = e.getFieldName();
		Map<String,Object> entity = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "select * from  [wordindex].[dbo].[wordDetail] where 1=1 ";
		if(id.length()>0){
			sql += "word ='"+id+"'";
		}
		PreparedStatement statm = jdbcutil.getPreparedStatement(sql);
		ResultSet rs = statm.getResultSet();
		while (rs.next()) {
			entity = new HashMap<String, Object>();
			for (int i = 1; i <= fields.size(); i++) {
				entity.put(fields.get(i-1), rs.getObject(i));
			}
			list.add(entity);
		}
		jdbcutil.close(statm, rs);
		return list;
	}

	@Override
	public int supdate(WordDetail wd) throws SQLException {
		// 验证是否存在改记录，存在则更新
		String valid = "select count(*) as count from [wordindex].[dbo].[wordDetail] where word='"+wd.getWord()+"'";
		int count = 0;
		PreparedStatement statm = jdbcutil.getPreparedStatement(valid);
		ResultSet rs = statm.getResultSet();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		if(count>0){
			String update = "update [wordindex].[dbo].[wordDetail] set wordf='"+wd.getWordF()+"'"
					+ " , wordc='"+wd.getWordC()+"' "
					+ " , wordd='"+wd.getWordD()+"' "
					+ " , wordenglish='"+wd.getWordEnglish()+"' where word='"+wd.getWord()+"' ";
			statm = jdbcutil.getPreparedStatement(update);
		}else{
			String insert  = "insert into [wordindex].[dbo].[wordDetail] (id,word,wordf,wordc,wordd,wordenglish)" +
					" values('"+wd.getId()+"','"+wd.getWord()+"','"+wd.getWordF()+"','"+wd.getWordC()+"','"+wd.getWordD()+"','"+wd.getWordEnglish()+"')";
			statm = jdbcutil.getPreparedStatement(insert);
		}
		count = statm.executeUpdate();
		jdbcutil.close(statm, rs);
		return count;
	}
}
