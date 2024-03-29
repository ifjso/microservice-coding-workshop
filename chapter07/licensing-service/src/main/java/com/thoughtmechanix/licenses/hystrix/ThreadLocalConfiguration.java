package com.thoughtmechanix.licenses.hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadLocalConfiguration {

  private HystrixConcurrencyStrategy hystrixConcurrencyStrategy;

  public ThreadLocalConfiguration(
      ObjectProvider<HystrixConcurrencyStrategy> hystrixConcurrencyStrategies) {
    this.hystrixConcurrencyStrategy = hystrixConcurrencyStrategies.getIfAvailable();
  }

  @PostConstruct
  public void init() {
    HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
    HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
    HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance()
        .getPropertiesStrategy();
    HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance()
        .getCommandExecutionHook();

    HystrixPlugins.reset();

    HystrixPlugins.getInstance()
        .registerConcurrencyStrategy(new ThreadLocalAwareStrategy(hystrixConcurrencyStrategy));
    HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
    HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
    HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
    HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
  }
}
