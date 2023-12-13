package tp1.app.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Database.Client;
import Database.DatabaseManager;

public class FragmentConnexion extends Fragment {

    private DatabaseManager databaseManager;
    private EditText editEmail;
    private EditText editPassword;
    private Button buttonAuthentification;
    private Button buttonCreerCompte;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseManager = new DatabaseManager(FragmentConnexion.super.getContext());

        View view = inflater.inflate(R.layout.fragment_connexion, container, false);

        editEmail = view.findViewById(R.id.editEmail);
        editPassword = view.findViewById(R.id.editMotDePasse);
        buttonAuthentification = view.findViewById(R.id.buttonAuthentification);
        buttonCreerCompte = view.findViewById(R.id.buttonCreerCompte);

        buttonAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                boolean clientExiste = false;

                databaseManager = new DatabaseManager(getContext());
                List<Client> clients = databaseManager.readClients();
                databaseManager.close();

                if (clients != null && !clients.isEmpty()) {
                    for (Client client : clients) {
                        if (client.motDePasse.equals(password) && client.adresseCourriel.equals(email)) {
                            MainActivity mainActivity = (MainActivity) getActivity();
                            if (mainActivity != null) {
                                mainActivity.setNavigationDrawer(true);
                                mainActivity.setConnectedClient(client);
                            }
                            clientExiste = true;

                            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame, new FragmentPizzas());
                            fragmentTransaction.commit();
                            break;
                        }
                    }
                }

                if (!clientExiste) {
                    Toast.makeText(getActivity(), "Email et/ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCreerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, new FragmentInscription());
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
