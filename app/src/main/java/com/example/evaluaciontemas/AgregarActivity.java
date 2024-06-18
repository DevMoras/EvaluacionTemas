package com.example.evaluaciontemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class AgregarActivity extends AppCompatActivity {

    TextView viewFecha, viewCartillaMilitar, viewDocsExtran;
    RadioButton rbMasculino, rbFemenino, rbNoLicencia, rbSiLicencia, rbNoEnfermedad, rbSiEnfermedad, rbNoDeporte, rbPadres, rbFamilia, rbParientes, rbBueno, rbRegular, rbMalo,
            rbSiDeporte, rbSiHijos, rbNoHijos, rbMexicana, rbExtranjera, rbNoEsposa, rbSiEsposa, rbNoPasaporte, rbSiPasaporte, rbSoltero, rbCasado, rbViveP, rbViveM, rbViveE,
            rbTituloPrimaria, rbTituloSecu, rbTituloPrepa, rbTituloProfesional, rbTituloComercial;
    TextInputLayout tilCartillaMilitar, tilClaveLicencia, tilNumLicencia, tilExpliEnfermedad, tilDeporte, tilHijos, tilPasaporte;
    Spinner cbxCartillaMilitar, cbxDocExtran, cbxPuesto, cbxGrado;
    RelativeLayout rlEsposa, rlEstudios, rlPrimaria, rlSecundaria, rlPrepa, rlProfeional, rlComercial, rlEstuioAct;
    CheckBox cbPrimaria, cbSecundaria, cbPrepa, cbProfesional, cbComercial, cbEstucioAct, cbHijos, cbConyuge, cbPadres, cbOtros;

    TextInputEditText tietSueldoD, tietSueldoA, tietFechaC, tietApellidoP, tietApellidoM, tietNombre, tietEdad, tietDomicilio, tietTelefono, tietLugarNac, tietFechaNac, tietEstatura, tietPeso,
                tietCurp, tietAfore, tietRfc, tietNumSegSoc, tietNumCartilla, tietPasaporte, tietClaveLicencia, tietNumLicencia, tietExpEnfermedad, tietDeporte, tietPasatiempo,
                tietMetaVida, tietNombreP, tietDomicilioP, tietOcupacionP, tietNombreM, tietDomicilioM, tietOcupacionM, tietNombreE, tietDomicilioE, tietOcupacionE, tietHijos,
                tietNombrePrimaria, tietDireccionPrimaria, tietDePrimaria, tietAPrimaria, tietAniosPrimaria, tietNombreSecu, tietDireccionSecu, tietDeSecu, tietASecu, tietAniosSecu,
                tietNombrePrepa, tietDireccionPrepa, tietDePrepa, tietAPrepa, tietAniosPrepa, tietNombreProfesional, tietDireccionProfesional, tietDeProfesional, tietAProfesional,
                tietAniosProfesional, tietNombreComercial, tietDireccionComercial, tietDeComercial, tietAComercial, tietAniosComercial, tietNombreActual,
                tietDeActual, tietAActual, tietCursoActual;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        inicializarFireBase();

        cbxPuesto = findViewById(R.id.cbxPuesto);
        tietSueldoD = findViewById(R.id.tietSueldoD);
        tietSueldoA = findViewById(R.id.tietSueldoA);
        tietFechaC = findViewById(R.id.tietFechaC);

        tietApellidoP = findViewById(R.id.tietApellidoP);
        tietApellidoM = findViewById(R.id.tietApellidoM);
        tietNombre = findViewById(R.id.tietNombre);
        tietEdad = findViewById(R.id.tietEdad);
        tietDomicilio = findViewById(R.id.tietDomicilio);
        tietTelefono = findViewById(R.id.tietTelefono);
        tietLugarNac = findViewById(R.id.tietLugarNac);
        tietFechaNac = findViewById(R.id.tietFechaNac);
        tietEstatura = findViewById(R.id.tietEstatura);
        tietPeso = findViewById(R.id.tietPeso);
        rbCasado = findViewById(R.id.rbCasado);
        rbSoltero = findViewById(R.id.rbSoltero);
        rbPadres = findViewById(R.id.rbPadres);
        rbFamilia = findViewById(R.id.rbFamilia);
        rbParientes = findViewById(R.id.rbParientes);
        cbHijos = findViewById(R.id.cbHijos);
        cbConyuge = findViewById(R.id.cbConyuge);
        cbPadres = findViewById(R.id.cbPadres);
        cbOtros = findViewById(R.id.cbOtros);

        tietCurp = findViewById(R.id.tietCurp);
        tietAfore = findViewById(R.id.tietAfore);
        tietRfc = findViewById(R.id.tietRfc);
        tietNumSegSoc = findViewById(R.id.tietNumSegSoc);
        tietNumCartilla = findViewById(R.id.tietNumCartilla);
        tietPasaporte = findViewById(R.id.tietPasaporte);
        tietClaveLicencia = findViewById(R.id.tietClaveLicencia);
        tietNumLicencia = findViewById(R.id.tietNumLicencia);

        rbRegular = findViewById(R.id.rbRegular);
        rbBueno = findViewById(R.id.rbBueno);
        rbMalo = findViewById(R.id.rbMalo);
        tietExpEnfermedad = findViewById(R.id.tietExpEnfermedad);
        tietDeporte = findViewById(R.id.tietDeporte);
        tietPasatiempo = findViewById(R.id.tietPasatiempo);
        tietMetaVida = findViewById(R.id.tietMetaVida);

        tietNombreP = findViewById(R.id.tietNombreP);
        rbViveP = findViewById(R.id.rbSiViveP);
        tietDomicilioP = findViewById(R.id.tietDomicilioP);
        tietOcupacionP = findViewById(R.id.tietOcupacionP);
        tietNombreM = findViewById(R.id.tietNombreM);
        rbViveM = findViewById(R.id.rbSiViveM);
        tietDomicilioM = findViewById(R.id.tietDomicilioM);
        tietOcupacionM = findViewById(R.id.tietOcupacionM);
        tietNombreE = findViewById(R.id.tietNombreE);
        rbViveE = findViewById(R.id.rbSiViveE);
        tietDomicilioE = findViewById(R.id.tietDomicilioE);
        tietOcupacionE = findViewById(R.id.tietOcupacionE);
        tietHijos = findViewById(R.id.tietHijos);

        tietNombrePrimaria = findViewById(R.id.tietNombrePrimaria);
        tietDireccionPrimaria = findViewById(R.id.tietDireccionPrimaria);
        tietDePrimaria = findViewById(R.id.tietDePrimaria);
        tietAPrimaria = findViewById(R.id.tietAPrimaria);
        tietAniosPrimaria = findViewById(R.id.tietAniosPrimaria);
        rbTituloPrimaria = findViewById(R.id.rbSiTituloPrimaria);
        tietNombreSecu = findViewById(R.id.tietNombreSecu);
        tietDireccionSecu = findViewById(R.id.tietDireccionSecu);
        tietDeSecu = findViewById(R.id.tietDeSecu);
        tietASecu = findViewById(R.id.tietASecu);
        tietAniosSecu = findViewById(R.id.tietAniosSecu);
        rbTituloSecu = findViewById(R.id.rbSiTituloSecundaria);
        tietNombrePrepa = findViewById(R.id.tietNombrePrepa);
        tietDireccionPrepa = findViewById(R.id.tietDireccionPrepa);
        tietDePrepa = findViewById(R.id.tietDePrepa);
        tietAPrepa = findViewById(R.id.tietAPrepa);
        tietAniosPrepa = findViewById(R.id.tietAniosPrepa);
        rbTituloPrepa = findViewById(R.id.rbSiTituloPreparatoria);
        tietNombreProfesional = findViewById(R.id.tietNombreProfesional);
        tietDireccionProfesional = findViewById(R.id.tietDireccionProfesional);
        tietDeProfesional = findViewById(R.id.tietDeProfesional);
        tietAProfesional = findViewById(R.id.tietAProfesional);
        tietAniosProfesional = findViewById(R.id.tietAniosProfesional);
        rbTituloProfesional = findViewById(R.id.rbSiTituloProfesional);
        tietNombreComercial = findViewById(R.id.tietNombreComercial);
        tietDireccionComercial = findViewById(R.id.tietDireccionComercial);
        tietDeComercial = findViewById(R.id.tietDeComercial);
        tietAComercial = findViewById(R.id.tietAComercial);
        tietAniosComercial = findViewById(R.id.tietAniosComercial);
        rbTituloComercial = findViewById(R.id.rbSiTituloComercial);
        tietNombreActual = findViewById(R.id.tietNombreActual);
        tietDeActual = findViewById(R.id.tietDeActual);
        tietAActual = findViewById(R.id.tietAActual);
        tietCursoActual = findViewById(R.id.tietCursoActual);
        cbxGrado = findViewById(R.id.cbxGrado);

        //sustituir = findViewById(R.id.sustituir);


        viewFecha = findViewById(R.id.viewFecha);

        rbMasculino = findViewById(R.id.rbMasculino);
        rbFemenino = findViewById(R.id.rbFemenino);

        viewCartillaMilitar = findViewById(R.id.viewCartillaMilitar);
        cbxCartillaMilitar = findViewById(R.id.cbxCartillaMilitar);
        tilCartillaMilitar = findViewById(R.id.txtCartillaMilitar);

        rbNoPasaporte = findViewById(R.id.rbNoPasaporte);
        rbSiPasaporte = findViewById(R.id.rbSiPasaporte);
        tilPasaporte = findViewById(R.id.txtPasaporte);

        rbNoLicencia = findViewById(R.id.rbNoLicencia);
        rbSiLicencia = findViewById(R.id.rbSiLicencia);
        tilClaveLicencia = findViewById(R.id.txtClaveLicencia);
        tilNumLicencia = findViewById(R.id.txtNumLicencia);

        rbNoEnfermedad = findViewById(R.id.rbNoEnfermedad);
        rbSiEnfermedad = findViewById(R.id.rbSiEnfermedad);
        tilExpliEnfermedad = findViewById(R.id.txtExpliqueEnfermedad);

        rbNoDeporte = findViewById(R.id.rbNoDeporte);
        rbSiDeporte = findViewById(R.id.rbSiDeporte);
        tilDeporte = findViewById(R.id.txtDeporte);

        rbNoHijos = findViewById(R.id.rbNoHijos);
        rbSiHijos = findViewById(R.id.rbSiHijos);
        tilHijos = findViewById(R.id.txtHijos);

        rbMexicana = findViewById(R.id.rbMexicana);
        rbExtranjera = findViewById(R.id.rbExtranjera);

        viewDocsExtran = findViewById(R.id.viewDocsExtran);
        cbxDocExtran = findViewById(R.id.cbxDocExtran);

        rbNoEsposa = findViewById(R.id.rbNoEsposa);
        rbSiEsposa = findViewById(R.id.rbSiEsposa);
        rlEsposa = findViewById(R.id.rlEsposa);

        cbPrimaria = findViewById(R.id.cbPrimaria);
        cbSecundaria = findViewById(R.id.cbSecundaria);
        cbPrepa = findViewById(R.id.cbPrepa);
        cbProfesional = findViewById(R.id.cbProfesional);
        cbComercial = findViewById(R.id.cbComercial);
        cbEstucioAct = findViewById(R.id.cbEstudioAct);
        rlEstudios = findViewById(R.id.rlEstudios);
        rlPrimaria = findViewById(R.id.rlPrimaria);
        rlSecundaria = findViewById(R.id.rlSecundaria);
        rlPrepa = findViewById(R.id.rlPrepa);
        rlProfeional = findViewById(R.id.rlProfesional);
        rlComercial = findViewById(R.id.rlComercial);
        rlEstuioAct = findViewById(R.id.rlEstudiosAct);


        Calendar calendar = Calendar.getInstance();
        long tiempounix = calendar.getTimeInMillis();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        String fecha = sdf.format(tiempounix);

        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yy");
        viewFecha.setText(fecha);

        // CODIGO DE OPCIONES DE SEXO PARA CARTILLA MILITAR
        rbFemenino.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbFemenino.isChecked()){
                    mostrarCartilla();
                }
            }
        });

        rbMasculino.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbMasculino.isChecked()){
                    mostrarCartilla();
                }
            }
        });

        // CODIGO DE OPCIONES DE NACIONALIDAD
        viewDocsExtran.setVisibility(View.GONE);
        cbxDocExtran.setVisibility(View.GONE);

        rbMexicana.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbMexicana.isChecked()){
                    mostrarCartilla();
                    viewDocsExtran.setVisibility(View.GONE);
                    cbxDocExtran.setVisibility(View.GONE);
                }
            }
        });

        rbExtranjera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbExtranjera.isChecked()){
                    mostrarCartilla();
                    viewDocsExtran.setVisibility(View.VISIBLE);
                    cbxDocExtran.setVisibility(View.VISIBLE);
                    ((Spinner) findViewById(R.id.cbxDocExtran)).setSelection(0);
                }
            }
        });

        // CODIGO DE OPCIONES DE PASAPORTE
        tilPasaporte.setVisibility(View.GONE);

        rbNoPasaporte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbNoPasaporte.isChecked()){
                    tilPasaporte.setVisibility(View.GONE);

                    ((TextInputEditText) findViewById(R.id.tietPasaporte)).setText("");
                }
            }
        });

        rbSiPasaporte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbSiPasaporte.isChecked()){
                    tilPasaporte.setVisibility(View.VISIBLE);
                }
            }
        });

        // CODIGO DE OPCIONES DE LICENCIA DE MANEJO
        tilClaveLicencia.setVisibility(View.GONE);
        tilNumLicencia.setVisibility(View.GONE);

        rbNoLicencia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbNoLicencia.isChecked()){
                    tilClaveLicencia.setVisibility(View.GONE);
                    tilNumLicencia.setVisibility(View.GONE);

                    ((TextInputEditText) findViewById(R.id.tietClaveLicencia)).setText("");
                    ((TextInputEditText) findViewById(R.id.tietNumLicencia)).setText("");
                }
            }
        });

        rbSiLicencia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbSiLicencia.isChecked()){
                    tilClaveLicencia.setVisibility(View.VISIBLE);
                    tilNumLicencia.setVisibility(View.VISIBLE);
                }
            }
        });

        // CODIGO DE OPCIONES DE ENFERMEDAD CÓNICA
        tilExpliEnfermedad.setVisibility(View.GONE);

        rbNoEnfermedad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbNoEnfermedad.isChecked()){
                    tilExpliEnfermedad.setVisibility(View.GONE);

                    ((TextInputEditText) findViewById(R.id.tietExpEnfermedad)).setText("");
                }
            }
        });

        rbSiEnfermedad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbSiEnfermedad.isChecked()){
                    tilExpliEnfermedad.setVisibility(View.VISIBLE);
                }
            }
        });

        // CODIGO DE OPCIONES DE DEPORTE
        tilDeporte.setVisibility(View.GONE);

        rbNoDeporte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbNoDeporte.isChecked()){
                    tilDeporte.setVisibility(View.GONE);

                    ((TextInputEditText) findViewById(R.id.tietDeporte)).setText("");
                }
            }
        });

        rbSiDeporte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbSiDeporte.isChecked()){
                    tilDeporte.setVisibility(View.VISIBLE);
                }
            }
        });

        // CODIGO DE OPCIONES DE HIJOS
        tilHijos.setVisibility(View.GONE);

        rbNoHijos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbNoHijos.isChecked()){
                    tilHijos.setVisibility(View.GONE);

                    ((TextInputEditText) findViewById(R.id.tietHijos)).setText("");
                }
            }
        });

        rbSiHijos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbSiHijos.isChecked()){
                    tilHijos.setVisibility(View.VISIBLE);
                }
            }
        });

        // CODIGO DE OPCIONES DE ESPOSA
        rlEsposa.setVisibility(View.GONE);

        rbNoEsposa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbNoEsposa.isChecked()){
                    rlEsposa.setVisibility(View.GONE);

                    tietNombreE.setText("");
                    tietDomicilioE.setText("");
                    tietOcupacionE.setText("");
                    ((RadioButton) findViewById(R.id.rbNoViveE)).setChecked(true);
                }
            }
        });

        rbSiEsposa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rbSiEsposa.isChecked()){
                    rlEsposa.setVisibility(View.VISIBLE);
                }
            }
        });

        // CODIGO DE ESCOLARIDAD
        rlPrimaria.setVisibility(View.GONE);
        rlSecundaria.setVisibility(View.GONE);
        rlPrepa.setVisibility(View.GONE);
        rlProfeional.setVisibility(View.GONE);
        rlComercial.setVisibility(View.GONE);
        rlEstuioAct.setVisibility(View.GONE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guardar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuGuardar) {
            if (validarCampos()){
                Solicitud s = new Solicitud();

                //********** SOLICITUD DE EMPLEO **********
                s.setIdSolicitud(UUID.randomUUID().toString());
                s.setPuesto(cbxPuesto.getSelectedItem().toString());
                s.setFecha(viewFecha.getText().toString());
                s.setSueldoDeseado(tietSueldoD.getText().toString());
                s.setSueldoAprobado(String.valueOf((int) Math.floor(Math.random()*15000+5000)));
                String mes = String.valueOf((int) Math.floor(Math.random()*1+12));
                String dia = String.valueOf((int) Math.floor(Math.random()*1+31));
                s.setFechaContratacion("2023" + "-" + mes + "-" + dia);
                //********** DATOS PERSONALES **********
                s.setApellidoP(tietApellidoP.getText().toString());
                s.setApellidoM(tietApellidoM.getText().toString());
                s.setNombre(tietNombre.getText().toString());
                s.setEdad(Integer.parseInt(tietEdad.getText().toString()));
                s.setDomicilio(tietDomicilio.getText().toString());
                s.setTelefono(tietTelefono.getText().toString());
                s.setLugarNac(tietLugarNac.getText().toString());
                s.setFechaNac(tietFechaNac.getText().toString());
                s.setEstatura(tietEstatura.getText().toString());
                s.setPeso(tietPeso.getText().toString());
                if (rbMexicana.isChecked()){
                    s.setNacionalidad("Mexicana");
                } else {
                    s.setNacionalidad("Extranjera");
                }
                if (rbMasculino.isChecked()){
                    s.setSexo("Masculino");
                } else {
                    s.setSexo("Femenino");
                }
                if (rbSoltero.isChecked()){
                    s.setEstadoCivil("Soltero");
                } else if(rbCasado.isChecked()){
                    s.setEstadoCivil("Casado");
                } else {
                    s.setEstadoCivil("Otro");
                }
                if(rbPadres.isChecked()){
                    s.setViveCon("Padres");
                } else if(rbFamilia.isChecked()){
                    s.setViveCon("Familia");
                } else if(rbParientes.isChecked()){
                    s.setViveCon("Parientes");
                } else {
                    s.setViveCon("Solo");
                }
                if(cbHijos.isChecked()){
                    s.setDepenHijos(1);
                }else{
                    s.setDepenHijos(0);
                }
                if(cbConyuge.isChecked()){
                    s.setDepenConyue(1);
                }else{
                    s.setDepenConyue(0);
                }
                if(cbPadres.isChecked()){
                    s.setDepenPadres(1);
                }else{
                    s.setDepenPadres(0);
                }
                if(cbOtros.isChecked()){
                    s.setDepenOtros(1);
                }else{
                    s.setDepenOtros(0);
                }
                //********** DOCUMENTACION **********
                s.setCurp(tietCurp.getText().toString());
                s.setAfore(tietAfore.getText().toString());
                s.setRfc(tietRfc.getText().toString());
                s.setSeguridadSocial(tietNumSegSoc.getText().toString());
                if(tilCartillaMilitar.getVisibility() == View.VISIBLE){
                    s.setLetraCartilla(cbxCartillaMilitar.getSelectedItem().toString());
                    s.setNumCartilla(tietNumCartilla.getText().toString());
                } else {
                    s.setLetraCartilla("");
                    s.setNumCartilla("");
                }
                if(tilPasaporte.getVisibility() == View.VISIBLE){
                    s.setPasaporte(tietPasaporte.getText().toString());
                } else {
                    s.setPasaporte("");
                }
                if(tilClaveLicencia.getVisibility() == View.VISIBLE){
                    s.setClaveLicencia(tietClaveLicencia.getText().toString());
                    s.setNumLicencia(tietNumLicencia.getText().toString());
                } else {
                    s.setClaveLicencia("");
                    s.setNumLicencia("");
                }
                if(cbxDocExtran.getVisibility() == View.VISIBLE){
                    s.setDocExtranjero(cbxDocExtran.getSelectedItem().toString());
                } else {
                    s.setDocExtranjero("");
                }
                //********** ESTADO DE SALUD Y HABITOS PERSONALES **********
                if(rbBueno.isChecked()){
                    s.setEstadoSalud("Bueno");
                } else if(rbRegular.isChecked()){
                    s.setEstadoSalud("Regular");
                } else if(rbMalo.isChecked()){
                    s.setEstadoSalud("Malo");
                }
                if(tilExpliEnfermedad.getVisibility() == View.VISIBLE){
                    s.setExpEnfermedad(tietExpEnfermedad.getText().toString());
                } else {
                    s.setExpEnfermedad("");
                }
                if(tilDeporte.getVisibility() == View.VISIBLE){
                    s.setDeporte(tietDeporte.getText().toString());
                } else {
                    s.setDeporte("");
                }
                if(rbNoDeporte.isChecked()){
                    s.setClubSocial("No");
                }else{
                    s.setClubSocial("Si");
                }
                s.setPasatiempo(tietPasatiempo.getText().toString());
                s.setMetaVida(tietMetaVida.getText().toString());
                //ESTADO DE SALUD Y HABITOS PERSONALES
                s.setNombrePadre(tietNombreP.getText().toString());
                if(rbViveP.isChecked()){
                    s.setVivePadre("Si");
                } else {
                    s.setVivePadre("No");
                }
                s.setDomicilioPadre(tietDomicilioP.getText().toString());
                s.setOcupacionPadre(tietOcupacionP.getText().toString());
                s.setNombreMadre(tietNombreM.getText().toString());
                if(rbViveM.isChecked()){
                    s.setViveMadre("Si");
                } else {
                    s.setViveMadre("No");
                }
                s.setDomicilioMadre(tietDomicilioM.getText().toString());
                s.setOcupacionMadre(tietOcupacionM.getText().toString());
                if (rbSiEsposa.isChecked()){
                    s.setNombreEsposa(tietNombreE.getText().toString());
                    if(rbViveE.isChecked()){
                        s.setViveEsposa("Si");
                    } else {
                        s.setViveEsposa("No");
                    }
                    s.setDomicilioEsposa(tietDomicilioE.getText().toString());
                    s.setOcupacionEsposa(tietOcupacionE.getText().toString());
                } else {
                    s.setNombreEsposa("");
                    s.setViveEsposa("");
                    s.setDomicilioEsposa("");
                    s.setOcupacionEsposa("");
                }
                if(rbSiHijos.isChecked()){
                    s.setNombreEdadHijos(tietHijos.getText().toString());
                } else {
                    s.setNombreEdadHijos("");
                }
                //********** ESCOLARIDAD **********
                if(cbPrimaria.isChecked()){
                    s.setNombrePrimaria(tietNombrePrimaria.getText().toString());
                    s.setDireccionPrimaria(tietDireccionPrimaria.getText().toString());
                    s.setDePrimaria(tietDePrimaria.getText().toString());
                    s.setaPrimaria(tietAPrimaria.getText().toString());
                    s.setAniosPrimaria(tietAniosPrimaria.getText().toString());
                    if(rbTituloPrimaria.isChecked()){
                        s.setTituloPrimaria("Si");
                    } else {
                        s.setTituloPrimaria("No");
                    }
                } else {
                    s.setNombrePrimaria("");
                    s.setDireccionPrimaria("");
                    s.setDePrimaria("");
                    s.setaPrimaria("");
                    s.setAniosPrimaria("");
                    s.setTituloPrimaria("No");
                }
                if(cbSecundaria.isChecked()){
                    s.setNombreSecundaria(tietNombreSecu.getText().toString());
                    s.setDireccionSecundaria(tietDireccionSecu.getText().toString());
                    s.setDeSecundaria(tietDeSecu.getText().toString());
                    s.setaSecundaria(tietASecu.getText().toString());
                    s.setAniosSecundaria(tietAniosSecu.getText().toString());
                    if(rbTituloSecu.isChecked()){
                        s.setTituloSecundaria("Si");
                    } else {
                        s.setTituloSecundaria("No");
                    }
                } else {
                    s.setNombreSecundaria("");
                    s.setDireccionSecundaria("");
                    s.setDeSecundaria("");
                    s.setaSecundaria("");
                    s.setAniosSecundaria("");
                    s.setTituloSecundaria("No");
                }
                if(cbPrepa.isChecked()){
                    s.setNombrePrepa(tietNombrePrepa.getText().toString());
                    s.setDireccionPrepa(tietDireccionPrepa.getText().toString());
                    s.setDePrepa(tietDePrepa.getText().toString());
                    s.setaPrepa(tietAPrepa.getText().toString());
                    s.setAniosPrepa(tietAniosPrepa.getText().toString());
                    if(rbTituloPrepa.isChecked()){
                        s.setTituloPrepa("Si");
                    } else {
                        s.setTituloPrepa("No");
                    }
                } else {
                    s.setNombrePrepa("");
                    s.setDireccionPrepa("");
                    s.setDePrepa("");
                    s.setaPrepa("");
                    s.setAniosPrepa("");
                    s.setTituloPrepa("No");
                }
                if(cbProfesional.isChecked()){
                    s.setNombreProfesional(tietNombreProfesional.getText().toString());
                    s.setDireccionProfesional(tietDireccionProfesional.getText().toString());
                    s.setDeProfesional(tietDeProfesional.getText().toString());
                    s.setaProfesional(tietAProfesional.getText().toString());
                    s.setAniosProfesional(tietAniosProfesional.getText().toString());
                    if(rbTituloProfesional.isChecked()){
                        s.setTituloProfesional("Si");
                    } else {
                        s.setTituloProfesional("No");
                    }
                } else {
                    s.setNombreProfesional("");
                    s.setDireccionProfesional("");
                    s.setDeProfesional("");
                    s.setaProfesional("");
                    s.setAniosProfesional("");
                    s.setTituloProfesional("No");
                }
                if(cbComercial.isChecked()){
                    s.setNombreComercial(tietNombreComercial.getText().toString());
                    s.setDireccionComercial(tietDireccionComercial.getText().toString());
                    s.setDeComercial(tietDeComercial.getText().toString());
                    s.setaComercial(tietAComercial.getText().toString());
                    s.setAniosComercial(tietAniosComercial.getText().toString());
                    if(rbTituloComercial.isChecked()){
                        s.setTituloComercial("Si");
                    } else {
                        s.setTituloComercial("No");
                    }
                } else {
                    s.setNombreComercial("");
                    s.setDireccionComercial("");
                    s.setDeComercial("");
                    s.setaComercial("");
                    s.setAniosComercial("");
                    s.setTituloComercial("No");
                }
                if(cbEstucioAct.isChecked()){
                    s.setNombreEscAct(tietNombreActual.getText().toString());
                    s.setDeEscAct(tietDeActual.getText().toString());
                    s.setaEscAct(tietAActual.getText().toString());
                    s.setCursoAct(tietCursoActual.getText().toString());
                    s.setGradoAct(cbxGrado.getSelectedItem().toString());
                } else {
                    s.setNombreEscAct("");
                    s.setDeEscAct("");
                    s.setaEscAct("");
                    s.setCursoAct("");
                    s.setGradoAct("");
                }

                databaseReference.child("Solicitudes").child(s.getIdSolicitud()).setValue(s);
                Toast.makeText(this, "Solicitud Guardada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Revise los campos erroneos", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void mostrarCartilla (){
        if (rbMasculino.isChecked() && rbMexicana.isChecked()){
            tilCartillaMilitar.setVisibility(View.VISIBLE);
            viewCartillaMilitar.setVisibility(View.VISIBLE);
            cbxCartillaMilitar.setVisibility(View.VISIBLE);
        } else {
            tilCartillaMilitar.setVisibility(View.GONE);
            viewCartillaMilitar.setVisibility(View.GONE);
            cbxCartillaMilitar.setVisibility(View.GONE);
            tietNumCartilla.setText("");
        }
    }

    public void escuelas(View view) {
        if (cbPrimaria.isChecked()){
            rlPrimaria.setVisibility(View.VISIBLE);
        } else {
            rlPrimaria.setVisibility(View.GONE);
            tietNombrePrimaria.setText("");
            tietDireccionPrimaria.setText("");
            tietDePrimaria.setText("");
            tietAPrimaria.setText("");
            tietAniosPrimaria.setText("");
            ((RadioButton) findViewById(R.id.rbNoTituloPrimaria)).setChecked(true);
        }

        if (cbSecundaria.isChecked()){
            rlSecundaria.setVisibility(View.VISIBLE);
        } else {
            rlSecundaria.setVisibility(View.GONE);
            tietNombreSecu.setText("");
            tietDireccionSecu.setText("");
            tietDeSecu.setText("");
            tietASecu.setText("");
            tietAniosSecu.setText("");
            ((RadioButton) findViewById(R.id.rbNoTituloSecundaria)).setChecked(true);
        }

        if (cbPrepa.isChecked()){
            rlPrepa.setVisibility(View.VISIBLE);
        } else {
            rlPrepa.setVisibility(View.GONE);
            tietNombrePrepa.setText("");
            tietDireccionPrepa.setText("");
            tietDePrepa.setText("");
            tietAPrepa.setText("");
            tietAniosPrepa.setText("");
            ((RadioButton) findViewById(R.id.rbNoTituloPreparatoria)).setChecked(true);
        }

        if (cbProfesional.isChecked()){
            rlProfeional.setVisibility(View.VISIBLE);
        } else {
            rlProfeional.setVisibility(View.GONE);
            tietNombreProfesional.setText("");
            tietDireccionProfesional.setText("");
            tietDeProfesional.setText("");
            tietAProfesional.setText("");
            tietAniosProfesional.setText("");
            ((RadioButton) findViewById(R.id.rbNoTituloProfesional)).setChecked(true);
        }

        if(cbComercial.isChecked()){
            rlComercial.setVisibility(View.VISIBLE);
        } else {
            rlComercial.setVisibility(View.GONE);
            tietNombreComercial.setText("");
            tietDireccionComercial.setText("");
            tietDeComercial.setText("");
            tietAComercial.setText("");
            tietAniosComercial.setText("");
            ((RadioButton) findViewById(R.id.rbNoTituloComercial)).setChecked(true);
        }

        if(cbEstucioAct.isChecked()){
            rlEstuioAct.setVisibility(View.VISIBLE);
        } else {
            rlEstuioAct.setVisibility(View.GONE);
            tietNombreActual.setText("");
            tietDeActual.setText("");
            tietAActual.setText("");
            tietCursoActual.setText("");
            ((Spinner) findViewById(R.id.cbxGrado)).setSelection(0);
        }
    }

    public void showError(EditText input, String s){
        input.setError(s);
    }

    private boolean validarCampos(){
        boolean res = true;
        // SOLICITUD DE EMPLEO
        if(tietSueldoD.getText().toString().isEmpty()){
            showError(tietSueldoD, "Rellene este campo");
            res = false;
        }

        // DATOS PERSONALES
        if(tietApellidoP.getText().toString().isEmpty()){
            showError(tietApellidoP, "Rellene este campo");
            res = false;
        }
        if(tietApellidoM.getText().toString().isEmpty()){
            showError(tietApellidoM, "Rellene este campo");
            res = false;
        }
        if(tietNombre.getText().toString().isEmpty()){
            showError(tietNombre, "Rellene este campo");
            res = false;
        }
        if(tietEdad.getText().toString().isEmpty()){
            showError(tietEdad, "Rellene este campo");
            res = false;
        } else if(Integer.parseInt(tietEdad.getText().toString()) < 18){
            showError(tietEdad, "Debe ser mayor de edad");
            res = false;
        }
        if(tietDomicilio.getText().toString().isEmpty()){
            showError(tietDomicilio, "Rellene este campo");
            res = false;
        }
        if(tietTelefono.getText().toString().isEmpty()){
            showError(tietTelefono, "Rellene este campo");
            res = false;
        } else if (tietTelefono.length() < 10){
            showError(tietTelefono, "Debe contener 10 digitos");
            res = false;
        }
        if(tietLugarNac.getText().toString().isEmpty()){
            showError(tietLugarNac, "Rellene este campo");
            res = false;
        }
        if(tietEstatura.getText().toString().isEmpty()){
            showError(tietEstatura, "Rellene este campo");
            res = false;
        }
        if(tietPeso.getText().toString().isEmpty()){
            showError(tietPeso, "Rellene este campo");
            res = false;
        }

        // DOCUMENTACION
        if(tietCurp.getText().toString().isEmpty()){
            showError(tietCurp, "Rellene este campo");
            res = false;
        } else if (tietCurp.length() < 18){
            showError(tietCurp, "Debe contener 18 caracteres");
            res = false;
        }
        if(tietAfore.getText().toString().isEmpty()){
            showError(tietAfore, "Rellene este campo");
            res = false;
        }
        if(tietNumSegSoc.getText().toString().isEmpty()){
            showError(tietNumSegSoc, "Rellene este campo");
            res = false;
        }
        if(tilCartillaMilitar.getVisibility() == View.VISIBLE){
            if(tietNumCartilla.getText().toString().isEmpty()){
                showError(tietNumCartilla, "Rellene este campo");
                res = false;
            } else if(tietNumCartilla.length() < 7){
                showError(tietNumCartilla, "Faltan digitos");
                res = false;
            }
        }
        if(tilPasaporte.getVisibility() == View.VISIBLE){
            if(tietPasaporte.getText().toString().isEmpty()){
                showError(tietPasaporte, "Rellene este campo");
                res = false;
            }
        }
        if(tilClaveLicencia.getVisibility() == View.VISIBLE){
            if(tietClaveLicencia.getText().toString().isEmpty()){
                showError(tietClaveLicencia, "Rellene este campo");
                res = false;
            }
            if(tietNumLicencia.getText().toString().isEmpty()){
                showError(tietNumLicencia, "Rellene este campo");
                res = false;
            }
        }

        // ESTADO DE SALUD Y HABITOS PERSONALES
        if(tilExpliEnfermedad.getVisibility() == View.VISIBLE){
            if(tietExpEnfermedad.getText().toString().isEmpty()){
                showError(tietExpEnfermedad, "Rellene este campo");
                res = false;
            }
        }
        if(tilDeporte.getVisibility() == View.VISIBLE){
            if(tietDeporte.getText().toString().isEmpty()){
                showError(tietDeporte, "Rellene este campo");
                res = false;
            }
        }
        if(tietPasatiempo.getText().toString().isEmpty()){
            showError(tietPasatiempo, "Rellene este campo");
            res = false;
        }
        if(tietMetaVida.getText().toString().isEmpty()){
            showError(tietMetaVida, "Rellene este campo");
            res = false;
        }

        // DATOS FAMILIARES
        if(tietNombreP.getText().toString().isEmpty()){
            showError(tietNombreP, "Rellene este campo");
            res = false;
        }
        if(tietDomicilioP.getText().toString().isEmpty()){
            showError(tietDomicilioP, "Rellene este campo");
            res = false;
        }
        if(tietOcupacionP.getText().toString().isEmpty()){
            showError(tietOcupacionP, "Rellene este campo");
            res = false;
        }
        if(tietNombreM.getText().toString().isEmpty()){
            showError(tietNombreM, "Rellene este campo");
            res = false;
        }
        if(tietDomicilioM.getText().toString().isEmpty()){
            showError(tietDomicilioM, "Rellene este campo");
            res = false;
        }
        if(tietOcupacionM.getText().toString().isEmpty()){
            showError(tietOcupacionM, "Rellene este campo");
            res = false;
        }
        if(rlEsposa.getVisibility() == View.VISIBLE){
            if(tietNombreE.getText().toString().isEmpty()){
                showError(tietNombreE, "Rellene este campo");
                res = false;
            }
            if(tietDomicilioE.getText().toString().isEmpty()){
                showError(tietDomicilioE, "Rellene este campo");
                res = false;
            }
            if(tietOcupacionM.getText().toString().isEmpty()){
                showError(tietOcupacionE, "Rellene este campo");
                res = false;
            }
        }
        if(tilHijos.getVisibility() == View.VISIBLE){
            if(tietHijos.getText().toString().isEmpty()){
                showError(tietHijos, "Rellene este campo");
                res = false;
            }
        }
        if(rlPrimaria.getVisibility() == View.VISIBLE){
            if(tietNombrePrimaria.getText().toString().isEmpty()){
                showError(tietNombrePrimaria, "Rellene este campo");
                res = false;
            }
            if(tietDireccionPrimaria.getText().toString().isEmpty()){
                showError(tietDireccionPrimaria, "Rellene este campo");
                res = false;
            }
            if(tietDePrimaria.getText().toString().isEmpty()){
                showError(tietDePrimaria, "Rellene este campo");
                res = false;
            }
            if(tietAPrimaria.getText().toString().isEmpty()){
                showError(tietAPrimaria, "Rellene este campo");
                res = false;
            }
            if(tietAniosPrimaria.getText().toString().isEmpty()){
                showError(tietAniosPrimaria, "Rellene este campo");
                res = false;
            }
        }

        if(rlSecundaria.getVisibility() == View.VISIBLE){
            if(tietNombreSecu.getText().toString().isEmpty()){
                showError(tietNombreSecu, "Rellene este campo");
                res = false;
            }
            if(tietDireccionSecu.getText().toString().isEmpty()){
                showError(tietDireccionSecu, "Rellene este campo");
                res = false;
            }
            if(tietDeSecu.getText().toString().isEmpty()){
                showError(tietDeSecu, "Rellene este campo");
                res = false;
            }
            if(tietASecu.getText().toString().isEmpty()){
                showError(tietASecu, "Rellene este campo");
                res = false;
            }
            if(tietAniosSecu.getText().toString().isEmpty()){
                showError(tietAniosSecu, "Rellene este campo");
                res = false;
            }
        }

        if(rlPrepa.getVisibility() == View.VISIBLE){
            if(tietNombrePrepa.getText().toString().isEmpty()){
                showError(tietNombrePrepa, "Rellene este campo");
                res = false;
            }
            if(tietDireccionPrepa.getText().toString().isEmpty()){
                showError(tietDireccionPrepa, "Rellene este campo");
                res = false;
            }
            if(tietDePrepa.getText().toString().isEmpty()){
                showError(tietDePrepa, "Rellene este campo");
                res = false;
            }
            if(tietAPrepa.getText().toString().isEmpty()){
                showError(tietAPrepa, "Rellene este campo");
                res = false;
            }
            if(tietAniosPrepa.getText().toString().isEmpty()){
                showError(tietAniosPrepa, "Rellene este campo");
                res = false;
            }
        }

        if(rlProfeional.getVisibility() == View.VISIBLE){
            if(tietNombreProfesional.getText().toString().isEmpty()){
                showError(tietNombreProfesional, "Rellene este campo");
                res = false;
            }
            if(tietDireccionProfesional.getText().toString().isEmpty()){
                showError(tietDireccionProfesional, "Rellene este campo");
                res = false;
            }
            if(tietDeProfesional.getText().toString().isEmpty()){
                showError(tietDeProfesional, "Rellene este campo");
                res = false;
            }
            if(tietAProfesional.getText().toString().isEmpty()){
                showError(tietAProfesional, "Rellene este campo");
                res = false;
            }
            if(tietAniosProfesional.getText().toString().isEmpty()){
                showError(tietAniosProfesional, "Rellene este campo");
                res = false;
            }
        }

        if(rlComercial.getVisibility() == View.VISIBLE){
            if(tietNombreComercial.getText().toString().isEmpty()){
                showError(tietNombreComercial, "Rellene este campo");
                res = false;
            }
            if(tietDireccionComercial.getText().toString().isEmpty()){
                showError(tietDireccionComercial, "Rellene este campo");
                res = false;
            }
            if(tietDeComercial.getText().toString().isEmpty()){
                showError(tietDeComercial, "Rellene este campo");
                res = false;
            }
            if(tietAComercial.getText().toString().isEmpty()){
                showError(tietAComercial, "Rellene este campo");
                res = false;
            }
            if(tietAniosComercial.getText().toString().isEmpty()){
                showError(tietAniosComercial, "Rellene este campo");
                res = false;
            }
        }

        if(rlEstuioAct.getVisibility() == View.VISIBLE){
            if(tietNombreActual.getText().toString().isEmpty()){
                showError(tietNombreActual, "Rellene este campo");
                res = false;
            }
            if(tietDeActual.getText().toString().isEmpty()){
                showError(tietDeActual, "Rellene este campo");
                res = false;
            }
            if(tietAActual.getText().toString().isEmpty()){
                showError(tietAActual, "Rellene este campo");
                res = false;
            }
            if(tietCursoActual.getText().toString().isEmpty()){
                showError(tietCursoActual, "Rellene este campo");
                res = false;
            }
        }

        return res;
    }
}