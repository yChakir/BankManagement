package ma.tiwtiw.bank.util;

import java.util.Locale;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Translator {

  private static ResourceBundleMessageSource source;

  private Translator(ResourceBundleMessageSource source) {
    Translator.source = source;
  }

  public static String translate(String code) {
    return translate(code, null);
  }

  public static String translate(String code, String... args) {
    Locale locale = LocaleContextHolder.getLocale();
    return source.getMessage(code, args, locale);
  }
}
