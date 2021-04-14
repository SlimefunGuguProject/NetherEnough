package me.sfiguz7.ne_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Original author: @Walshy
 */
class NonnullOrNullableAnnotationsTest {

    @Test
    void checkForNonnullOrNullableAnnotations() {
        final Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
            .setUrls(ClasspathHelper.forPackage("me.sfiguz7.netherenough"))
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("me.sfiguz7.netherenough")))
        );

        final Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        for (Class<?> clazz : classes) {
            for (Method method : clazz.getDeclaredMethods()) {
                final Parameter param = findParamWithNoAnnotation(method);
                if (param != null) {
                    fail(clazz.getName() + '#' + method.getName() + " is missing @Nonnull/@Nullable on parameter "
                        + '\'' + param.getName() + '\'');
                }
            }
        }

        Assertions.assertTrue(true);
    }

    @Nullable
    private Parameter findParamWithNoAnnotation(Method method) {
        if (method.getName().contains("lambda")) return null;

        if (hasParametersAreNonnullByDefault(method.getDeclaredAnnotations())) {
            return null;
        }

        for (Parameter param : method.getParameters()) {
            if (param.getType().isPrimitive()) continue;

            if (!hasNonnullOrNullable(param.getAnnotations()))
                return param;
        }
        return null;
    }

    private boolean hasParametersAreNonnullByDefault(@Nonnull Annotation[] annotations) {
        for (Annotation ann : annotations) {
            if (ann instanceof ParametersAreNonnullByDefault || ann instanceof Nonnull || ann instanceof Nullable)
                return true;
        }
        return false;
    }

    private boolean hasNonnullOrNullable(@Nonnull Annotation[] annotations) {
        for (Annotation ann : annotations) {
            if (ann instanceof ParametersAreNonnullByDefault || ann instanceof Nonnull || ann instanceof Nullable)
                return true;
        }
        return false;
    }
}
