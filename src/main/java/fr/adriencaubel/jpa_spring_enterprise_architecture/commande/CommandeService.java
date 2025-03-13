package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAllCommandes() {
    	// todo
    	return null;
    }
    
    public Commande createCommande(CommandeRequestModel commandeRequestModel) {
    	// todo
    	return null;
    }

    public void deleteCommande(Long id) {
    	// todo
    }
}
