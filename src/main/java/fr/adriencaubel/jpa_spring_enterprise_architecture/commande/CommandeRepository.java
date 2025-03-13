package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
      @Query("SELECT DISTINCT c FROM Commande c "
            + "LEFT JOIN FETCH c.client "
            + "LEFT JOIN FETCH c.articles")
       List<Commande> findAllWithClientAndArticles();
}
