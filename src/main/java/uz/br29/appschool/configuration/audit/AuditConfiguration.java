package uz.br29.appschool.configuration.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@EnableJpaAuditing
public class AuditConfiguration {

    @Bean
    AuditorAware<UUID> auditorAware() {
        return new Audit();
    }

}