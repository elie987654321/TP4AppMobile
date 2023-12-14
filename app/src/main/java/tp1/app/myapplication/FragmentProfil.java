package tp1.app.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import Database.Client;
import Database.DatabaseManager;

public class FragmentProfil extends Fragment {

    private EditText editNomE;
    private EditText editCourrielE;
    private EditText editPasswordE;
    private EditText editAdresseE;
    private EditText editTelephoneE;
    private Button buttonModification;
    private DatabaseManager databaseManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        // Access MainActivity
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        Client connectedClient = mainActivity.getConnectedClient();

        // Use the connectedClient to populate the EditText fields
        editNomE = view.findViewById(R.id.editNomE);
        editCourrielE = view.findViewById(R.id.editCourrielE);
        editPasswordE = view.findViewById(R.id.editPasswordE);
        editAdresseE = view.findViewById(R.id.editAdresseE);
        editTelephoneE = view.findViewById(R.id.editTelephoneE);

        // Set EditText fields with connectedClient's information
        editNomE.setText(connectedClient.nom);
        editCourrielE.setText(connectedClient.adresseCourriel);
        editPasswordE.setText(connectedClient.motDePasse);
        editAdresseE.setText(connectedClient.adresseDeLivraison);
        editTelephoneE.setText(connectedClient.telephone);

        buttonModification = view.findViewById(R.id.buttonModification);
        buttonModification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Modify the connectedClient with the updated information from EditText fields
                connectedClient.setNom(editNomE.getText().toString());
                connectedClient.setAdresseCourriel(editCourrielE.getText().toString());
                connectedClient.setMotDePasse(editPasswordE.getText().toString());
                connectedClient.setAdresseDeLivraison(editAdresseE.getText().toString());
                connectedClient.setTelephone(editTelephoneE.getText().toString());

                databaseManager = new DatabaseManager(getContext());
                databaseManager.modifyClient(connectedClient);
                databaseManager.close();

            }
        });

        return view;
    }
}
