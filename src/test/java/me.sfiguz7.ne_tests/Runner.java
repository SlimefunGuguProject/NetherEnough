package me.sfiguz7.ne_tests;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.opentest4j.AssertionFailedError;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Original author: @Walshy
 */
public class Runner {

    private static boolean color = true;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("--no-color"))
            color = false;

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
            .request()
            .selectors(DiscoverySelectors.selectPackage("me.sfiguz7.ne_tests"))
            .build();

        Launcher launcher = LauncherFactory.create();
        launcher.discover(request);
        launcher.registerTestExecutionListeners(new TestListener());
        launcher.execute(request);
    }

    private static final class TestListener implements TestExecutionListener {

        public void executionStarted(TestIdentifier id) {
            if (id.isContainer() &&
                !id.getDisplayName().contains("JUnit")
                && id.getDisplayName().endsWith("Test")
            ) {
                System.out.println(Ansi.PURPLE + id.getDisplayName());
            }
        }

        public void executionFinished(TestIdentifier id, TestExecutionResult result) {
            if (id.isContainer()) return;

            final boolean pass = result.getStatus() == TestExecutionResult.Status.SUCCESSFUL;
            System.out.println(Ansi.WHITE + "  [" + (pass ? Ansi.GREEN + "+" : Ansi.RED + "-") + Ansi.WHITE + "] "
                + id.getDisplayName());

            if (!pass && result.getThrowable().isPresent()) {
                final Throwable t = result.getThrowable().get();
                if (t instanceof AssertionFailedError) {
                    System.out.println("    " + Ansi.RED + t.getMessage() + '\n' +
                        Arrays.stream(result.getThrowable().get().getStackTrace())
                            .filter(e -> e.getClassName().startsWith("dev.walshy."))
                            .map(e -> "    " + Ansi.RED + e.getClassName() + '.' + e.getMethodName() + ": "
                                + e.getLineNumber())
                            .collect(Collectors.joining("\n"))
                    );
                } else {
                    System.out.println("    " + Ansi.RED + t.getClass().getSimpleName() + ": " + t.getMessage() + '\n' +
                        Arrays.stream(result.getThrowable().get().getStackTrace())
                            .filter(e -> e.getClassName().startsWith("dev.walshy."))
                            .map(e -> "    " + Ansi.RED + e.getClassName() + '.' + e.getMethodName() + ": "
                                + e.getLineNumber())
                            .collect(Collectors.joining("\n"))
                    );
                }
            }
        }

        public void executionSkipped(TestIdentifier id, String reason) {
            System.out.println(Ansi.WHITE + "  [ ] " + id.getDisplayName() + " (Skipped)");
        }

        public void reportingEntryPublished(TestIdentifier id, ReportEntry entry) {
            System.out.println("reportingEntryPublished - " + id.getDisplayName());
        }
    }

    private enum Ansi {

        RESET(0),
        BLACK(30),
        RED(31),
        GREEN(32),
        YELLOW(33),
        BLUE(34),
        PURPLE(35),
        CYAN(36),
        WHITE(37);

        private final String ansiCode;

        Ansi(int ansiCode) {
            this.ansiCode = "\u001B[" + ansiCode + "m";
        }

        @Override
        public String toString() {
            return color ? this.ansiCode : "";
        }
    }
}
