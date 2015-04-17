package org.haibin369.test;

import org.haibin369.common.ObjectGenerator;
import org.junit.Test;

//静态导入，方便使用Assert对象的断言方法
import static org.junit.Assert.*;

/**
 * 测试类，不需继承任何JUnit的类
 */
public class ObjectGeneratorTest {
    //使用@Test标注测试方法
    @Test
    public void testGetString() {
        ObjectGenerator generator = new ObjectGenerator();
        String msg = generator.getString();
        if (msg == null) {
            //Assert中也有使测试失败的fail方法，参数为失败信息（此处仅作演示）
            fail("Message is null");
        }

        //断言得到的msg为AString，否则测试失败，第一个参数为失败时的信息
        assertEquals("Wrong message generated.", "AString", msg);
    }

    @Test
    public void testGetNull() {
        ObjectGenerator generator = new ObjectGenerator();
        //断言为空
        assertNull("Returned object is not null", generator.getNull());
    }
}
