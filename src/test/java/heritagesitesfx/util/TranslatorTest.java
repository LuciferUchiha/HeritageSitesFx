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
}
