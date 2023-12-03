package tp1.app.myapplication;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Pizza {
    @DatabaseField(columnName = "id_pizza", generatedId = true)
    public int id;

    @DatabaseField (columnName = "type_pizza")
    public String type;

    @DatabaseField (columnName = "prix_pizza")
    public float prix;
}
