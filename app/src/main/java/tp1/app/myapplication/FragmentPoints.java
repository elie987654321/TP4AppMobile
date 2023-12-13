package tp1.app.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import Database.Client;

public class FragmentPoints extends Fragment {

    TextView textPoints;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_points, container, false);
        textPoints = view.findViewById(R.id.textPoints);
        Client client;

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            client = mainActivity.getConnectedClient();
            textPoints.setText(String.valueOf("Points : " + client.points));
        }

        return view;
    }
}