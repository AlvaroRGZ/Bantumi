package es.upm.miw.bantumi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.upm.miw.bantumi.dialog.BuildMatchDialog;
import es.upm.miw.bantumi.dialog.FinalAlertDialog;
import es.upm.miw.bantumi.dialog.RestartDialog;
import es.upm.miw.bantumi.dialog.SaveMatchDialog;
import es.upm.miw.bantumi.model.Score;
import es.upm.miw.bantumi.view.BantumiViewModel;
import es.upm.miw.bantumi.view.ScoreViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MiW";
    public JuegoBantumi juegoBantumi;
    public BantumiViewModel bantumiVM;
    int numInicialSemillas;

    private SharedPreferences sharedPref;

    private final String NOMBRE_FICHERO = "saved_match";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // Instancia el ViewModel y el juego, y asigna observadores a los huecos
        //numInicialSemillas = Integer.parseInt(this.sharedPref.getString("numeroSemillasIniciales", "9"));
        numInicialSemillas = getResources().getInteger(R.integer.intNumInicialSemillas);
        bantumiVM = new ViewModelProvider(this).get(BantumiViewModel.class);
        juegoBantumi = new JuegoBantumi(bantumiVM, JuegoBantumi.Turno.turnoJ1, numInicialSemillas);

        crearObservadores();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        TextView tvJugador1 = findViewById(R.id.tvPlayer1);
        tvJugador1.setText(this.sharedPref.getString("nombreJugador1", "Jugador 1"));

        TextView tvJugador2 = findViewById(R.id.tvPlayer2);
        tvJugador2.setText(this.sharedPref.getString("nombreJugador2", "Jugador 2"));
    }

    /**
     * Crea y subscribe los observadores asignados a las posiciones del tablero.
     * Si se modifica el contenido del tablero -> se actualiza la vista.
     */
    private void crearObservadores() {
        for (int i = 0; i < JuegoBantumi.NUM_POSICIONES; i++) {
            int finalI = i;
            bantumiVM.getNumSemillas(i).observe(    // Huecos y almacenes
                    this,
                    new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {
                            mostrarValor(finalI, juegoBantumi.getSemillas(finalI));
                        }
                    });
        }
        bantumiVM.getTurno().observe(   // Turno
                this,
                new Observer<JuegoBantumi.Turno>() {
                    @Override
                    public void onChanged(JuegoBantumi.Turno turno) {
                        marcarTurno(juegoBantumi.turnoActual());
                    }
                }
        );
    }

    /**
     * Indica el turno actual cambiando el color del texto
     *
     * @param turnoActual turno actual
     */
    private void marcarTurno(@NonNull JuegoBantumi.Turno turnoActual) {
        TextView tvJugador1 = findViewById(R.id.tvPlayer1);
        TextView tvJugador2 = findViewById(R.id.tvPlayer2);
        switch (turnoActual) {
            case turnoJ1:
                tvJugador1.setTextColor(getColor(R.color.white));
                tvJugador1.setBackgroundColor(getColor(android.R.color.holo_blue_light));
                tvJugador2.setTextColor(getColor(R.color.black));
                tvJugador2.setBackgroundColor(getColor(R.color.white));
                break;
            case turnoJ2:
                tvJugador1.setTextColor(getColor(R.color.black));
                tvJugador1.setBackgroundColor(getColor(R.color.white));
                tvJugador2.setTextColor(getColor(R.color.white));
                tvJugador2.setBackgroundColor(getColor(android.R.color.holo_blue_light));
                break;
            default:
                tvJugador1.setTextColor(getColor(R.color.black));
                tvJugador2.setTextColor(getColor(R.color.black));
        }
    }

    /**
     * Muestra el valor <i>valor</i> en la posición <i>pos</i>
     *
     * @param pos posición a actualizar
     * @param valor valor a mostrar
     */
    private void mostrarValor(int pos, int valor) {
        String num2digitos = String.format(Locale.getDefault(), "%02d", pos);
        // Los identificadores de los huecos tienen el formato casilla_XX
        int idBoton = getResources().getIdentifier("casilla_" + num2digitos, "id", getPackageName());
        if (0 != idBoton) {
            TextView viewHueco = findViewById(idBoton);
            viewHueco.setText(String.valueOf(valor));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opciones_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcAjustes:
                startActivity(new Intent(this, BantumiPrefs.class));
                return true;
            case R.id.opcAcercaDe:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.aboutTitle)
                        .setMessage(R.string.aboutMessage)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
                return true;
            case R.id.opcReiniciarPartida:
                new RestartDialog()
                        .show(getSupportFragmentManager(), "REINICIAR_DIALOG");
                return true;

            case R.id.opcGuardarPartida:
                new SaveMatchDialog()
                        .show(getSupportFragmentManager(), "GUARDAR_DIALOG");
                return true;

            case R.id.opcRecuperarPartida:
                new BuildMatchDialog()
                        .show(getSupportFragmentManager(), "RECUPERAR_DIALOG");
                return true;

            case R.id.opcMejoresResultados:
                startActivity(new Intent(MainActivity.this, ScoreActivity.class));
                return true;

            default:
                Snackbar.make(
                        findViewById(android.R.id.content),
                        getString(R.string.txtSinImplementar),
                        Snackbar.LENGTH_LONG
                ).show();
        }
        return true;
    }

    /**
     * Acción que se ejecuta al pulsar sobre cualquier hueco
     *
     * @param v Vista pulsada (hueco)
     */
    public void huecoPulsado(@NonNull View v) {
        String resourceName = getResources().getResourceEntryName(v.getId()); // pXY
        int num = Integer.parseInt(resourceName.substring(resourceName.length() - 2));
        Log.i(LOG_TAG, "huecoPulsado(" + resourceName + ") num=" + num);
        switch (juegoBantumi.turnoActual()) {
            case turnoJ1:
                juegoBantumi.jugar(num);
                break;
            case turnoJ2:
                juegaComputador();
                break;
            default:    // JUEGO TERMINADO
                finJuego();
        }
        if (juegoBantumi.juegoTerminado()) {
            finJuego();
        }
    }

    /**
     * Elige una posición aleatoria del campo del jugador2 y realiza la siembra
     * Si mantiene turno -> vuelve a jugar
     */
    void juegaComputador() {
        while (juegoBantumi.turnoActual() == JuegoBantumi.Turno.turnoJ2) {
            int pos = 7 + (int) (Math.random() * 6);    // posición aleatoria [7..12]
            Log.i(LOG_TAG, "juegaComputador(), pos=" + pos);
            if (juegoBantumi.getSemillas(pos) != 0 && (pos < 13)) {
                juegoBantumi.jugar(pos);
            } else {
                Log.i(LOG_TAG, "\t posición vacía");
            }
        }
    }

    /**
     * El juego ha terminado. Volver a jugar?
     */
    private void finJuego() {

        this.sharedPref.getString("NombreJugador1", "Jugador 1");
        String texto = (juegoBantumi.getSemillas(6) > 6 * numInicialSemillas)
                ? "Gana " + this.sharedPref.getString("nombreJugador1", "Jugador 1")
                : "Gana " + this.sharedPref.getString("nombreJugador2", "Jugador 2");
        if (juegoBantumi.getSemillas(6) == 6 * numInicialSemillas) {
            texto = "¡¡¡ EMPATE !!!";
        }
        Snackbar.make(
                        findViewById(android.R.id.content),
                        texto,
                        Snackbar.LENGTH_LONG
                )
                .show();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

        Score score = new Score(
                        sdf.format(new Date()),
                        this.sharedPref.getString("nombreJugador1", "Jugador 1"), this.juegoBantumi.getDeposito1(),
                        this.sharedPref.getString("nombreJugador2", "Jugador 2"), this.juegoBantumi.getDeposito2(),
                    juegoBantumi.getSemillas(6) > 6 * numInicialSemillas);
        try {
            new ViewModelProvider(this).get(ScoreViewModel.class).insert(score);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // terminar
        new FinalAlertDialog().show(getSupportFragmentManager(), "ALERT_DIALOG");
    }

    private String obtenerNombreDeArchivo() {
        return NOMBRE_FICHERO;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void saveGame() {
        try {
            FileOutputStream fos = openFileOutput(obtenerNombreDeArchivo(), Context.MODE_PRIVATE);
            fos.write((this.juegoBantumi.serializa()).getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rebuildGame() {
        String readedData = "";
        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput(obtenerNombreDeArchivo())));
            String linea = fin.readLine();
            while (linea != null) {
                readedData += linea + ";";
                linea = fin.readLine();
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.juegoBantumi.deserializa(readedData);
    }
}