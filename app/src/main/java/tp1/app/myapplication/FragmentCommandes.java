package tp1.app.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Database.Pizza;

public class FragmentCommandes extends Fragment {
    private TextView prixTotal;
    private TextView economie;
    private TextView totalEconomie;
    private CheckBox pointsUse;
    private Button addToCart;
    private RecyclerView recyclerView;
    private PizzaCommandeAdapter pizzaAdapter;
    private List<Pizza> pizzaList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commandes, container, false);

        prixTotal = view.findViewById(R.id.prixTotal);
        economie = view.findViewById(R.id.economie);
        totalEconomie = view.findViewById(R.id.totalEconomie);
        pointsUse = view.findViewById(R.id.pointsUse);
        recyclerView = view.findViewById(R.id.recyclerCommande);
        addToCart = view.findViewById(R.id.addToCart);
        pointsUse = view.findViewById(R.id.pointsUse);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        int points = 0;

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            pizzaList = mainActivity.getCommande();
            pizzaAdapter = new PizzaCommandeAdapter(pizzaList);
            recyclerView.setAdapter(pizzaAdapter);
            points = mainActivity.getConnectedClient().points;
        }
        Double tot = 0.0;
        Double econ = 0.0;
        Double totalEcon = 0.0;
        for (Pizza pizza : pizzaList) {
            tot += pizza.prix;
        }
        econ = points * 0.75 / 100;
        totalEcon = tot - econ;

        prixTotal.setText(String.valueOf("Total Price : " + tot));
        economie.setText(String.valueOf("Vous economisez : " + econ));
        totalEconomie.setText(String.valueOf("Total avec points : " + totalEcon));

        Double finalTot = tot;
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    if (pointsUse.isActivated()) {
                        mainActivity.getConnectedClient().setPoints(0);
                    } else {
                        String priceText = prixTotal.getText().toString();
                        if (!priceText.isEmpty()) {
                            double price = finalTot;
                            mainActivity.getConnectedClient().setPoints((int) (mainActivity.getConnectedClient().points + (price * 10)));
                        }
                    }

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Passez a la caisse pour paiement par Carte");
                    alertDialogBuilder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.remove(FragmentCommandes.this).commit();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

        return view;
    }
}
