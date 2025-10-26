@RestController
public class FournisseurController {

    private final FournisseurRepository fournisseurRepository;

    public FournisseurController(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository = fournisseurRepository;
    }

    @GetMapping("/fournisseurs/france")
    public getFrenchSuppliers(){
        return fournisseurRepository;
    }
}