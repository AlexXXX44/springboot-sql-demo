@RestController
public class EmployeController {

    private final EmployeRepository employeRepository;

    public EmployeController(EmployeRepository employeRepository){
        this.employeRepository = employeRepository;
    }

    @GetMapping("/employes/commandes-france")
    public EmployeRepository getEmployeRepository() {
        return employeRepository;
    }
}