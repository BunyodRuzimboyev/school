package uz.br29.appschool.service;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Service;

@Service
public class SanitizeInputService {
    public String sanitizeInput(String unsafeText) {
        return sanitizeInputStepTwo(sanitizeInputStepOne(unsafeText));
    }

    private String sanitizeInputStepOne(String unsafeText) {
        if (unsafeText != null) {
            PolicyFactory policy = new HtmlPolicyBuilder()
                    .allowElements("a", "b", "i")
                    .toFactory();
            return policy.sanitize(unsafeText);
        } else {
            return null;
        }
    }

    private String sanitizeInputStepTwo(String unsafeText) {
        if (unsafeText != null) {
            return Jsoup.clean(unsafeText, Safelist.basic());
        }
        else return null;
    }


}

