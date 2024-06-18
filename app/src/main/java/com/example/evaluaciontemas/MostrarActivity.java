package com.example.evaluaciontemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
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

public class MostrarActivity extends AppCompatActivity {

    TextView viewFecha, viewCartillaMilitar, viewDocsExtran;
    RadioButton rbMasculino, rbFemenino, rbNoLicencia, rbSiLicencia, rbNoEnfermedad, rbSiEnfermedad, rbNoDeporte, rbPadres, rbFamilia, rbParientes, rbSolo, rbBueno, rbRegular, rbMalo,
            rbSiDeporte, rbSiHijos, rbNoHijos, rbMexicana, rbExtranjera, rbNoEsposa, rbSiEsposa, rbNoPasaporte, rbSiPasaporte, rbSoltero, rbCasado, rbOtro, rbViveP, rbViveM, rbViveE,
            rbTituloPrimaria, rbTituloSecu, rbTituloPrepa, rbTituloProfesional, rbTituloComercial, rbSiClubSocial, rbNoClubSocial, rbNoViveP, rbNoViveM, rbNoViveE, rbNoTituloPrimaria,
            rbNoTituloSecu, rbNoTituloPrepa, rbNoTituloProfeional, rbNoTituloComercial;

    TextInputLayout tilCartillaMilitar, tilClaveLicencia, tilNumLicencia, tilExpliEnfermedad, tilDeporte, tilHijos, tilPasaporte;
    Spinner cbxCartillaMilitar, cbxDocExtran, cbxPuesto, cbxGrado;
    RelativeLayout rlEsposa, rlEstudios, rlPrimaria, rlSecundaria, rlPrepa, rlProfeional, rlComercial, rlEstudioAct;
    CheckBox cbPrimaria, cbSecundaria, cbPrepa, cbProfesional, cbComercial, cbEstudioAct, cbHijos, cbConyuge, cbPadres, cbOtros;

    TextInputEditText tietSueldoD, tietSueldoA, tietFechaC, tietApellidoP, tietApellidoM, tietNombre, tietEdad, tietDomicilio, tietTelefono, tietLugarNac, tietFechaNac, tietEstatura, tietPeso,
            tietCurp, tietAfore, tietRfc, tietNumSegSoc, tietNumCartilla, tietPasaporte, tietClaveLicencia, tietNumLicencia, tietExpEnfermedad, tietDeporte, tietPasatiempo,
            tietMetaVida, tietNombreP, tietDomicilioP, tietOcupacionP, tietNombreM, tietDomicilioM, tietOcupacionM, tietNombreE, tietDomicilioE, tietOcupacionE, tietHijos,
            tietNombrePrimaria, tietDireccionPrimaria, tietDePrimaria, tietAPrimaria, tietAniosPrimaria, tietNombreSecu, tietDireccionSecu, tietDeSecu, tietASecu, tietAniosSecu,
            tietNombrePrepa, tietDireccionPrepa, tietDePrepa, tietAPrepa, tietAniosPrepa, tietNombreProfesional, tietDireccionProfesional, tietDeProfesional, tietAProfesional,
            tietAniosProfesional, tietNombreComercial, tietDireccionComercial, tietDeComercial, tietAComercial, tietAniosComercial, tietNombreActual,
            tietDeActual, tietAActual, tietCursoActual;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        inicializarFireBase();
        identificarId();
        quitarEdicion();

        Solicitud s = recibirSolicitud();

        //********** SOLICITUD DE EMPLEO **********
        switch (s.getPuesto()) {
            case "Gerente":
                cbxPuesto.setSelection(0);
                break;
            case "Administrador":
                cbxPuesto.setSelection(1);
                break;
            case "Contador":
                cbxPuesto.setSelection(2);
                break;
            case "Desarrollador":
                cbxPuesto.setSelection(3);
                break;
            case "Jefe de proyectos":
                cbxPuesto.setSelection(4);
                break;
            case "Otro":
                cbxPuesto.setSelection(5);
                break;
        }
        viewFecha.setText(s.getFecha());
        tietSueldoD.setText(s.getSueldoDeseado());
        tietSueldoA.setText(s.getSueldoAprobado());
        tietFechaC.setText(s.getFechaContratacion());
        //********** DATOS PERSONALES **********
        tietApellidoP.setText(s.getApellidoP());
        tietApellidoM.setText(s.getApellidoM());
        tietNombre.setText(s.getNombre());
        tietEdad.setText(String.valueOf(s.getEdad()));
        tietDomicilio.setText(s.getDomicilio());
        tietTelefono.setText(s.getTelefono());
        tietLugarNac.setText(s.getLugarNac());
        tietFechaNac.setText(s.getFechaNac());
        tietEstatura.setText(s.getEstatura());
        tietPeso.setText(s.getPeso());
        if(s.getNacionalidad().equals("Extranjera")){
            rbExtranjera.setChecked(true);
            viewCartillaMilitar.setVisibility(View.GONE);
            tilCartillaMilitar.setVisibility(View.GONE);
            cbxCartillaMilitar.setVisibility(View.GONE);
            switch (s.getDocExtranjero()) {
                case "Visa de residente temporal por solvencia económica":
                    cbxDocExtran.setSelection(0);
                    break;
                case "Visa de residente temporal por oferta de empleo":
                    cbxDocExtran.setSelection(1);
                    break;
                case "Documento de empleador de extranjero":
                    cbxDocExtran.setSelection(2);
                    break;
                case "Carta de contratación laboral":
                    cbxDocExtran.setSelection(3);
                    break;
                case "Permiso migratorio":
                    cbxDocExtran.setSelection(4);
                    break;
            }
        } else {
            rbMexicana.setChecked(true);
            cbxDocExtran.setVisibility(View.GONE);
            viewDocsExtran.setVisibility(View.GONE);
            if (s.getSexo().equals("Masculino")){
                rbMasculino.setChecked(true);
                if(s.getLetraCartilla().equals("C-")){
                    cbxCartillaMilitar.setSelection(0);
                } else {
                    cbxCartillaMilitar.setSelection(1);
                }
                tietNumCartilla.setText(String.valueOf(s.getNumCartilla()));
            } else {
                rbFemenino.setChecked(true);
                viewCartillaMilitar.setVisibility(View.GONE);
                tilCartillaMilitar.setVisibility(View.GONE);
                cbxCartillaMilitar.setVisibility(View.GONE);
            }
        }
        if(s.getEstadoCivil().equals("Soltero")){
            rbSoltero.setChecked(true);
        } else if(s.getEstadoCivil().equals("Casado")){
            rbCasado.setChecked(true);
        } else {
            rbOtro.setChecked(true);
        }
        switch (s.getViveCon()) {
            case "Padres":
                rbPadres.setChecked(true);
                break;
            case "Familia":
                rbFamilia.setChecked(true);
                break;
            case "Parientes":
                rbParientes.setChecked(true);
                break;
            default:
                rbSolo.setChecked(true);
                break;
        }
        if(s.getDepenHijos() == 1){
            cbHijos.setChecked(true);
        }
        if(s.getDepenConyue() == 1){
            cbConyuge.setChecked(true);
        }
        if(s.getDepenPadres() == 1){
            cbPadres.setChecked(true);
        }
        if(s.getDepenOtros() == 1){
            cbOtros.setChecked(true);
        }
        //********** DOCUMENTACION **********
        tietCurp.setText(s.getCurp());
        tietAfore.setText(s.getAfore());
        tietRfc.setText(s.getRfc());
        tietNumSegSoc.setText(s.getSeguridadSocial());
        if(s.getPasaporte().isEmpty()){
            rbNoPasaporte.setChecked(true);
            tilPasaporte.setVisibility(View.GONE);
        }else{
            rbSiPasaporte.setChecked(true);
            tietPasaporte.setText(s.getPasaporte());
        }if(s.getClaveLicencia().isEmpty()){
            rbNoLicencia.setChecked(true);
            tilClaveLicencia.setVisibility(View.GONE);
            tilNumLicencia.setVisibility(View.GONE);
        } else {
            rbSiLicencia.setChecked(true);
            tietClaveLicencia.setText(s.getClaveLicencia());
            tietNumLicencia.setText(s.getNumLicencia());
        }
        //********** ESTADO DE SAUD Y HABITOS PERSONALES **********
        if(s.getEstadoSalud().equals("Bueno")){
            rbBueno.setChecked(true);
        } else if (s.getEstadoSalud().equals("Regular")){
            rbRegular.setChecked(true);
        } else {
            rbMalo.setChecked(true);
        }
        if (s.getExpEnfermedad().isEmpty()){
            rbNoEnfermedad.setChecked(true);
            tilExpliEnfermedad.setVisibility(View.GONE);
        } else {
            rbSiEnfermedad.setChecked(true);
            tietExpEnfermedad.setText(s.getExpEnfermedad());
        }
        if(s.getDeporte().isEmpty()){
            rbNoDeporte.setChecked(true);
            tilDeporte.setVisibility(View.GONE);
        } else {
            rbSiDeporte.setChecked(true);
            tietDeporte.setText(s.getDeporte());
        }
        if(s.getClubSocial().equals("Si")){
            rbSiClubSocial.setChecked(true);
        } else {
            rbNoClubSocial.setChecked(true);
        }
        tietPasatiempo.setText(s.getPasatiempo());
        tietMetaVida.setText(s.getMetaVida());
        //********** DATOS FAMILIARES **********
        tietNombreP.setText(s.getNombrePadre());
        if(s.getVivePadre().equals("Si")){
            rbViveP.setChecked(true);
        } else {
            rbNoViveP.setChecked(true);
        }
        tietDomicilioP.setText(s.getDomicilioPadre());
        tietOcupacionP.setText(s.getOcupacionPadre());
        tietNombreM.setText(s.getNombreMadre());
        if(s.getViveMadre().equals("Si")){
            rbViveM.setChecked(true);
        } else {
            rbNoViveM.setChecked(true);
        }
        tietDomicilioM.setText(s.getDomicilioMadre());
        tietOcupacionM.setText(s.getOcupacionMadre());
        if(s.getNombreEsposa().isEmpty()){
            rbNoEsposa.setChecked(true);
            rlEsposa.setVisibility(View.GONE);
        } else {
            rbSiEsposa.setChecked(true);
            tietNombreE.setText(s.getNombreEsposa());
            if(s.getViveEsposa().equals("Si")){
                rbViveE.setChecked(true);
            } else {
                rbNoViveE.setChecked(true);
            }
            tietDomicilioE.setText(s.getDomicilioEsposa());
            tietOcupacionE.setText(s.getOcupacionEsposa());
        }
        if(s.getNombreEdadHijos().isEmpty()){
            rbNoHijos.setChecked(true);
            tilHijos.setVisibility(View.GONE);
        } else {
            rbSiHijos.setChecked(true);
            tietHijos.setText(s.getNombreEdadHijos());
        }
        //********** ESCOLARIDAD **********
        if (s.getNombrePrimaria().isEmpty()) {
            rlPrimaria.setVisibility(View.GONE);
        } else {
            cbPrimaria.setChecked(true);
            tietNombrePrimaria.setText(s.getNombrePrimaria());
            tietDireccionPrimaria.setText(s.getDireccionPrimaria());
            tietDePrimaria.setText(s.getDePrimaria());
            tietAPrimaria.setText(s.getaPrimaria());
            tietAniosPrimaria.setText(s.getAniosPrimaria());
            if (s.getTituloPrimaria().equals("Si")) {
                rbTituloPrimaria.setChecked(true);
            } else {
                rbNoTituloPrimaria.setChecked(true);
            }
        }
        if (s.getNombreSecundaria().isEmpty()) {
            rlSecundaria.setVisibility(View.GONE);
        } else {
            cbSecundaria.setChecked(true);
            tietNombreSecu.setText(s.getNombreSecundaria());
            tietDireccionSecu.setText(s.getDireccionSecundaria());
            tietDeSecu.setText(s.getDeSecundaria());
            tietASecu.setText(s.getaSecundaria());
            tietAniosSecu.setText(s.getAniosSecundaria());
            if (s.getTituloSecundaria().equals("Si")) {
                rbTituloSecu.setChecked(true);
            } else {
                rbNoTituloSecu.setChecked(true);
            }
        }
        if (s.getNombrePrepa().isEmpty()) {
            rlPrepa.setVisibility(View.GONE);
        } else {
            cbPrepa.setChecked(true);
            tietNombrePrepa.setText(s.getNombrePrepa());
            tietDireccionPrepa.setText(s.getDireccionPrepa());
            tietDePrepa.setText(s.getDePrepa());
            tietAPrepa.setText(s.getaPrepa());
            tietAniosPrepa.setText(s.getAniosPrepa());
            if (s.getTituloPrepa().equals("Si")) {
                rbTituloPrepa.setChecked(true);
            } else {
                rbNoTituloPrepa.setChecked(true);
            }
        }
        if (s.getNombreProfesional().isEmpty()) {
            rlProfeional.setVisibility(View.GONE);
        } else {
            cbProfesional.setChecked(true);
            tietNombreProfesional.setText(s.getNombreProfesional());
            tietDireccionProfesional.setText(s.getDireccionProfesional());
            tietDeProfesional.setText(s.getDeProfesional());
            tietAProfesional.setText(s.getaProfesional());
            tietAniosProfesional.setText(s.getAniosProfesional());
            if (s.getTituloProfesional().equals("Si")) {
                rbTituloProfesional.setChecked(true);
            } else {
                rbNoTituloProfeional.setChecked(true);
            }
        }
        if (s.getNombreComercial().isEmpty()) {
            rlComercial.setVisibility(View.GONE);
        } else {
            cbComercial.setChecked(true);
            tietNombreComercial.setText(s.getNombreComercial());
            tietDireccionComercial.setText(s.getDireccionComercial());
            tietDeComercial.setText(s.getDeComercial());
            tietAComercial.setText(s.getaComercial());
            tietAniosComercial.setText(s.getAniosComercial());
            if (s.getTituloComercial().equals("Si")) {
                rbTituloComercial.setChecked(true);
            } else {
                rbNoTituloComercial.setChecked(true);
            }
        }
        if (s.getNombreEscAct().isEmpty()) {
            rlEstudioAct.setVisibility(View.GONE);
        } else {
            cbEstudioAct.setChecked(true);
            tietNombreActual.setText(s.getNombreEscAct());
            tietDeActual.setText(s.getDeEscAct());
            tietAActual.setText(s.getaEscAct());
            tietCursoActual.setText(s.getCursoAct());
            switch (s.getGradoAct()) {
                case "1°":
                    cbxGrado.setSelection(0);
                    break;
                case "2°":
                    cbxGrado.setSelection(1);
                    break;
                case "3°":
                    cbxGrado.setSelection(2);
                    break;
                case "4°":
                    cbxGrado.setSelection(3);
                    break;
                case "5°":
                    cbxGrado.setSelection(4);
                    break;
                case "6°":
                    cbxGrado.setSelection(5);
                    break;
                case "7°":
                    cbxGrado.setSelection(6);
                    break;
                case "8°":
                    cbxGrado.setSelection(7);
                    break;
                case "9°":
                    cbxGrado.setSelection(8);
                    break;
                case "10°":
                    cbxGrado.setSelection(9);
                    break;
                case "11°":
                    cbxGrado.setSelection(10);
                    break;
                case "12°":
                    cbxGrado.setSelection(11);
                    break;
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_eliminar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuEliminar) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MostrarActivity.this);
            builder.setMessage("¿Desea eliminar esta solicitud?")
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Solicitud s = recibirSolicitud();
                            s.setIdSolicitud(s.getIdSolicitud());

                            databaseReference.child("Solicitudes").child(s.getIdSolicitud()).removeValue();

                            Toast.makeText(MostrarActivity.this, "Solicitud Eliminada", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
            }).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private Solicitud recibirSolicitud(){
        Bundle extra = getIntent().getExtras();
        Solicitud solicitudSeleccionada = null;
        if(extra != null){
            solicitudSeleccionada = (Solicitud) extra.getSerializable("solicitudSeleccionada");
        }
        return solicitudSeleccionada;
    }

    private void identificarId(){
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
        rbOtro = findViewById(R.id.rbOtro);
        rbPadres = findViewById(R.id.rbPadres);
        rbFamilia = findViewById(R.id.rbFamilia);
        rbParientes = findViewById(R.id.rbParientes);
        rbSolo = findViewById(R.id.rbSolo);
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
        rbSiClubSocial = findViewById(R.id.rbSiClub);
        rbNoClubSocial = findViewById(R.id.rbNoClub);
        tietDeporte = findViewById(R.id.tietDeporte);
        tietPasatiempo = findViewById(R.id.tietPasatiempo);
        tietMetaVida = findViewById(R.id.tietMetaVida);

        tietNombreP = findViewById(R.id.tietNombreP);
        rbViveP = findViewById(R.id.rbSiViveP);
        rbNoViveP = findViewById(R.id.rbNoViveP);
        tietDomicilioP = findViewById(R.id.tietDomicilioP);
        tietOcupacionP = findViewById(R.id.tietOcupacionP);
        tietNombreM = findViewById(R.id.tietNombreM);
        rbViveM = findViewById(R.id.rbSiViveM);
        rbNoViveM = findViewById(R.id.rbNoViveM);
        tietDomicilioM = findViewById(R.id.tietDomicilioM);
        tietOcupacionM = findViewById(R.id.tietOcupacionM);
        tietNombreE = findViewById(R.id.tietNombreE);
        rbViveE = findViewById(R.id.rbSiViveE);
        rbNoViveE = findViewById(R.id.rbNoViveE);
        tietDomicilioE = findViewById(R.id.tietDomicilioE);
        tietOcupacionE = findViewById(R.id.tietOcupacionE);
        tietHijos = findViewById(R.id.tietHijos);

        tietNombrePrimaria = findViewById(R.id.tietNombrePrimaria);
        tietDireccionPrimaria = findViewById(R.id.tietDireccionPrimaria);
        tietDePrimaria = findViewById(R.id.tietDePrimaria);
        tietAPrimaria = findViewById(R.id.tietAPrimaria);
        tietAniosPrimaria = findViewById(R.id.tietAniosPrimaria);
        rbTituloPrimaria = findViewById(R.id.rbSiTituloPrimaria);
        rbNoTituloPrimaria = findViewById(R.id.rbNoTituloPrimaria);
        tietNombreSecu = findViewById(R.id.tietNombreSecu);
        tietDireccionSecu = findViewById(R.id.tietDireccionSecu);
        tietDeSecu = findViewById(R.id.tietDeSecu);
        tietASecu = findViewById(R.id.tietASecu);
        tietAniosSecu = findViewById(R.id.tietAniosSecu);
        rbTituloSecu = findViewById(R.id.rbSiTituloSecundaria);
        rbNoTituloSecu = findViewById(R.id.rbNoTituloSecundaria);
        tietNombrePrepa = findViewById(R.id.tietNombrePrepa);
        tietDireccionPrepa = findViewById(R.id.tietDireccionPrepa);
        tietDePrepa = findViewById(R.id.tietDePrepa);
        tietAPrepa = findViewById(R.id.tietAPrepa);
        tietAniosPrepa = findViewById(R.id.tietAniosPrepa);
        rbTituloPrepa = findViewById(R.id.rbSiTituloPreparatoria);
        rbNoTituloPrepa = findViewById(R.id.rbNoTituloPreparatoria);
        tietNombreProfesional = findViewById(R.id.tietNombreProfesional);
        tietDireccionProfesional = findViewById(R.id.tietDireccionProfesional);
        tietDeProfesional = findViewById(R.id.tietDeProfesional);
        tietAProfesional = findViewById(R.id.tietAProfesional);
        tietAniosProfesional = findViewById(R.id.tietAniosProfesional);
        rbTituloProfesional = findViewById(R.id.rbSiTituloProfesional);
        rbNoTituloProfeional = findViewById(R.id.rbNoTituloProfesional);
        tietNombreComercial = findViewById(R.id.tietNombreComercial);
        tietDireccionComercial = findViewById(R.id.tietDireccionComercial);
        tietDeComercial = findViewById(R.id.tietDeComercial);
        tietAComercial = findViewById(R.id.tietAComercial);
        tietAniosComercial = findViewById(R.id.tietAniosComercial);
        rbTituloComercial = findViewById(R.id.rbSiTituloComercial);
        rbNoTituloComercial = findViewById(R.id.rbNoTituloComercial);
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
        cbEstudioAct = findViewById(R.id.cbEstudioAct);
        rlEstudios = findViewById(R.id.rlEstudios);
        rlPrimaria = findViewById(R.id.rlPrimaria);
        rlSecundaria = findViewById(R.id.rlSecundaria);
        rlPrepa = findViewById(R.id.rlPrepa);
        rlProfeional = findViewById(R.id.rlProfesional);
        rlComercial = findViewById(R.id.rlComercial);
        rlEstudioAct = findViewById(R.id.rlEstudiosAct);
    }

    private void quitarEdicion(){
        cbxPuesto.setEnabled(false);
        tietSueldoD.setEnabled(false);
        tietSueldoA.setEnabled(false);
        tietFechaC.setEnabled(false);

        tietApellidoP.setEnabled(false);
        tietApellidoM.setEnabled(false);
        tietNombre.setEnabled(false);
        tietEdad.setEnabled(false);
        tietDomicilio.setEnabled(false);
        tietTelefono.setEnabled(false);
        tietLugarNac.setEnabled(false);
        tietFechaNac.setEnabled(false);
        tietEstatura.setEnabled(false);
        tietPeso.setEnabled(false);
        rbCasado.setEnabled(false);
        rbSoltero.setEnabled(false);
        rbOtro.setEnabled(false);
        rbPadres.setEnabled(false);
        rbFamilia.setEnabled(false);
        rbParientes.setEnabled(false);
        rbSolo.setEnabled(false);
        cbHijos.setEnabled(false);
        cbConyuge.setEnabled(false);
        cbPadres.setEnabled(false);
        cbOtros.setEnabled(false);

        tietCurp.setEnabled(false);
        tietAfore.setEnabled(false);
        tietRfc.setEnabled(false);
        tietNumSegSoc.setEnabled(false);
        tietNumCartilla.setEnabled(false);
        tietPasaporte.setEnabled(false);
        tietClaveLicencia.setEnabled(false);
        tietNumLicencia.setEnabled(false);

        rbRegular.setEnabled(false);
        rbBueno.setEnabled(false);
        rbMalo.setEnabled(false);
        rbSiClubSocial.setEnabled(false);
        rbNoClubSocial.setEnabled(false);
        tietExpEnfermedad.setEnabled(false);
        tietDeporte.setEnabled(false);
        tietPasatiempo.setEnabled(false);
        tietMetaVida.setEnabled(false);

        tietNombreP.setEnabled(false);
        rbViveP.setEnabled(false);
        rbNoViveP.setEnabled(false);
        tietDomicilioP.setEnabled(false);
        tietOcupacionP.setEnabled(false);
        tietNombreM.setEnabled(false);
        rbViveM.setEnabled(false);
        rbNoViveM.setEnabled(false);
        tietDomicilioM.setEnabled(false);
        tietOcupacionM.setEnabled(false);
        tietNombreE.setEnabled(false);
        rbViveE.setEnabled(false);
        rbNoViveE.setEnabled(false);
        tietDomicilioE.setEnabled(false);
        tietOcupacionE.setEnabled(false);
        tietHijos.setEnabled(false);

        tietNombrePrimaria.setEnabled(false);
        tietDireccionPrimaria.setEnabled(false);
        tietDePrimaria.setEnabled(false);
        tietAPrimaria.setEnabled(false);
        tietAniosPrimaria.setEnabled(false);
        rbTituloPrimaria.setEnabled(false);
        tietNombreSecu.setEnabled(false);
        tietDireccionSecu.setEnabled(false);
        tietDeSecu.setEnabled(false);
        tietASecu.setEnabled(false);
        tietAniosSecu.setEnabled(false);
        rbTituloSecu.setEnabled(false);
        tietNombrePrepa.setEnabled(false);
        tietDireccionPrepa.setEnabled(false);
        tietDePrepa.setEnabled(false);
        tietAPrepa.setEnabled(false);
        tietAniosPrepa.setEnabled(false);
        rbTituloPrepa.setEnabled(false);
        tietNombreProfesional.setEnabled(false);
        tietDireccionProfesional.setEnabled(false);
        tietDeProfesional.setEnabled(false);
        tietAProfesional.setEnabled(false);
        tietAniosProfesional.setEnabled(false);
        rbTituloProfesional.setEnabled(false);
        tietNombreComercial.setEnabled(false);
        tietDireccionComercial.setEnabled(false);
        tietDeComercial.setEnabled(false);
        tietAComercial.setEnabled(false);
        tietAniosComercial.setEnabled(false);
        rbTituloComercial.setEnabled(false);
        tietNombreActual.setEnabled(false);
        tietDeActual.setEnabled(false);
        tietAActual.setEnabled(false);
        tietCursoActual.setEnabled(false);
        cbxGrado.setEnabled(false);

        //sustituir = findViewById(R.id.sustituir);

        rbMasculino.setEnabled(false);
        rbFemenino.setEnabled(false);

        cbxCartillaMilitar.setEnabled(false);

        rbNoPasaporte.setEnabled(false);
        rbSiPasaporte.setEnabled(false);

        rbNoLicencia.setEnabled(false);
        rbSiLicencia.setEnabled(false);

        rbNoEnfermedad.setEnabled(false);
        rbSiEnfermedad.setEnabled(false);

        rbNoDeporte.setEnabled(false);
        rbSiDeporte.setEnabled(false);

        rbNoHijos.setEnabled(false);
        rbSiHijos.setEnabled(false);

        rbMexicana.setEnabled(false);
        rbExtranjera.setEnabled(false);

        cbxDocExtran.setEnabled(false);

        rbNoEsposa.setEnabled(false);
        rbSiEsposa.setEnabled(false);

        cbPrimaria.setEnabled(false);
        cbSecundaria.setEnabled(false);
        cbPrepa.setEnabled(false);
        cbProfesional.setEnabled(false);
        cbComercial.setEnabled(false);
        cbEstudioAct.setEnabled(false);
    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}