package com.dl.dao;

import java.io.Serializable;
import java.sql.SQLException;

import com.dl.entity.indexing.EnterpriseInfoEntity;

public interface EnterpriseInfoDao extends BaseDao<EnterpriseInfoEntity, Serializable> {

	int supdate(EnterpriseInfoEntity ai) throws SQLException;

}
