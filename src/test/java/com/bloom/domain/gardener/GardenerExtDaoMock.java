/**
 * 
 */
package com.bloom.domain.gardener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import com.bloom.dao.ext.GardenerExtDao;
import com.bloom.dao.po.Gardener;
import com.bloom.dao.po.GardenerExample;

/**
 * <p>Title: GardenerExtDaoMock.java<／p>
 * <p>Description: GardenerExtDao模拟对象<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2018年12月25日
 * @version 1.0
*/
public class GardenerExtDaoMock implements GardenerExtDao{
	/**
	 * gardener列表
	 */
	private List<Gardener> gardeners = new ArrayList<Gardener>();
	/**
	 * 模拟自增主键
	 */
	private AtomicLong keyGenerator = new AtomicLong();
	/* (non-Javadoc)
	 * @see com.bloom.dao.GardenerMapper#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		Iterator<Gardener> iterator = gardeners.iterator();
		while (iterator.hasNext()) {
			Gardener gardener = iterator.next();
			if (Objects.equals(id, gardener.getId())) {
				iterator.remove();
				return 1;
			}
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.bloom.dao.GardenerMapper#insert(com.bloom.dao.po.Gardener)
	 */
	@Override
	public int insert(Gardener gardener) {
		long key = keyGenerator.incrementAndGet();
		gardener.setId((int)key);
		gardeners.add(gardener);
		return gardener.getId();
	}


	/* (non-Javadoc)
	 * @see com.bloom.dao.GardenerMapper#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public Gardener selectByPrimaryKey(Integer id) {
		Iterator<Gardener> iterator = gardeners.iterator();
		while (iterator.hasNext()) {
			Gardener gardener = iterator.next();
			if (Objects.equals(id, gardener.getId())) {
				Gardener copyGardener = new Gardener();
				copyGardener.setId(gardener.getId());
				copyGardener.setUsername(gardener.getUsername());
				copyGardener.setPassword(gardener.getPassword());
				copyGardener.setNickName(gardener.getNickName());
				copyGardener.setRoleId(gardener.getRoleId());
				copyGardener.setGender(gardener.getGender());
				copyGardener.setEmail(gardener.getEmail());
				copyGardener.setCt(gardener.getCt());
				copyGardener.setUt(new Date());
				return copyGardener;
			}
		}
		return null;
	}


	/* (non-Javadoc)
	 * @see com.bloom.dao.GardenerMapper#updateByPrimaryKey(com.bloom.dao.po.Gardener)
	 */
	@Override
	public int updateByPrimaryKey(Gardener record) {
		Iterator<Gardener> iterator = gardeners.iterator();
		while (iterator.hasNext()) {
			Gardener gardener = iterator.next();
			if (Objects.equals(record.getId(), gardener.getId())) {
				gardener.setUsername(record.getUsername());
				gardener.setPassword(record.getPassword());
				gardener.setNickName(record.getNickName());
				gardener.setRoleId(record.getRoleId());
				gardener.setGender(record.getGender());
				gardener.setEmail(record.getEmail());
				gardener.setUt(new Date());
				return 1;
			}
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.bloom.dao.ext.GardenerExtDao#selectKeyByUsername(java.lang.String)
	 */
	@Override
	public Integer selectKeyByUsername(String username) {
		Iterator<Gardener> iterator = gardeners.iterator();
		while (iterator.hasNext()) {
			Gardener gardener = iterator.next();
			if (Objects.equals(username, gardener.getUsername())) {
				return gardener.getId();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bloom.dao.GardenerMapper#insertSelective(com.bloom.dao.po.Gardener)
	 */
	@Override
	@Deprecated
	public int insertSelective(Gardener record) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.bloom.dao.GardenerMapper#selectByExample(com.bloom.dao.po.GardenerExample)
	 */
	@Override
	@Deprecated
	public List<Gardener> selectByExample(GardenerExample example) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bloom.dao.GardenerMapper#updateByPrimaryKeySelective(com.bloom.dao.po.Gardener)
	 */
	@Override
	@Deprecated
	public int updateByPrimaryKeySelective(Gardener record) {
		return 0;
	}

}
