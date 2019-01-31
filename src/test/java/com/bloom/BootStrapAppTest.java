package com.bloom;

import com.bloom.domain.gardener.GardenerTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>Title: BootStrapAppTest.java<／p>
 * <p>Description: 单元测试启动类<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @version 1.0
 * @date 2019/1/27
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		TestDemo.class,
		GardenerTestSuite.class
})
public class BootStrapAppTest {}