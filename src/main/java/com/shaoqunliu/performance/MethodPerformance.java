package com.shaoqunliu.performance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class MethodPerformance {
    private Object object;
    private Class<?> clazz;

    public MethodPerformance(Object object) {
        Objects.requireNonNull(object, "[ERROR] Can not construct MethodPerformance due to the reflected object is null");
        this.object = object;
        this.clazz = object.getClass();
    }

    public long performsMethod(String methodName, int iteration, Object... objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = clazz.getMethod(methodName);
        long before = System.nanoTime();
        while (iteration-- != 0) {
            method.invoke(object, objects);
        }
        return System.nanoTime() - before;
    }

    public long performsMethod(String methodName, int iteration, List<Object> returnedValue, Object... objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.err.println("[WARN] Recording returned value of methods will cause extra time to consume, especially the precision of timer is nano level!");
        Method method = clazz.getMethod(methodName);
        long before = System.nanoTime();
        while (iteration-- != 0) {
            returnedValue.add(method.invoke(object, objects));
        }
        return System.nanoTime() - before;
    }
}
