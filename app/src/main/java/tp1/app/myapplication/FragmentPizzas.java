package tp1.app.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import Database.Client;
import Database.DatabaseManager;
import Database.Pizza;

public class FragmentPizzas extends Fragment {

    private DatabaseManager databaseManager;
    private RecyclerView recyclerView;
    private PizzaAdapter pizzaAdapter;
    private List<Pizza> pizzaList;

    private List<Pizza> getPizzaList() {
        databaseManager = new DatabaseManager(getContext());
        List<Pizza> pizzas = databaseManager.readPizzas();
        databaseManager.close();
        return pizzas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizzas, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        pizzaList = getPizzaList(); // Fetch the list of pizzas

        // Create and set the adapter
        pizzaAdapter = new PizzaAdapter(pizzaList, (MainActivity) getActivity());
        recyclerView.setAdapter(pizzaAdapter);

        // Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }
}