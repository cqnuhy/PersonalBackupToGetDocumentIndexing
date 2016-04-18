package com.dl.dao;

import java.io.Serializable;

import com.dl.entity.indexing.EnterpriseInfoEntity;

public interface EnterpriseInfoDao extends BaseDao<EnterpriseInfoEntity, Serializable> {

	int supdate(EnterpriseInfoEntity ai);

}
