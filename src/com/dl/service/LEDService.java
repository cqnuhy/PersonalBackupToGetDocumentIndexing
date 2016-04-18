package com.dl.service;

import java.util.List;

import com.dl.entity.indexing.LEDEntity;

public interface LEDService {

	public List<LEDEntity> findAll(String queryType);

	public int saveOrupate(List<LEDEntity> list,String queryType);
	public int saveOrupate(LEDEntity led,String queryType);
	
}
