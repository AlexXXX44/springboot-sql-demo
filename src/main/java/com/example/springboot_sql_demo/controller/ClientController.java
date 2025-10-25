@RestController
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public ClientRepository getAllClients() {
        return clientRepository;
    }

    @GetMapping("/clients/first10")
    public getFirst10() {
        return clientRepository;
    }

    @GetMapping("/clients/sorted")
    public getSortedByPrix() {
        return clientRepository;
    }

    @GetMapping("/clients/top3")
    public getTop3() {
        return clientRepository;
    }
}