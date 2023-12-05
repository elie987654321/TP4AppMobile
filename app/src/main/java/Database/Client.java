package Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Client
{
    @DatabaseField(columnName = "id_client", generatedId = true)
    public int id;

    @DatabaseField(columnName = "adresse_couriel")
    public String adresseCourriel;

    @DatabaseField(columnName = "mot_de_passe")
    public String motDePasse;

    @DatabaseField(columnName = "adresse_de_livraison")
    public String adresseDeLivraison;
}
