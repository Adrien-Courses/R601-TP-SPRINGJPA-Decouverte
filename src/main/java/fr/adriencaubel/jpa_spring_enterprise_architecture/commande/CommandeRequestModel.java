package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import java.util.ArrayList;
import java.util.List;

public class CommandeRequestModel {
    private Long clientId;
    private List<Long> articleIds = new ArrayList<>();

    public Long getClientId() {
        return clientId;
    }

    public List<Long> getArticleIds() {
        return articleIds;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setArticleIds(List<Long> articleIds) {
        this.articleIds = articleIds;
    }
}