package com.dl.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dl.dao.MultiIndexingDao;
import com.dl.entity.indexing.MultiIndexingEntity;
import com.dl.utils.Const;
import com.dl.utils.db.JdbcUtil;

public class MultiIndexingDaoImpl extends BaseDaoImpl<MultiIndexingEntity, Serializable> implements MultiIndexingDao {
	
	private final String DB_NAME = Const.applicationConst.getProperty("DB_NAME");
	private JdbcUtil jdbcutil;
	public MultiIndexingDaoImpl(JdbcUtil jdbcutil) {
		this.jdbcutil = jdbcutil;
	}

	
	@Override
	public List<MultiIndexingEntity> findAllByMenu(Integer... i) throws SQLException {
		List<MultiIndexingEntity> list = new ArrayList<MultiIndexingEntity>();
		String sql = "";
		switch (i[0]) {
		case 1:
			sql = "select * from  ["+DB_NAME+"].[dbo].[需要标引的联营企业]";
			list = getListData(sql,false);
			break;
		case 2:
			sql = "select * from  ["+DB_NAME+"].[dbo].[需要标引的母公司]";
			list = getListData(sql,false);
			break;
		case 4:
			sql = "select * from  ["+DB_NAME+"].[dbo].[需要标引的重点项目]";
			list = getListData(sql,true);
			break;
		case 5:
			sql = "select * from  ["+DB_NAME+"].[dbo].[需要标引的子公司]";
			list = getListData(sql,false);
			break;
		default:
			break;
		};
		return list;
	}
	
	private List<MultiIndexingEntity> getListData(String sql,Boolean isMenu4) throws SQLException{
		List<MultiIndexingEntity> list = new ArrayList<MultiIndexingEntity>();
		PreparedStatement statm = jdbcutil.getPreparedStatement(sql);
		ResultSet rs  = statm.getResultSet();
		while (rs.next()) {
			MultiIndexingEntity e = new MultiIndexingEntity();
			e.setId(rs.getString(1));
			e.setName(rs.getString(2));
			if(isMenu4){
				e.setIndexingConcepts(rs.getString(3));
				e.setIndexingWords(rs.getString(4));
			}else{
				e.setBusiness(rs.getString(3));
				e.setIndexingConcepts(rs.getString(4));
				e.setIndexingWords(rs.getString(5));
			}
			list.add(e);
		}
		jdbcutil.close(statm, rs);
		return list;
	}


	@Override
	public int supdate(MultiIndexingEntity mie,int menu) throws SQLException {
		String TABLE_NAME = "";
		switch (menu) {
		case 1:
			TABLE_NAME = "[需要标引的联营企业]";
			break;
		case 2:
			TABLE_NAME = "[需要标引的母公司]";
			break;
		case 4:
			TABLE_NAME = "[需要标引的重点项目]";
			break;
		case 5:
			TABLE_NAME = "[需要标引的子公司]";
			break;
			
		default:
			break;
		}
		
		String update="update ["+DB_NAME+"].[dbo]."+TABLE_NAME+" " +
						"set " +
							"[受控词]='"+mie.getIndexingConcepts()+"', " +
							"[非受控词]='"+mie.getIndexingWords()+"' " +
						"where [记录id]='"+mie.getId()+"' and [企业名称]='"+mie.getName()+"'";
		String insert="insert into ["+DB_NAME+"]" +".[dbo]."+TABLE_NAME+" " +
						"([记录id],[企业名称],[主要业务] ,[受控词],[非受控词])" +
						" values('"+mie.getId()+"','"+mie.getName()+"','"+mie.getBusiness()+"',"+mie.getIndexingConcepts()+",'"+mie.getIndexingWords()+"')";
		// 验证是否存在改记录，存在则更新
		String valid = "select count(*) as count from ["+DB_NAME+"]" +".[dbo]."+TABLE_NAME+" where [记录id]='"+mie.getId()+"'";
		int count = 0;
		PreparedStatement statm = jdbcutil.getPreparedStatement(valid);
		ResultSet rs  = statm.getResultSet();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		if(count>0){
			if(4==menu){
				update="update ["+DB_NAME+"].[dbo]."+TABLE_NAME+" set " +
						"[受控词]='"+mie.getIndexingConcepts()+"', " +
						"[非受控词]='"+mie.getIndexingWords()+"' " +
						"where [记录id]='"+mie.getId()+"' and [名称]='"+mie.getName()+"'";
			}
			statm = jdbcutil.getPreparedStatement(update);
		}else{
			if(4==menu){
				insert="insert into ["+DB_NAME+"]" +".[dbo]."+TABLE_NAME+" " +
						"([记录id],[名称] ,[受控词],[非受控词])" +
						" values('"+mie.getId()+"','"+mie.getName()+"',"+mie.getIndexingConcepts()+",'"+mie.getIndexingWords()+"')";
			}
			statm = jdbcutil.getPreparedStatement(insert);
		}
		count = statm.executeUpdate();
		jdbcutil.close(statm, rs);
		return count;
	}

}
