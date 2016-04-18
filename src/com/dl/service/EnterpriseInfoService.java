package com.dl.service;

import java.util.List;

import com.dl.entity.indexing.EnterpriseInfoEntity;

public interface EnterpriseInfoService {

	public int saveOrUpate(EnterpriseInfoEntity ai);

	public List<EnterpriseInfoEntity> findAll();

}
