package fr.adriencaubel.jpa_spring_enterprise_architecture.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getById(long id) {
    	return articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Article> getArticles(Boolean actif) {
        if (actif == null) {
            return articleRepository.findAll();
        }
        return articleRepository.findByActif(actif);
    }

	public List<Article> findAllById(List<Long> articleIds) {
		return articleRepository.findAllById(articleIds);
	}
}