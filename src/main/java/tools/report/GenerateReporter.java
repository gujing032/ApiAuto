package tools.report;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.util.List;
import java.util.Map;
/**
 * Created by Chuckie on 2017/5/1.
 */
public class GenerateReporter implements IReporter{
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
        // TODO Auto-generated method stub
        try {
            // 初始化并取得Velocity引擎
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            ve.init();
            Template t = ve.getTemplate("test.vm");
            VelocityContext context = new VelocityContext();

            for (ISuite suite : suites) {
                Map<String, ISuiteResult> suiteResults = suite.getResults();
                for (ISuiteResult suiteResult : suiteResults.values()) {
                    ReporterData data = new ReporterData();
                    ITestContext testContext = suiteResult.getTestContext();
                    // 把数据填入上下文
                    context.put("overView", data.testContext(testContext));//测试结果汇总信息
                    //ITestNGMethod[] allTests = testContext.getAllTestMethods();//所有的测试方法
                    //Collection<ITestNGMethod> excludeTests = testContext.getExcludedMethods();//未执行的测试方法
                    IResultMap passedTests = testContext.getPassedTests();//测试通过的测试方法
                    IResultMap failedTests = testContext.getFailedTests();//测试失败的测试方法
                    IResultMap skippedTests = testContext.getSkippedTests();//测试跳过的测试方法

                    context.put("pass", data.testResults(passedTests, ITestResult.SUCCESS));
                    context.put("fail", data.testResults(failedTests, ITestResult.FAILURE));
                    context.put("skip", data.testResults(skippedTests, ITestResult.FAILURE));



                }
            }
            // 输出流
            //Writer writer = new BufferedWriter(new FileWriter("report.html"));
            OutputStream out=new FileOutputStream("report.html");
            Writer writer = new BufferedWriter(new OutputStreamWriter(out,"utf-8"));//解决乱码问题
            // 转换输出
            t.merge(context, writer);
            //System.out.println(writer.toString());
            writer.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
