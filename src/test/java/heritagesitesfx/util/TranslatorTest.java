package heritagesitesfx.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

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
