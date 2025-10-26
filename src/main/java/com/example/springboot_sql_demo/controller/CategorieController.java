@RestController
public class CategorieController {

    private final CategorieRepository categorieRepository;

    public CategorieController(CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/categories/descriptions")
    public  getCategorieDescriptions() {
        return categorieRepository;
    }
}