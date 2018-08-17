package tools.report;
import org.testng.ITestResult;
/**
 * Created by Chuckie on 2017/5/1.
 */
public class TestResultSort implements Comparable<ITestResult> {
    private Long order;
    @Override
    public int compareTo(ITestResult arg0) {
        // TODO Auto-generated method stub
        return this.order.compareTo( arg0.getStartMillis());//按test开始时间排序
    }
}
