package uz.br29.appschool.configuration.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Component
@EnableJpaAuditing
public class AuditConfiguration {

    @Bean
    AuditorAware<Long> auditorAware() {
        return new Audit();
    }

}