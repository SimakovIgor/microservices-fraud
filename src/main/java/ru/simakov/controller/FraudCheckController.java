package ru.simakov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.simakov.commons.model.internal.fraud.FraudCheckResponse;
import ru.simakov.service.FraudCheckService;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class FraudCheckController {
    public static final String HELLO_WORLD = "Hello World!";
    private final FraudCheckService fraudCheckService;

    @GetMapping("/api/v1/fraud-check")
    public FraudCheckResponse checkForFraud(final @RequestParam("customerId") Long customerId) {
        return fraudCheckService.checkFraud(customerId);
    }

    @GetMapping("/health")
    public String healthCheck() {
        final LocalDateTime now = LocalDateTime.now();
        log.info("Healthy {}", now);
        return "Healthy: " + now;
    }

    @GetMapping("/hello")
    public String hello() {
        return HELLO_WORLD;
    }

    @GetMapping("/simulate")
    public String simulateLoad(@RequestParam(name = "cpuTime") final long cpuTime,
                               @RequestParam(name = "usleepTime") final long usleepTime) throws InterruptedException {
        final long startTime = System.nanoTime();

        simulateCpuLoad(cpuTime);

        final long endTime = System.nanoTime();
        final long actualCpuTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        Thread.sleep(usleepTime);

        return String.format("Simulated CPU load: %d ms, Sleep time: %d ms", actualCpuTime, usleepTime);
    }

    @SuppressWarnings({"EmptyBlock", "PMD.EmptyControlStatement"})
    private void simulateCpuLoad(final long cpuTime) {
        final long endTime = System.nanoTime() + TimeUnit.SECONDS.toNanos(cpuTime);
        while (System.nanoTime() < endTime) {

        }
    }

}
