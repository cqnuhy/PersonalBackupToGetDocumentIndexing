package com.dl.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dl.dao.LEDDao;
import com.dl.entity.indexing.LEDEntity;
import com.dl.utils.Const;
import com.dl.utils.db.JdbcUtil;

public class LEDDaoImpl extends BaseDaoImpl<LEDEntity, Serializable> implements LEDDao {

	private final String DB_NAME = Const.applicationConst.getProperty("DB_NAME");
	private JdbcUtil jdbcutil;
	public LEDDaoImpl(JdbcUtil jdbcutil) {
		this.jdbcutil = jdbcutil;
	}

	@Override
	public List<LEDEntity> findAll(String queryType) throws SQLException{
		boolean f =  queryType.equals("SSLED");
		String TABLE_NAME =f?"深市LED$":"沪市LED$";
		List<LEDEntity> list = new ArrayList<LEDEntity>();
		String sql = "select * from  ["+DB_NAME+"].[dbo].["+TABLE_NAME+"]";
		PreparedStatement statm = jdbcutil.getPreparedStatement(sql);
		ResultSet rs = statm.getResultSet();
		while (rs.next()) {
			LEDEntity e = new LEDEntity();
			e.setPatentNumber(rs.getString(1));
			e.setApplyingNumber(rs.getString(2));
			e.setLicense(rs.getString(3));
			if(f){
				e.setInventionName(rs.getString(4));
				e.set_abstract(rs.getString(5));
				e.setApplicant(rs.getString(6));
			}else{
				e.setPatentType(rs.getString(4));
				e.setPatentName(rs.getString(5));
				e.set_abstract(rs.getString(6));
				e.setApplicant(rs.getString(7));
			}
			list.add(e);
		}
		jdbcutil.close(statm, rs);
		return list;
	}

	@Override
	public int saveALL(List<LEDEntity> list,String saveType) {
		boolean f =  saveType.equals("SSLED");
		String TABLE_NAME =f?"深市LED$":"沪市LED$";
		int totalCount = 0;
		PreparedStatement statm = null;
		ResultSet rs = null;
		for (LEDEntity led : list) {
			// 验证是否存在改记录，存在则更新
			String valid = "select count(*) as count from  ["+DB_NAME+"].[dbo].["+TABLE_NAME+"] " +
					"where [专利号]='"+led.getPatentNumber()+"'  and [申请号]='"+led.getApplyingNumber()+"'";
			int count = 0;
			String update = "update ["+DB_NAME+"].[dbo].["+TABLE_NAME+"] set "
							+ " [受控词]='"+led.getIndexingConcepts()+"' , [非受控词]='"+led.getIndexingWords()+"'" +
							" where [专利号]='"+led.getPatentNumber()+"' and [申请号]='"+led.getApplyingNumber()+"'";
			try {
				statm = jdbcutil.getPreparedStatement(valid);
				rs = statm.getResultSet();
				while (rs.next()) {
					count = rs.getInt("count");
				}
				if(count>0){
					statm = jdbcutil.getPreparedStatement(update);
				}else{
					String insert = "";
					if(f){
						insert  = "insert into [wordindex].[dbo].[autoIndexing] ([专利号],[申请号],[授权号],[发明名称],[摘要],[申请人])" +
								" values('"+led.getPatentNumber()+"','"+led.getApplyingNumber()+"','"+led.getLicense()+"',"+led.getInventionName()+",'"+led.get_abstract()+"','"+led.getApplicant()+"')";
					}else{
						insert  = "insert into [wordindex].[dbo].[autoIndexing] ([专利号],[申请号],[授权号],[专利类型],[专利名称],[摘要],[申请人])" +
								" values('"+led.getPatentNumber()+"','"+led.getApplyingNumber()+"','"+led.getLicense()+"',"+led.getPatentType()+",'"+led.getPatentName()+"','"+led.get_abstract()+"','"+led.getApplicant()+"')";
					}
					statm = jdbcutil.getPreparedStatement(insert);
				}
				count = statm.executeUpdate();
			} catch (SQLException e) {
				System.out.println("出现错误的sql:"+update);
				e.printStackTrace();
			}
			totalCount += count;
		}
		jdbcutil.close(statm, rs);
		return totalCount;
	}

	@Override
	public int save(LEDEntity led, String saveType) {
		boolean f =  saveType.equals("SSLED");
		String TABLE_NAME =f?"深市LED$":"沪市LED$";
		int totalCount = 0;
		// 验证是否存在改记录，存在则更新
		String valid = "select count(*) as count from  ["+DB_NAME+"].[dbo].["+TABLE_NAME+"] " +
				"where [专利号]='"+led.getPatentNumber()+"'  and [申请号]='"+led.getApplyingNumber()+"'";
		int count = 0;
		String update = "update ["+DB_NAME+"].[dbo].["+TABLE_NAME+"] set "
						+ " [受控词]='"+led.getIndexingConcepts()+"' , [非受控词]='"+led.getIndexingWords()+"'" +
						" where [专利号]='"+led.getPatentNumber()+"' and [申请号]='"+led.getApplyingNumber()+"'";
		PreparedStatement statm = null;
		ResultSet rs = null;
		try {
			statm = jdbcutil.getPreparedStatement(valid);
			rs = statm.getResultSet();
			while (rs.next()) {
				count = rs.getInt("count");
			}
			if(count>0){
				statm = jdbcutil.getPreparedStatement(update);
			}else{
				String insert = "";
				if(f){
					insert  = "insert into ["+DB_NAME+"].[dbo].["+TABLE_NAME+"]  ([专利号],[申请号],[授权号],[发明名称],[摘要],[申请人])" +
							" values('"+led.getPatentNumber()+"','"+led.getApplyingNumber()+"','"+led.getLicense()+"',"+led.getInventionName()+",'"+led.get_abstract()+"','"+led.getApplicant()+"')";
				}else{
					insert  = "insert into ["+DB_NAME+"].[dbo].["+TABLE_NAME+"] ([专利号],[申请号],[授权号],[专利类型],[专利名称],[摘要],[申请人])" +
							" values('"+led.getPatentNumber()+"','"+led.getApplyingNumber()+"','"+led.getLicense()+"',"+led.getPatentType()+",'"+led.getPatentName()+"','"+led.get_abstract()+"','"+led.getApplicant()+"')";
				}
				statm = jdbcutil.getPreparedStatement(insert);
			}
			count = statm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("出现错误的sql:"+update);
			e.printStackTrace();
		}
		totalCount += count;
		jdbcutil.close(statm, rs);
		return totalCount;
	}

}
