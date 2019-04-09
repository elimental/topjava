package ru.javawebinar.topjava.rule;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.DateTimeUtil.nanosToMilliseconds;

public class TestStopWatch extends Stopwatch {

    private static final Logger log = getLogger(TestStopWatch.class);

    private Map<String, Long> testMethodDuration;

    public TestStopWatch(Map<String, Long> testMethodDuration) {
        this.testMethodDuration = testMethodDuration;
    }

    @Override
    protected void finished(long nanos, Description description) {
        String testMethodName = description.getMethodName();
        String testClass = description.getTestClass().getName();
        long testDurationTime = nanosToMilliseconds(nanos);
        log.info("Test Class: {}. Test method name: {}. Duration {}.", testClass, testMethodName, testDurationTime);
        testMethodDuration.put(testMethodName, testDurationTime);
    }


}
