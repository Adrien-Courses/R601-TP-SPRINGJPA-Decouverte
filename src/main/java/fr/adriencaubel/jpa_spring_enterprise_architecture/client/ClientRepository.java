package fr.adriencaubel.jpa_spring_enterprise_architecture.client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Optional<Client> findByEmail(String email);
}
