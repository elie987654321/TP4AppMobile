package tp1.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickBoutonInscription()
    {
        ActiviteInscription activiteInscription = new ActiviteInscription();

        Intent intent = new Intent(this, ActiviteInscription.class);
        startActivity(intent);
    }

}