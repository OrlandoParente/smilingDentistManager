package sdms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sdms.model.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

	Optional<EmailTemplate> findByType(String type);
}
