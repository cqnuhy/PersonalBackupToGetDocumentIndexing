package com.dl.dao;

import java.io.Serializable;
import java.util.List;

import com.dl.entity.indexing.LEDEntity;

public interface LEDDao extends BaseDao<LEDEntity, Serializable>{

	public List<LEDEntity> findAll(String queryType);

	public int saveALL(List<LEDEntity> list,String type);

	public int save(LEDEntity led,String type);
}
