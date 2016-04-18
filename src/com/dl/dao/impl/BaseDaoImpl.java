package com.dl.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.dl.dao.BaseDao;


public class BaseDaoImpl<T,ID extends Serializable> implements BaseDao<T, ID>{

	@Override
	public List<T> findAll() throws Exception {
		
		return null;
	}

	@Override
	public List<T> findOne(String id) throws Exception {
		return null;
	}
}
