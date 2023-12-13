package tp1.app.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DatabaseManager;

public class FragmentConnexion extends Fragment {

    private DatabaseManager databaseManager;
    private EditText editEmail;
    private EditText editPassword;
    private Button buttonAuthentification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseManager = new DatabaseManager(FragmentConnexion.super.getContext());

        View view = inflater.inflate(R.layout.fragment_connexion, container, false);

        editEmail = view.findViewById(R.id.editEmail);
        editPassword = view.findViewById(R.id.editMotDePasse);
        buttonAuthentification = view.findViewById(R.id.buttonAuthentification);

        buttonAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                Toast.makeText(getActivity(), email + " " + password, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
