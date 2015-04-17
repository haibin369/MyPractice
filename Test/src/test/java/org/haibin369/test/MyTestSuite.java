package org.haibin369.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//使用JUnit的Suite Runner执行测试
@RunWith(Suite.class)
//配置所有需要执行的测试
@Suite.SuiteClasses({
        ObjectGeneratorTest.class,
        LoginActionTest.class
})

//创建一个类作为Test Suite的入口
public class MyTestSuite {
}
