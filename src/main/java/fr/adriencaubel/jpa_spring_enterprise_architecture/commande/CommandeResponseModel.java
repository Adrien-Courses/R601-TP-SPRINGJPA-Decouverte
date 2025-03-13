package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import fr.adriencaubel.jpa_spring_enterprise_architecture.article.Article;
import fr.adriencaubel.jpa_spring_enterprise_architecture.client.ClientResponseModel;

public class CommandeResponseModel {
	private Long id;
    private LocalDateTime dateCommande;
    private ClientResponseModel client;
    private List<Long> articleIds;
    private Double total;

    // Constructeur
    public CommandeResponseModel(Commande commande) {
        this.id = commande.getId();
        this.dateCommande = commande.getCreatedOn();
        this.client = new ClientResponseModel(commande.getClient());
        this.articleIds = commande.getArticles().stream()
                                  .map(Article::getId)
                                  .collect(Collectors.toList());
        
        this.total = commande.getArticles().stream()
                .mapToDouble(ligne -> ligne.getPrix())
                .sum();
    }

    // Getters
    public Long getId() { return id; }
    public LocalDateTime getDateCommande() { return dateCommande; }
    public ClientResponseModel getClient() { return client; }
    public List<Long> getArticleIds() { return articleIds; }
	public Double getTotal() { return total; }
}
