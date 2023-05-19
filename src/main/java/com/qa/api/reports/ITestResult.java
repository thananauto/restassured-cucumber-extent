package com.qa.api.reports;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;

public class ITestResult implements EventListener {
    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunFinished.class, this::testRunFinished);
        eventPublisher.registerHandlerFor(TestRunStarted.class, this::testRunStarted);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::testCaseFinished);
    }

    private void testRunFinished(TestRunFinished event){

    }
    private void testRunStarted(TestRunStarted event){

    }
    private void testCaseFinished(TestCaseFinished event){

    }
}
