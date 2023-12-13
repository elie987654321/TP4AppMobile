package tp1.app.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import Database.Pizza;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private List<Pizza> pizzaList;

    public PizzaAdapter(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        // Set pizza data to views
        holder.pizzaImage.setImageResource(R.drawable.pizza); // Replace with actual pizza image
        holder.sorteTextView.setText(pizza.getSorte());
        holder.typeTextView.setText(pizza.getType());
        holder.prixTextView.setText(String.valueOf(pizza.getPrix())); // Assuming prix is a double or float
        holder.ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        ImageView pizzaImage;
        TextView sorteTextView, typeTextView, prixTextView;
        Button ajouterButton;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaImage = itemView.findViewById(R.id.PizzaImage);
            sorteTextView = itemView.findViewById(R.id.Sorte);
            typeTextView = itemView.findViewById(R.id.Type);
            prixTextView = itemView.findViewById(R.id.Prix);
            ajouterButton = itemView.findViewById(R.id.Ajouter);
        }
    }
}
