package com.example.evaluaciontemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evaluaciontemas.Adaptadores.ListaSolicitudesAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Solicitud> listSolicitudes = new ArrayList<Solicitud>();
    ArrayAdapter<Solicitud> arrayAdapterSolicitudes;
    ListaSolicitudesAdapter listaSolicitudesAdapter;
    ListView lvSolicitudes;

    Solicitud solicitudSeleccionada;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSolicitudes = findViewById(R.id.lvSolicitudes);

        lvSolicitudes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                solicitudSeleccionada = (Solicitud) adapterView.getItemAtPosition(i);
                mostrarSolicitud(solicitudSeleccionada);
            }
        });

        inicializarFireBase();
        listarSolicitudes();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agregar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAgregar) {
            Intent agregar = new Intent(this, AgregarActivity.class);
            startActivity(agregar);
        }

        return super.onOptionsItemSelected(item);
    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void mostrarSolicitud(Solicitud solicitud){
        Intent intent = new Intent(this, MostrarActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("solicitudSeleccionada", solicitud);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void listarSolicitudes(){
        databaseReference.child("Solicitudes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listSolicitudes.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Solicitud s = snapshot1.getValue(Solicitud.class);
                    listSolicitudes.add(s);
                }

                listaSolicitudesAdapter = new ListaSolicitudesAdapter(MainActivity.this, listSolicitudes);

                lvSolicitudes.setAdapter(listaSolicitudesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}