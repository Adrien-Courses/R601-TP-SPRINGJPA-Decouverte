package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adriencaubel.jpa_spring_enterprise_architecture.article.Article;
import fr.adriencaubel.jpa_spring_enterprise_architecture.article.ArticleRepository;
import fr.adriencaubel.jpa_spring_enterprise_architecture.article.ArticleService;
import fr.adriencaubel.jpa_spring_enterprise_architecture.client.Client;
import fr.adriencaubel.jpa_spring_enterprise_architecture.client.ClientRepository;
import fr.adriencaubel.jpa_spring_enterprise_architecture.client.ClientService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ClientService clientService;
    private final ArticleService articleService;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository, 
    		ClientService clientService, ArticleService articleService) {
        this.commandeRepository = commandeRepository;
        this.clientService = clientService;
        this.articleService = articleService;
    }
    
    public List<Commande> getAllCommandesLazy() {
    	List<Commande> commandes = commandeRepository.findAll();
    	return commandes;
    }

    public List<Commande> getAllCommandes() {
    	List<Commande> commandes = commandeRepository.findAllWithClientAndArticles();
    	return commandes;
    }
    
    public Commande createCommande(CommandeRequestModel commandeRequestModel) {
    	// Récupérer le client
        Client client = clientService.getById(commandeRequestModel.getClientId());
        
        // Récupérer les articles
        List<Article> articles = articleService.findAllById(commandeRequestModel.getArticleIds());
        
        // Vérifier si tous les articles sont actifs
        List<Article> articlesInactifs = articles.stream()
                .filter(article -> !article.isActif())
                .collect(Collectors.toList());

        if (!articlesInactifs.isEmpty()) {
            throw new IllegalArgumentException("Impossible de créer la commande. Les articles suivants sont inactifs: " 
                + articlesInactifs.stream()
                    .map(Article::getNom)
                    .collect(Collectors.joining(", ")));
        }
        
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setArticles(articles);
        commande.setCreatedOn(LocalDateTime.now());
        
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Long id) {
    	Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commande non trouvée"));
    	
        commandeRepository.delete(commande);
    }
}
