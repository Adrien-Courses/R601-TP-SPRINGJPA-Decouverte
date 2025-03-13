package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/commandes")
public class CommandeController {
    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
    
    
    @GetMapping("/lazy")
    public ResponseEntity<List<CommandeResponseModel>> getAllCommandesLazy() {
    	List<Commande> commandes = commandeService.getAllCommandesLazy();
    	
    	List<CommandeResponseModel> commandeResponseModels = toResponseModel(commandes);
    	
    	return ResponseEntity.ok(commandeResponseModels);
    }
    
    @GetMapping
    public ResponseEntity<List<CommandeResponseModel>> getAllCommandes() {
    	List<Commande> commandes = commandeService.getAllCommandes();
    	
    	List<CommandeResponseModel> commandeResponseModels = toResponseModel(commandes);
    	
    	return ResponseEntity.ok(commandeResponseModels);
    }


    @PostMapping()
    public ResponseEntity<CommandeResponseModel> createCommande(
        @RequestBody CommandeRequestModel commande) {
        Commande nouvelleCommande = commandeService.createCommande(commande);
        
        CommandeResponseModel response = new CommandeResponseModel(nouvelleCommande);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
    
	private List<CommandeResponseModel> toResponseModel(List<Commande> commandes) {
		List<CommandeResponseModel> commandeResponseModels = new ArrayList<CommandeResponseModel>();
    	for(Commande commande : commandes) {
        	CommandeResponseModel commandeResponseModel = new CommandeResponseModel(commande);
        	commandeResponseModels.add(commandeResponseModel);

    	}
		return commandeResponseModels;
	}
}