@RestController
public class ProduitController {

    private final ProduitRepository produitRepository;

    public ProduitController(ProduitRepository produitRepository){
        this.produitRepository = produitRepository;
    }

    @GetMapping("/produits")
    public ProduitRepository getAllProduits() {
        return produitRepository;
    }

    @GetMapping("/produits/first10")
    public getFirst10() {
        return produitRepository;
    }

    @GetMapping("/produits/sorted")
    public getSortedByPrix() {
        return produitRepository;
    }

    @GetMapping("/produits/top3")
    public getTop3() {
        return produitRepository;
    }

    @GetMapping("/produits/bouteille-canettes")
    public getBottlesOrCans() {return produitRepository;}

    @GetMapping("/produits/tofu-choco")
    public getTofuOrChocoUnder100(){
        return produitRepository;
    }

    @GetMapping("/produits/fournisseur8")
    public ProduitRepository getProductsFromSupplier8() {
        return produitRepository;
    }
}