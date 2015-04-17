package org.haibin369.test;

import org.haibin369.rules.LoopRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.*;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.File;
import java.io.IOException;

public class RulesTest {
    @Rule
    public Timeout timeout = new Timeout(500);

    //创建TemporaryFolder Rule
    //可以在构造方法上加入路径参数来指定临时目录，否则使用系统临时目录
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testTempFolderRule() throws IOException {
        //在系统的临时目录下创建文件或者目录，当测试方法执行完毕自动删除
        tempFolder.newFile("test.txt");
        tempFolder.newFolder("test");
    }


    File tempFile;

    @Rule
    public ExternalResource extResource = new ExternalResource() {
        //每个测试执行之前都会调用该方法创建一个临时文件
        @Override
        protected void before() throws Throwable {
            tempFile = File.createTempFile("test", ".txt");
        }

        //每个测试执行之后都会调用该方法删除临时文件
        @Override
        protected void after() {
            tempFile.delete();
        }
    };

    @Test
    public void testExtResource() throws IOException {
        System.out.println(tempFile.getCanonicalPath());
    }


    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void testErrorCollector() {
        errorCollector.addError(new Exception("Test Fail 1"));
        errorCollector.addError(new Throwable("fff"));
    }

//    String result;
//
//    @Rule
//    public Verifier verifier = new Verifier() {
//        //当测试执行完之后会调用verify方法验证结果，抛出异常表明测试失败
//        @Override
//        protected void verify() throws Throwable {
//            if (!"Success".equals(result)) {
//                throw new Exception("Test Fail.");
//            }
//        }
//    };
//
//    @Test
//    public void testVerifier() {
//        result = "Fail";
//    }

//    @Rule
//    public TestWatcher testWatcher = new TestWatcher() {
//        @Override
//        protected void succeeded(Description description) {
//            System.out.println(description.getDisplayName() + " Succeed");
//        }
//
//        @Override
//        protected void failed(Throwable e, Description description) {
//            System.out.println(description.getDisplayName() + " Fail");
//        }
//
//        @Override
//        protected void skipped(AssumptionViolatedException e, Description description) {
//            System.out.println(description.getDisplayName() + " Skipped");
//        }
//
//        @Override
//        protected void starting(Description description) {
//            System.out.println(description.getDisplayName() + " Started");
//        }
//
//        @Override
//        protected void finished(Description description) {
//            System.out.println(description.getDisplayName() + " finished");
//        }
//    };
//
//    @Test
//    public void testTestWatcher() {
//        /*
//            测试执行后会有以下输出：
//            testTestWatcher(org.haibin369.test.RulesTest) Started
//            Test invoked
//            testTestWatcher(org.haibin369.test.RulesTest) Succeed
//            testTestWatcher(org.haibin369.test.RulesTest) finished
//         */
//        System.out.println("Test invoked");
//    }

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testTestName() {
        //打印出测试方法的名字testTestName
        System.out.println(testName.getMethodName());
    }

    @Rule
    public LoopRule loopRule = new LoopRule(3);

    @Test
    public void testLoopRule() {
        System.out.println("Test invoked!");
    }
}
