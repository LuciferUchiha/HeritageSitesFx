package heritagesitesfx.util;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TranslatorTest {

    @BeforeAll
    public static void initToolkit()
            throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });

        // That's a pretty reasonable delay... Right?
        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }

    @BeforeEach
    public void resetToDefaultLocale() {
        Translator.setLocale(Translator.getDefaultLocale());
    }

    @Test
    public void testGetGermanText() {
        Translator.setLocale(Locale.GERMAN);
        Assertions.assertEquals("Welterbe", Translator.get("application.title"));
    }

    @Test
    public void testGetEnglishText() {
        Translator.setLocale(Locale.ENGLISH);
        Assertions.assertEquals("Heritage Sites", Translator.get("application.title"));
    }

    @Test
    public void testDefaultLocaleIsGerman() {
        Assertions.assertEquals(Locale.GERMAN, Translator.getDefaultLocale());
        Assertions.assertEquals(Locale.GERMAN, Translator.getLocale());
        Assertions.assertEquals("Welterbe", Translator.get("application.title"));
    }

    @Test
    public void testButtonCreation() {
        Assertions.assertEquals("Welterbe", Translator.buttonForKey("application.title").getText());
    }

    @Test
    public void testLabelCreation() {
        Assertions.assertEquals("Welterbe", Translator.labelForKey("application.title").getText());
    }
}
