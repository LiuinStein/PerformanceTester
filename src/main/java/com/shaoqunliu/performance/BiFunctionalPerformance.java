package com.shaoqunliu.performance;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class BiFunctionalPerformance<PARAM1, PARAM2, RET> {
    private BiFunction<PARAM1, PARAM2, RET> function = null;
    private BiConsumer<PARAM1, PARAM2> consumer = null;

    public BiFunctionalPerformance(BiFunction<PARAM1, PARAM2, RET> function) {
        Objects.requireNonNull(function, "[ERROR] Can not create BiFunctionalPerformance with a null functor");
        this.function = function;
    }

    public BiFunctionalPerformance(BiConsumer<PARAM1, PARAM2> consumer) {
        Objects.requireNonNull(consumer, "[ERROR] Can not create BiFunctionalPerformance with a null functor");
        this.consumer = consumer;
    }

    public long performsBiFunction(PARAM1 param1, PARAM2 param2, int iteration) {
        Objects.requireNonNull(function, "[ERROR] The test functor can not be null! Maybe you create this object with a BiConsumer but invoke the performsBiFunction method.");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            function.apply(param1, param2);
        }
        return System.nanoTime() - before;
    }

    public long performsBiFunction(PARAM1 param1, PARAM2 param2, int iteration, List<RET> returnedValues) {
        System.err.println("[WARN] Recording returned value of functions will cause extra time to consume, especially the precision of timer is nano level!");
        Objects.requireNonNull(function, "[ERROR] The test functor can not be null! Maybe you create this object with a BiConsumer but invoke the performsBiFunction method.");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            returnedValues.add(function.apply(param1, param2));
        }
        return System.nanoTime() - before;
    }

    public long performsBiConsumer(PARAM1 param1, PARAM2 param2, int iteration) {
        Objects.requireNonNull(consumer, "[ERROR] The test functor can not be null! Maybe you create this object with a BiFunction but invoke the performsBiConsumer method.");
        long before = System.nanoTime();
        while (iteration-- != 0) {
            consumer.accept(param1, param2);
        }
        return System.nanoTime() - before;
    }
}
