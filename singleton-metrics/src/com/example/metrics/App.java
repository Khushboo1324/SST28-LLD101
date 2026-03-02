package com.example.metrics;

import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * PulseMeter CLI entry point.
 *
 * Starter behavior prints instance identity and increments a counter.
 * After you fix the Singleton, the identity should be stable across the app.
 */
public class App {

    public static void main(String[] args) throws Exception {
        String propsPath = resolvePropsPath("metrics.properties");

        MetricsLoader loader = new MetricsLoader();
        MetricsRegistry loaded = loader.loadFromFile(propsPath);

        // In a correct design, loader should populate the SAME singleton instance.
        MetricsRegistry global = MetricsRegistry.getInstance();

        System.out.println("Loaded registry instance  : " + System.identityHashCode(loaded));
        System.out.println("Global registry instance  : " + System.identityHashCode(global));

        global.increment("REQUESTS_TOTAL");
        System.out.println("\nREQUESTS_TOTAL = " + global.getCount("REQUESTS_TOTAL"));

        System.out.println("\nAll counters:");
        for (Map.Entry<String, Long> e : global.getAll().entrySet()) {
            System.out.println("  " + e.getKey() + " = " + e.getValue());
        }

        System.out.println("\nTIP: Run ConcurrencyCheck / ReflectionAttack / SerializationCheck for validations.");
    }

    private static String resolvePropsPath(String fileName) {
        String[] candidates = new String[] {
                fileName,
                "singleton-metrics/src/" + fileName,
                "SST28-LLD101/singleton-metrics/src/" + fileName
        };

        for (String candidate : candidates) {
            Path p = Paths.get(candidate);
            if (Files.exists(p)) {
                return candidate;
            }
        }
        return fileName;
    }
}
