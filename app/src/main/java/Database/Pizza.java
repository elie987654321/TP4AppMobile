package Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "T_Pizzas")
public class Pizza {
    @DatabaseField(columnName = "id_pizza", generatedId = true)
    public int id;

    @DatabaseField(columnName = "sorte")
    public String sorte;

    @DatabaseField (columnName = "type_pizza")
    public String type;

    @DatabaseField (columnName = "prix")
    public double prix;

    public Pizza(){
    }

    public Pizza(String sorte, String type, double prix) {
        this.sorte = sorte;
        this.type = type;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSorte() {
        return sorte;
    }

    public void setSorte(String sorte) {
        this.sorte = sorte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "sorte='" + sorte + '\'' +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                '}';
    }
}
