package com.shaoqunliu.performance;


public class FunctionWithinClassPerformance {
    private Object object;
    private Class<?> clazz;


    public FunctionWithinClassPerformance(Object object) {
        this.object = object;
        this.clazz = object.getClass();
    }




}
