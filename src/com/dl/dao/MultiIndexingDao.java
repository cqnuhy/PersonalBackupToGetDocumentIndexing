package com.dl.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.dl.entity.indexing.MultiIndexingEntity;

public interface MultiIndexingDao extends BaseDao<MultiIndexingEntity, Serializable>{

	public List<MultiIndexingEntity> findAllByMenu(Integer... i) throws Exception;

	public int supdate(MultiIndexingEntity mie,int menu)  throws SQLException;
}
