package com.shaoqunliu.performance;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalPerformance<PARAM, RET> {
    private Function<PARAM, RET> function = null;
    private Consumer<PARAM> consumer = null;

    public FunctionalPerformance(Function<PARAM, RET> function) {
        this.function = function;
    }

    public FunctionalPerformance(Consumer<PARAM> consumer) {
        this.consumer = consumer;
    }

    public long performsFunction(PARAM param, int iteration) {
        Objects.requireNonNull(function, "[ERROR] The test functor can not be null!");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            function.apply(param);
        }
        return System.nanoTime() - before;
    }

    public long performsFunction(PARAM param, int iteration, List<RET> returnedValues) {
        System.err.println("[WARN] Recording returned value of functions will cause extra time to consume, especially the precision of timer is nano level!");
        Objects.requireNonNull(function, "[ERROR] The test functor can not be null!");
        Objects.requireNonNull(returnedValues, "[ERROR] The list to store returned values is null!");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            returnedValues.add(function.apply(param));
        }
        return System.nanoTime() - before;
    }

    public long performsConsumer(PARAM param, int iteration) {
        Objects.requireNonNull(consumer, "[ERROR] The test functor can not be null!");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            consumer.accept(param);
        }
        return System.nanoTime() - before;
    }
}
