package com.bloom.domain.gardener;

import com.bloom.domain.gardener.impl.SignServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>Title: GardenerTestSuite.java<／p>
 * <p>Description: Gardener 测试组件<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @version 1.0
 * @date 2019/1/27
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		SignServiceImplTest.class
})
public class GardenerTestSuite {
}