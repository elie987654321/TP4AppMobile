package tp1.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.database.SQLException;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.j256.ormlite.dao.Dao;

import Database.Client;
import Database.Pizza;
import Database.SQLManager;

public class MainActivity extends AppCompatActivity
{
    DrawerLayout dLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dLayout.openDrawer(Gravity.LEFT);
            }
        });
        setNavigationDrawer();

        SQLManager sqlManager = new SQLManager(this, null);

        // Add base users
        Client client1 = new Client();
        client1.nom = "charles";
        client1.adresseCourriel = "charles@hotmail.ca";
        client1.motDePasse = "1234";
        client1.adresseDeLivraison = "123 rue Testeur";
        client1.telephone = "123-456-7890";
        client1.points = 0;

        Client client2 = new Client();
        client2.nom = "elie";
        client2.adresseCourriel = "elie@hotmail.ca";
        client2.motDePasse = "4321";
        client2.adresseDeLivraison = "321 rue Testeur";
        client2.telephone = "098-765-4321";
        client2.points = 0;

        try {
            Dao<Client, Integer> clientDao = sqlManager.getDao(Client.class);
            clientDao.createOrUpdate(client1);
            clientDao.createOrUpdate(client2);
        } catch (SQLException | java.sql.SQLException e) {
            e.printStackTrace();
        }

        // Add base pizzas
        Pizza pizza1 = new Pizza();
        pizza1.sorte = "Double Cheese";
        pizza1.type = "petit";
        pizza1.prix = 8.99;

        Pizza pizza2 = new Pizza();
        pizza2.sorte = "Pepperoni";
        pizza2.type = "moyen";
        pizza2.prix = 10.99;

        try {
            Dao<Pizza, Integer> pizzaDao = sqlManager.getDao(Pizza.class);
            pizzaDao.createOrUpdate(pizza1);
            pizzaDao.createOrUpdate(pizza2);
        } catch (SQLException | java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private void setNavigationDrawer() {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
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