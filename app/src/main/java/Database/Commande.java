package Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable( tableName = "T_Commandes")
public class Commande
{
    @DatabaseField(columnName = "id_commande", generatedId = true)
    public int id;

    @DatabaseField(columnName = "numero_commande")
    public String numero;

    @DatabaseField(columnName = "nom_client")
    public String nom_client;

    @DatabaseField(columnName = "montant_paye_commande")
    public float montantPaye;

    @DatabaseField(columnName = "date_commande")
    public Date date;

    public Commande(){
    }

    public Commande(String numero, String nom_client, float montantPaye, Date date) {
        this.numero = numero;
        this.nom_client = nom_client;
        this.montantPaye = montantPaye;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public float getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(float montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "numero='" + numero + '\'' +
                ", nom_client='" + nom_client + '\'' +
                ", montantPaye=" + montantPaye +
                ", date=" + date +
                '}';
    }
}
