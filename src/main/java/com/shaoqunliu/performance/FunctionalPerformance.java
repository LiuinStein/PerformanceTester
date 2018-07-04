package com.shaoqunliu.performance;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalPerformance<PARAM, RET> {
    private Function<PARAM, RET> function = null;
    private Consumer<PARAM> consumer = null;

    public FunctionalPerformance(Function<PARAM, RET> function) {
        Objects.requireNonNull(function, "[ERROR] Can not create FunctionalPerformance object with a null functor");
        this.function = function;
    }

    public FunctionalPerformance(Consumer<PARAM> consumer) {
        Objects.requireNonNull(consumer, "[ERROR] Can not create FunctionalPerformance object with a null functor");
        this.consumer = consumer;
    }

    public long performsFunction(PARAM param, int iteration) {
        Objects.requireNonNull(function, "[ERROR] The test functor can not be null! Maybe you create this object with a Consumer but invoke the performsFunction method.");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            function.apply(param);
        }
        return System.nanoTime() - before;
    }

    public long performsFunction(PARAM param, int iteration, List<RET> returnedValues) {
        System.err.println("[WARN] Recording returned value of functions will cause extra time to consume, especially the precision of timer is nano level!");
        Objects.requireNonNull(function, "[ERROR] The test functor can not be null! Maybe you create this object with a Consumer but invoke the performsFunction method.");
        Objects.requireNonNull(returnedValues, "[ERROR] The list to store returned values is null!");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            returnedValues.add(function.apply(param));
        }
        return System.nanoTime() - before;
    }

    public long performsConsumer(PARAM param, int iteration) {
        Objects.requireNonNull(consumer, "[ERROR] The test functor can not be null! Maybe you create this object with a Function but invoke the performsConsumer method.");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            consumer.accept(param);
        }
        return System.nanoTime() - before;
    }
}
