package fr.adriencaubel.jpa_spring_enterprise_architecture.client;

public class ClientResponseModel {
    private Long id;
    private String nom;
    private String email;

    public ClientResponseModel(Client client) {
        this.id = client.getId();
        this.nom = client.getNom();
        this.email = client.getEmail();
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
}
