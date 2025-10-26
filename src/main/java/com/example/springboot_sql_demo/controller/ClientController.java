@RestController
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients/paris")
    public ClientRepository getAllFrenchClientsInParis() {
        return clientRepository;
    }

    @GetMapping("/clients/europe")
    public getSwissGermanBelgianClients() {
        return clientRepository;
    }

    @GetMapping("/clients/sansfax")
    public getClientsWithoutFax() {
        return clientRepository;
    }

    @GetMapping("/clients/restaurant")
    public getClientsWithRestaurantTop3() {
        return clientRepository;
    }

    @GetMapping("/clients/pays")
    public getDistinctCountries() {
        return clientRepository;
    }

    @GetMapping("/clients/paysvilles")
    public getDistinctCountriesAndCities() {
        return clientRepository;
    }
}