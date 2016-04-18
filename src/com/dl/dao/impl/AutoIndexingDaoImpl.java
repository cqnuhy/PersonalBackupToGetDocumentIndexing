package com.dl.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dl.dao.AutoIndexingDao;
import com.dl.entity.AutoIndexing;
import com.dl.utils.db.JdbcUtil;

public class AutoIndexingDaoImpl extends BaseDaoImpl<AutoIndexing, Serializable> implements
		AutoIndexingDao {
		
		private JdbcUtil jdbcutil = null;
		
		public AutoIndexingDaoImpl(JdbcUtil jdbcutil){
			this.jdbcutil = jdbcutil;
		}
	
		@Override
		public List<AutoIndexing> findAll() throws Exception {
			List<AutoIndexing> list = new ArrayList<AutoIndexing>();
			String sql = "select * from  [wordindex].[dbo].[autoIndexing]";
			PreparedStatement statm = jdbcutil.getPreparedStatement(sql);
			ResultSet rs = statm.getResultSet();
			while (rs.next()) {
				AutoIndexing e = new AutoIndexing();
				e.setId(rs.getString("id"));
				e.setTitle(rs.getString("title"));
				e.set_abstract(rs.getString("abstract"));
				e.setResult(rs.getInt("result"));
				e.setMessage(rs.getString("message"));
				e.setIndexingConcepts(rs.getString("indexingConcepts"));
				e.setIndexingWords(rs.getString("indexingWords"));
				e.setClcNumbers(rs.getString("clcNumbers"));
				list.add(e);
			}
			jdbcutil.close(statm, rs);
			return list;
		}

	@Override
	public int supdate(AutoIndexing ai) throws SQLException{
		// 验证是否存在改记录，存在则更新
		String valid = "select count(*) as count from [wordindex].[dbo].[autoIndexing] where id='"+ai.getId()+"'";
		int count = 0;
		PreparedStatement statm = jdbcutil.getPreparedStatement(valid);
		ResultSet rs = statm.getResultSet();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		if(count>0){
			String update = "update [wordindex].[dbo].[autoIndexing] set result='"+ai.getResult()+"'"
					+ " , message='"+ai.getMessage()+"' "
					+ " , indexingConcepts='"+ai.getIndexingConcepts()+"' "
					+ " , indexingWords='"+ai.getIndexingWords()+"' " 
					+ ", clcNumbers='"+ai.getClcNumbers()+"' where title='"+ai.getTitle()+"' ";
			statm = jdbcutil.getPreparedStatement(update);
		}else{
			String insert  = "insert into [wordindex].[dbo].[autoIndexing] (id,title,abstract,result,message,indexingConcepts,indexingWords,clcNumbers)" +
					" values('"+ai.getId()+"','"+ai.getTitle()+"','"+ai.get_abstract()+"',"+ai.getResult()+",'"+ai.getMessage()+"'," +
							"'"+ai.getIndexingConcepts()+"','"+ai.getIndexingWords()+"','"+ai.getClcNumbers()+"')";
			statm = jdbcutil.getPreparedStatement(insert);
		}
		count = statm.executeUpdate();
		
		jdbcutil.close(statm, rs);
		return count;
	}
}
