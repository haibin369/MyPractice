package org.haibin369.test;

import org.haibin369.common.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

////使用Parameterized Runner执行参数化测试
//@RunWith(Parameterized.class)
//public class CalculatorTest {
//    //允许误差
//    private static final double DELTA = 0.01;
//
//    private int dividend;
//    private int divisor;
//    private int result;
//
//    public CalculatorTest(int dividend, int divisor, int result) {
//        this.dividend = dividend;
//        this.divisor = divisor;
//        this.result = result;
//    }
//
//    // 用@Parameterized.Parameters注解标注该方法是返回所有参数，被注解的方法必须返
//    // 回装载数组的Iterable对象，同时必须为public，static，当测试执行时，系统会遍历
//    // 每组参数（数组）调用构造函数并执行测试。
//    @Parameterized.Parameters
//    public static Iterable<Object[]> getParameters() {
//        return Arrays.asList(new Object[][]{
//                {9, 3, 3}, {5, 1, 5}, {12, 4, 3}
//        });
//    }
//
//    //当执行测试后，该方法会运行3次
//    @Test
//    public void testAdd() throws Exception {
//        assertEquals(result, Calculator.divide(dividend, divisor), DELTA);
//    }
//}


//使用Parameterized Runner执行参数化测试
@RunWith(Parameterized.class)
public class CalculatorTest {
    //允许误差
    private static final double DELTA = 0.01;

    //使用@Parameter标注public变量，JUnit会遍历每组参数进行注入
    //注解中的整数参数表示注入参数组中的第几个参数
    @Parameter(0)
    public int dividend;
    @Parameter(1)
    public int divisor;
    @Parameter(2)
    public int result;

    // 用@Parameterized.Parameters注解标注该方法是返回所有参数，被注解的方法必须返
    // 回装载数组的Iterable对象，同时必须为public，static，当测试执行时，系统会遍历
    // 每组参数（数组）调用构造函数并执行测试。
    @Parameterized.Parameters(name = "{index}: {0} / {1} = {2}")
    public static Iterable<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {9, 3, 3}, {15, 0, 0}, {5, 1, 5}, {12, 4, 3},
        });
    }

    //当执行测试后，该方法会运行3次
    @Test
    public void testDivide() throws Exception {
        //假定除数不为0，若为0跳过该测试
        assumeTrue("Divisor can't be 0", divisor != 0);
        assertEquals(result, Calculator.divide(dividend, divisor), DELTA);
    }
}