package com.dl.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dl.dao.AutoIndexingDao;
import com.dl.entity.AutoIndexing;

public class AutoIndexingDaoImpl extends BaseDaoImpl<AutoIndexing, Serializable> implements
		AutoIndexingDao {
	
	private ResultSet rs ;

	// 在业务层处理完成后自行关闭数据连接
		public AutoIndexingDaoImpl(Connection conn) {
			super.initBaseDaoImpl(conn);
		}
		
		@Override
		public List<AutoIndexing> findAll() throws Exception {
			List<AutoIndexing> list = new ArrayList<AutoIndexing>();
			String sql = "select * from  [wordindex].[dbo].[autoIndexing]";
			this.setPreparedStatement(sql);
			this.rs = this.getResultSet();
			while (this.rs.next()) {
				AutoIndexing e = new AutoIndexing();
				e.setId(this.rs.getString("id"));
				e.setTitle(this.rs.getString("title"));
				e.set_abstract(this.rs.getString("abstract"));
				e.setResult(this.rs.getInt("result"));
				e.setMessage(this.rs.getString("message"));
				e.setIndexingConcepts(this.rs.getString("indexingConcepts"));
				e.setIndexingWords(this.rs.getString("indexingWords"));
				e.setClcNumbers(this.rs.getString("clcNumbers"));
				list.add(e);
			}
			return list;
		}

	@Override
	public int supdate(AutoIndexing ai) {
		// 验证是否存在改记录，存在则更新
		String valid = "select count(*) as count from [wordindex].[dbo].[autoIndexing] where id='"+ai.getId()+"'";
		int count = 0;
		try {
			this.setPreparedStatement(valid);
			this.rs = this.getResultSet();
			while (this.rs.next()) {
				count = rs.getInt("count");
			}
			if(count>0){
				String update = "update [wordindex].[dbo].[autoIndexing] set result='"+ai.getResult()+"'"
						+ " , message='"+ai.getMessage()+"' "
						+ " , indexingConcepts='"+ai.getIndexingConcepts()+"' "
						+ " , indexingWords='"+ai.getIndexingWords()+"' " 
						+ ", clcNumbers='"+ai.getClcNumbers()+"' where title='"+ai.getTitle()+"' ";
				this.setPreparedStatement(update);
			}else{
				String insert  = "insert into [wordindex].[dbo].[autoIndexing] (id,title,abstract,result,message,indexingConcepts,indexingWords,clcNumbers)" +
						" values('"+ai.getId()+"','"+ai.getTitle()+"','"+ai.get_abstract()+"',"+ai.getResult()+",'"+ai.getMessage()+"'," +
								"'"+ai.getIndexingConcepts()+"','"+ai.getIndexingWords()+"','"+ai.getClcNumbers()+"')";
				this.setPreparedStatement(insert);
			}
			count = this.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
