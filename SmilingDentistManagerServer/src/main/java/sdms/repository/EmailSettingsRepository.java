package sdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sdms.model.EmailSettings;


public interface EmailSettingsRepository extends JpaRepository<EmailSettings, Long> {

}
