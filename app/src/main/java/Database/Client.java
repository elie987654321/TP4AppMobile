package Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_Clients")
public class Client
{
    @DatabaseField(columnName = "idClient", generatedId = true)
    public int id;

    @DatabaseField(columnName = "nom")
    public String nom;

    @DatabaseField(columnName = "adresse_couriel")
    public String adresseCourriel;

    @DatabaseField(columnName = "mot_de_passe")
    public String motDePasse;

    @DatabaseField(columnName = "adresse_de_livraison")
    public String adresseDeLivraison;

    @DatabaseField(columnName = "telephone")
    public String telephone;

    @DatabaseField(columnName = "points")
    public int points;

    public Client(){
    }

    public Client(String nom, String adresseCourriel, String motDePasse, String adresseDeLivraison, String telephone, int points) {
        this.nom = nom;
        this.adresseCourriel = adresseCourriel;
        this.motDePasse = motDePasse;
        this.adresseDeLivraison = adresseDeLivraison;
        this.telephone = telephone;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseCourriel() {
        return adresseCourriel;
    }

    public void setAdresseCourriel(String adresseCourriel) {
        this.adresseCourriel = adresseCourriel;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getAdresseDeLivraison() {
        return adresseDeLivraison;
    }

    public void setAdresseDeLivraison(String adresseDeLivraison) {
        this.adresseDeLivraison = adresseDeLivraison;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
