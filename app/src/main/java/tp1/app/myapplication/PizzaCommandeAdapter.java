package tp1.app.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Database.Pizza;

public class PizzaCommandeAdapter extends RecyclerView.Adapter<PizzaCommandeAdapter.PizzaViewHolder> {

    private List<Pizza> pizzaList;
    private List<Pizza> uniquePizzaList;
    private MainActivity mainActivity;

    private HashMap<Integer, Integer> quantityMap;

    public PizzaCommandeAdapter(List<Pizza> pizzaList, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.pizzaList = pizzaList;
        this.quantityMap = new HashMap<>();
        initializeQuantityMap();
        createUniquePizzaList();
    }

    private void createUniquePizzaList() {
        uniquePizzaList = new ArrayList<>();

        for (Pizza pizza : pizzaList) {
            if (!uniquePizzaList.contains(pizza)) {
                uniquePizzaList.add(pizza);
            }
        }
    }

    private void initializeQuantityMap() {
        for (Pizza pizza : pizzaList) {
            if (!quantityMap.containsKey(pizza.id)) {
                quantityMap.put(pizza.id, 1);
            } else {
                int amountTemp = quantityMap.get(pizza.id);
                quantityMap.put(pizza.id, amountTemp + 1);
            }
        }
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pizza_commande, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        holder.sorteC.setText(pizza.sorte);
        holder.typeC.setText(pizza.type);
        holder.prixC.setText(String.valueOf(pizza.prix));

        int quantity = quantityMap.get(pizza.id);
        holder.quantite.setText(String.valueOf(quantity));
    }

    @Override
    public int getItemCount() {
        return uniquePizzaList.size();
    }

    public class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView sorteC, typeC, prixC, quantite;
        Button plusButton, minusButton;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            sorteC = itemView.findViewById(R.id.SorteC);
            typeC = itemView.findViewById(R.id.TypeC);
            prixC = itemView.findViewById(R.id.PrixC);
            quantite = itemView.findViewById(R.id.quantite);
            plusButton = itemView.findViewById(R.id.plus);
            minusButton = itemView.findViewById(R.id.minus);

            // Plus button click listener
            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        increaseQuantity(position);
                    }
                }
            });

            // Minus button click listener
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        decreaseQuantity(position);
                    }
                }
            });
        }

        // Method to increase quantity
        private void increaseQuantity(int position) {
            Pizza pizza = pizzaList.get(position);
            int pizzaId = pizza.getId();
            int quantity = quantityMap.getOrDefault(pizzaId, 0);
            quantity++;
            quantityMap.put(pizzaId, quantity);
            notifyItemChanged(position);
        }

        // Method to decrease quantity
        private void decreaseQuantity(int position) {
            Pizza pizza = pizzaList.get(position);
            int pizzaId = pizza.getId();
            int quantity = quantityMap.getOrDefault(pizzaId, 0);
            if (quantity > 0) {
                quantity--;
                quantityMap.put(pizzaId, quantity);
                notifyItemChanged(position);
            }
        }
    }
}
