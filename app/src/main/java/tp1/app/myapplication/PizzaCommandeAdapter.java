package tp1.app.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Database.Pizza;

public class PizzaCommandeAdapter extends RecyclerView.Adapter<PizzaCommandeAdapter.PizzaViewHolder> {

    private List<Pizza> pizzaList;

    public PizzaCommandeAdapter(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
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
        holder.quantite.setText("1");
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView sorteC, typeC, prixC, quantite;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            sorteC = itemView.findViewById(R.id.SorteC);
            typeC = itemView.findViewById(R.id.TypeC);
            prixC = itemView.findViewById(R.id.PrixC);
            quantite = itemView.findViewById(R.id.quantite);
        }
    }
}
