package com.dl.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dl.dao.EnterpriseInfoDao;
import com.dl.entity.indexing.EnterpriseInfoEntity;
import com.dl.utils.Const;
import com.dl.utils.db.JdbcUtil;

public class EnterpriseInfoDaoImpl extends BaseDaoImpl<EnterpriseInfoEntity, Serializable> implements EnterpriseInfoDao {
	private final String DB_NAME = Const.applicationConst.getProperty("DB_NAME");
	// 在业务层处理完成后自行关闭数据连接
	private JdbcUtil jdbcutil ;
	public EnterpriseInfoDaoImpl(JdbcUtil jdbcutil) {
		this.jdbcutil = jdbcutil;
	}
	
	@Override
	public List<EnterpriseInfoEntity> findAll() throws Exception {
		List<EnterpriseInfoEntity> list = new ArrayList<EnterpriseInfoEntity>();
		String sql = "select * from  ["+DB_NAME+"].[dbo].[需要标引的企业基本信息] ";
		PreparedStatement statm = jdbcutil.getPreparedStatement(sql);
		ResultSet rs = statm.getResultSet();
		while (rs.next()) {
			EnterpriseInfoEntity e = new EnterpriseInfoEntity();
			e.setId(rs.getString(1));
			e.setStockCode(rs.getString(2));
			e.setR_DProjectKeypoint(rs.getString(3));
			e.setR_DProjectKeypointIC(rs.getString(4));
			e.setR_DProjectKeypointIW(rs.getString(5));
			e.setCoreTechnology(rs.getString(6));
			e.setCoreTechnologyIC(rs.getString(7));
			e.setCoreTechnologyIW(rs.getString(8));
			e.setCoreCompetitive(rs.getString(9));
			e.setCoreCompetitiveIC(rs.getString(10));
			e.setCoreCompetitiveIW(rs.getString(11));
			e.setCoreProduct(rs.getString(12));
			e.setCoreProductIC(rs.getString(13));
			e.setCoreProductIW(rs.getString(14));
			e.setBusinessScope(rs.getString(15));
			e.setBusinessScopeIC(rs.getString(16));
			e.setBusinessScopeIW(rs.getString(17));
			list.add(e);
		}
		jdbcutil.close(statm, rs);
		return list;
	}

	@Override
	public int supdate(EnterpriseInfoEntity ai) throws SQLException {
		
		String update="update ["+DB_NAME+"].[dbo].[需要标引的企业基本信息] " +
						"set " +
							"[研发项目重点受控词]='"+ai.getR_DProjectKeypointIC()+"', " +
							"[研发项目重点非受控词]='"+ai.getR_DProjectKeypointIW()+"', " +
							"[关键技术受控词]='"+ai.getCoreTechnologyIC()+"', " +
							"[关键技术非受控词]='"+ai.getCoreTechnologyIW()+"', " +
							"[核心竞争力受控词]='"+ai.getCoreCompetitiveIC()+"', " +
							"[核心竞争力非受控词]='"+ai.getCoreCompetitiveIW()+"', " +
							"[主要产品受控词]='"+ai.getCoreProductIC()+"', " +
							"[主要产品非受控词]='"+ai.getCoreProductIW()+"', " +
							"[经营范围受控词]='"+ai.getBusinessScopeIC()+"', " +
							"[经营范围非受控词]='"+ai.getBusinessScopeIW()+"'" +
							
						"where [记录id]='"+ai.getId()+"' and [股票代码]='"+ai.getStockCode()+"'";
		
		String insert="insert into ["+DB_NAME+"]" +".[dbo].[需要标引的企业基本信息] " +
						"([记录ID]"+
				           ",[股票代码]"+
				           ",[研发项目重点]"+
				           ",[研发项目重点受控词]"+
				           ",[研发项目重点非受控词]"+
				           ",[关键技术]"+
				           ",[关键技术受控词]"+
				           ",[关键技术非受控词]"+
				           ",[核心竞争力]"+
				           ",[核心竞争力受控词]"+
				           ",[核心竞争力非受控词]"+
				           ",[主要产品]"+
				           ",[主要产品受控词]"+
				           ",[主要产品非受控词]"+
				           ",[经营范围]"+
				           ",[经营范围受控词]"+
				           ",[经营范围非受控词])" +
						" values('"+ai.getId()+"','"+ai.getStockCode()+"','"+ai.getR_DProjectKeypoint()+"',"+ai.getR_DProjectKeypointIC()+",'"+ai.getR_DProjectKeypointIW()+"'," +
								"'"+ai.getCoreTechnology()+"','"+ai.getCoreTechnologyIC()+"','"+ai.getCoreTechnologyIW()+"'," +
								"'"+ai.getCoreCompetitive()+"','"+ai.getCoreCompetitiveIC()+"','"+ai.getCoreCompetitiveIW()+"'," +
								"'"+ai.getCoreProduct()+"','"+ai.getCoreProductIC()+"','"+ai.getCoreProductIW()+"'," +
								"'"+ai.getBusinessScope()+"','"+ai.getBusinessScopeIC()+"','"+ai.getBusinessScopeIW()+"')";
		// 验证是否存在改记录，存在则更新
		String valid = "select count(*) as count from ["+DB_NAME+"]" +".[dbo].[需要标引的企业基本信息] where [记录id]='"+ai.getId()+"'";
		int count = 0;
		PreparedStatement statm = jdbcutil.getPreparedStatement(valid);
		ResultSet rs = statm.getResultSet();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		if(count>0){
			statm = jdbcutil.getPreparedStatement(update);
		}else{
			statm = jdbcutil.getPreparedStatement(insert);
		}
		count = statm.executeUpdate();
		jdbcutil.close(statm, rs);
		return count;
	}
}
