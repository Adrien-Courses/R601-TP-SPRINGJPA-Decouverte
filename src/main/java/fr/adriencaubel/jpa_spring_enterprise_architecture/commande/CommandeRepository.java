package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
