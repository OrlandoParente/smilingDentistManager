package sdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sdms.model.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

}
