package fr.adriencaubel.jpa_spring_enterprise_architecture.article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>{

	List<Article> findByActif(boolean isActif);

}
