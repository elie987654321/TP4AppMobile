package tp1.app.myapplication;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Commande
{
    @DatabaseField(columnName = "id_commande", generatedId = true)
    public int id;

    @DatabaseField(columnName = "numero_commande")
    public String numero;

    @DatabaseField(columnName = "montant_paye_commande")
    public float montantPaye;

    @DatabaseField(columnName = "date_commande")
    public Date date;
}