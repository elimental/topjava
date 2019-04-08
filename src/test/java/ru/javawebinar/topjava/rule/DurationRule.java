package ru.javawebinar.topjava.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Map;

public class DurationRule implements TestRule {

    private Map<String, Long> testMethodDurationMap;

    public DurationRule(Map<String, Long> testMethodDurationMap) {
        this.testMethodDurationMap = testMethodDurationMap;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {

            private long time;

            @Override
            public void evaluate() throws Throwable {
                time = System.currentTimeMillis();
                base.evaluate();
                time = System.currentTimeMillis() - time;
                String testName = description.getMethodName();
                System.err.println(String.format("Test name: %s. Duration: %s ms", testName, time));
                testMethodDurationMap.put(testName, time);
            }
        };
    }
}
