package com.bloom.dao.ext;

import com.bloom.dao.GardenerMapper;

public interface GardenerExtDao extends GardenerMapper{
	
	Integer selectKeyByUsername(String username);

}
