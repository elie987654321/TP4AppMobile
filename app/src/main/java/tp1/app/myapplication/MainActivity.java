package tp1.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.database.SQLException;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

import Database.Client;
import Database.Pizza;
import Database.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    Client connectedClient;

    List<Pizza> commande = new ArrayList<>();
    DrawerLayout dLayout;
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DatabaseManager(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dLayout.openDrawer(Gravity.LEFT);
            }
        });
        setNavigationDrawer(false);

        if (databaseManager.isDatabaseEmpty()) {
            // Perform insertion only if the database is empty
            Client client1 = new Client("Charles", "Charlis70@hotmail.ca", "1234", "123 rue test", "123-456-7890", 0);
            Client client2 = new Client("Elie", "Elie@hotmail.ca", "4321", "321 rue test", "098-765-4321", 0);

            Pizza pizza1 = new Pizza("Double Cheese", "petit", 8.99);
            Pizza pizza2 = new Pizza("Pepperoni", "moyen", 10.99);

            databaseManager.insertClient(client1);
            databaseManager.insertClient(client2);
            databaseManager.insertPizza(pizza1);
            databaseManager.insertPizza(pizza2);
        }


        databaseManager.close();
    }

    public List<Pizza> getCommande() {
        return commande;
    }

    public void setCommande(List<Pizza> commande) {
        this.commande = commande;
    }

    public Client getConnectedClient() {
        return connectedClient;
    }

    public void setConnectedClient(Client connectedClient) {
        this.connectedClient = connectedClient;
    }

    public void setNavigationDrawer(boolean isConnected) {
        dLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.navigation);
        Menu menu = navView.getMenu();
        MenuItem accueilItem = menu.findItem(R.id.accueil);

        if (isConnected) {
            // Show all menu items
            accueilItem.setVisible(true);
            menu.findItem(R.id.pizzas).setVisible(true);
            menu.findItem(R.id.profil).setVisible(true);
            menu.findItem(R.id.commandes).setVisible(true);
            menu.findItem(R.id.points).setVisible(true);
            //TODO ajouter le petit icone cliquable d'utilisateur qui redirige vers le fragment modifier utilisateur (Tu dois aussi creer le fragment, il n'existe pas encore)
            //TODO nous somme dans un if car on veut seulement cet icone quand on est connecter, tu peus le pre-faire dans le layout et lui pre assigner le on-Click dans le
            //TODO on-create de cette activiter mais set sa visibilite a true seulement quand l"utilisateur est connecter
        } else {
            // Only show the "Accueil" menu item
            accueilItem.setVisible(true);
            // Hide other menu items
            menu.findItem(R.id.pizzas).setVisible(false);
            menu.findItem(R.id.profil).setVisible(false);
            menu.findItem(R.id.commandes).setVisible(false);
            menu.findItem(R.id.points).setVisible(false);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment frag = null;
                int itemId = menuItem.getItemId();
                if (itemId == R.id.accueil) {
                    frag = new FragmentAccueil();
                } else if (itemId == R.id.pizzas) {
                    frag = new FragmentPizzas();
                } else if (itemId == R.id.profil) {
                    frag = new FragmentProfil();
                } else if (itemId == R.id.commandes) {
                    frag = new FragmentCommandes();
                } else if (itemId == R.id.points) {
                    frag = new FragmentPoints();
                }


                Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                if (frag != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, frag);
                    transaction.commit();
                    dLayout.closeDrawers();
                    return true;
                }
                return false;
            }
        });


    }
}