@Repository
public CommandeRepository {

    public findNoEmp (){
        return ("SELECT DISTINCT NoEmp FROM Commande WHERE PaysLiv = 'France' AND VilleLiv IN ('Lille','Lyon','Nantes')")
    }
}