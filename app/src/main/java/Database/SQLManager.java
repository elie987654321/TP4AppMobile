package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import Database.Client;
import Database.Commande;
import Database.Pizza;


public class SQLManager extends OrmLiteSqliteOpenHelper {

    private final static String DATABASE_NAME = "BD_pizzeria";

    private final static int DATABASE_VERSION = 1;

    public SQLManager(Context context, ConnectionSource connectionSource)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTable(connectionSource, Pizza.class);
            TableUtils.createTable(connectionSource, Client.class);
            TableUtils.createTable(connectionSource, Commande.class);
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la creation de la base de donnée");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {

    }

    public void CreateFakeDatabase()
    {

    }



}
