package Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Client
{
    @DatabaseField(columnName = "id_client", generatedId = true)
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
}
