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

import Database.Client;
import Database.DatabaseManager;

public class FragmentInscription extends Fragment {
    private EditText editNom;
    private EditText editCourriel;
    private EditText editPassword;
    private EditText editAdresse;
    private EditText editTelephone;
    private Button buttonCreation;
    private DatabaseManager databaseManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inscription, container, false);

        editNom = view.findViewById(R.id.editNom);
        editCourriel = view.findViewById(R.id.editCourriel);
        editPassword = view.findViewById(R.id.editPassword);
        editAdresse = view.findViewById(R.id.editAdresse);
        editTelephone = view.findViewById(R.id.editTelephone);
        buttonCreation = view.findViewById(R.id.buttonCreation);

        buttonCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editNom.getText().toString().trim();
                String courriel = editCourriel.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String adresse = editAdresse.getText().toString().trim();
                String telephone = editTelephone.getText().toString().trim();

                if (!nom.isEmpty() && !courriel.isEmpty() && !password.isEmpty()
                        && !adresse.isEmpty() && !telephone.isEmpty()) {
                    // Create a Client object
                    Client newClient = new Client(nom, courriel, password, adresse, telephone, 0);
                    databaseManager = new DatabaseManager(requireContext());
                    databaseManager.insertClient(newClient);
                    databaseManager.close();
                    MainActivity mainActivity = (MainActivity) getActivity();
                    if (mainActivity != null) {
                        mainActivity.setNavigationDrawer(true);
                        mainActivity.setConnectedClient(newClient);
                    }
                    FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, new FragmentPizzas());
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getActivity(), "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}