package com.example.metrics;

import java.lang.reflect.Constructor;

/**
 * Attempts to create multiple instances via reflection.
 * Starter allows this. After fix, it must fail.
 */
public class ReflectionAttack {

    public static void main(String[] args) throws Exception {
        MetricsRegistry singleton = MetricsRegistry.getInstance();

        Constructor<MetricsRegistry> ctor = MetricsRegistry.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        System.out.println("Singleton identity: " + System.identityHashCode(singleton));

        try {
            MetricsRegistry evil = ctor.newInstance();
            System.out.println("Evil identity     : " + System.identityHashCode(evil));
            System.out.println("Same object?      : " + (singleton == evil));
        } catch (Exception e) {
            System.out.println("Reflection blocked: " + e.getCause().getMessage());
        }
    }
}
