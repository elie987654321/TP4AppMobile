package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;


import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class DatabaseManager extends OrmLiteSqliteOpenHelper {

    private final static String DATABASE_NAME = "BD_pizzeria";

    private final static int DATABASE_VERSION = 1;

    public DatabaseManager(Context context)
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
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable( connectionSource, Pizza.class, true );
            TableUtils.dropTable( connectionSource, Client.class, true );
            TableUtils.dropTable( connectionSource, Commande.class, true );
            onCreate( database, connectionSource);
            Log.i( "DATABASE", "onUpgrade invoked" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Can't upgrade Database", exception );
        }
    }

    public void insertClient( Client client ) {
        try {
            Dao<Client, Integer> dao = getDao( Client.class );
            dao.create( client );
            Log.i( "DATABASE", "Insertion client" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Impossible d'insérer le client dans la base de données", exception );
        }
    }

    public List<Client> readClients() {
        try {
            Dao<Client, Integer> dao = getDao( Client.class );
            List<Client> clients = dao.queryForAll();
            Log.i( "DATABASE", "Lecture des clients" );
            return clients;
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Impossible de lire les clients la base de données", exception );
            return null;
        }
    }

    public void insertCommande( Commande commande ) {
        try {
            Dao<Commande, Integer> dao = getDao( Commande.class );
            dao.create( commande );
            Log.i( "DATABASE", "Insertion commande" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Impossible d'insérer la commande dans la base de données", exception );
        }
    }

    public List<Commande> readCommandes() {
        try {
            Dao<Commande, Integer> dao = getDao( Commande.class );
            List<Commande> commandes = dao.queryForAll();
            Log.i( "DATABASE", "Lecture des commandes" );
            return commandes;
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Impossible de lire les commandes la base de données", exception );
            return null;
        }
    }

    public void insertPizza( Pizza pizza ) {
        try {
            Dao<Pizza, Integer> dao = getDao( Pizza.class );
            dao.create( pizza );
            Log.i( "DATABASE", "Insertion pizza" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Impossible d'insérer la pizza dans la base de données", exception );
        }
    }

    public List<Pizza> readPizzas() {
        try {
            Dao<Pizza, Integer> dao = getDao( Pizza.class );
            List<Pizza> pizzas = dao.queryForAll();
            Log.i( "DATABASE", "Lecture des pizzas" );
            return pizzas;
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Impossible de lire les clients la base de données", exception );
            return null;
        }
    }

    public boolean isDatabaseEmpty() {
        try {
            Dao<Client, Integer> clientDao = getDao(Client.class);
            Dao<Pizza, Integer> pizzaDao = getDao(Pizza.class);

            long clientCount = clientDao.countOf();
            long pizzaCount = pizzaDao.countOf();

            // You can adjust this condition as per your requirement
            // Check if both Client and Pizza tables are empty
            return (clientCount == 0 && pizzaCount == 0);
        } catch (SQLException e) {
            Log.e("DATABASE", "Error checking database records", e);
            return true; // Return true as a fail-safe option or handle the error accordingly
        }
    }

    //TODO creer la methode modifyClient pour pouvoir changer les infos du client connecter

}
