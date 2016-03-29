package com.example.pepo.proyecto1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends Activity {

    private Game game;
    //static int GREEN=0;
    //static int PURPLE=1;
    //static int YELLOW=2;

    int colorPlayer=0;


    private final int ids[][] = {

            { R.id.c0, R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6 },
            { R.id.c7, R.id.c8, R.id.c9, R.id.c10, R.id.c11, R.id.c12, R.id.c13 },
            { R.id.c14, R.id.c15, R.id.c16, R.id.c17, R.id.c18, R.id.c19,
                    R.id.c20 },
            { R.id.c21, R.id.c22, R.id.c23, R.id.c24, R.id.c25, R.id.c26,
                    R.id.c27 },
            { R.id.c28, R.id.c29, R.id.c30, R.id.c31, R.id.c32, R.id.c33,
                    R.id.c34 },
            { R.id.c35, R.id.c36, R.id.c37, R.id.c38, R.id.c39, R.id.c40,
                    R.id.c41 } };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();//instanciamos el objeto tipo Game

    }//fin del metodo onCreate

    private int deIdentificadorAFila(int id){//nos devuelve la fila del tablero correspondiente al boton cuyo identificador se pasa como argumento

        //Buscamos el identificador en la matriz ids
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                if (ids[i][j]==id)
                return i;
            }
        }
        return -1;
    }//fin del metodo deIdentificadorAFila

    private int deIdentificadorAColumna(int id){//nos devuelve la columna del tablero correspondiente al boton cuyo identificador se pasa como argumento

        //Buscamos el identificador en la matriz ids
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                if (ids[i][j]==id)
                return j;
            }
        }
        return -1;
    }//fin del metodo deIdentificadorAColumna


    public void pulsado(View v){//metodo callback que se ejecuta cuando el jugador pulsa una posición del tablero


        //Identificamos las coordenadas del boton que se ha pulsado del tablero
        int id = v.getId();
        int fila = deIdentificadorAFila(id);
        int columna = deIdentificadorAColumna(id);

        if (game.sePuedeColocarFicha(fila,columna))
            //actualizamos el tablero con la ficha del jugador
            game.jugadorFicha(fila,columna);
        else{


            Toast.makeText(this, "No es posible colocar la ficha",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Comprobamos si el jugador humano ha ganado la partida
        if (game.comprobarCuatro(game.jugador)){
            Toast.makeText(this, "Enhorabuena! Has ganado la partida!",
                    Toast.LENGTH_SHORT).show();
            dibujarTablero();

            new AlertDialogFragment().show(getFragmentManager(), "ALERT DIALOG");

        }

        //Comprobamos si el tablero esta completo
        if (game.tableroLleno()){
            Toast.makeText(this, "Tablas!",
                    Toast.LENGTH_SHORT).show();
            restartActivity(this);

        }

        //Dejamos jugar a la maquina
        game.juegaMaquina();

        //Comprobamos si la maquina ha ganado
        if (game.comprobarCuatro(game.maquina)){
            Toast.makeText(this, "Vaya! Has perdido!",
                    Toast.LENGTH_SHORT).show();
            dibujarTablero();
            new AlertDialogFragment().show(getFragmentManager(), "ALERT DIALOG");
        }

        //Comprobamos si el tablero esta completo
        if (game.tableroLleno()){
            Toast.makeText(this, "Tablas!",
                    Toast.LENGTH_SHORT).show();
            dibujarTablero();
            new AlertDialogFragment().show(getFragmentManager(), "ALERT DIALOG");

        }

        //Actualizamos el aspecto de la interfaz
        dibujarTablero();

    }//fin del metodo pulsado

    public void dibujarTablero(){//Dibuja el tablero de acuerdo con los valores del array tablero

        int ident=0;//identificador de la casilla en la interfaz
        int id;
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                ident=ids[i][j];

                if (game.hayMaquina(i,j)==true){//Casilla ocupada por la maquina; pintamos en rojo

                    id = R.drawable.casilla_maquina;
                    ImageButton imageButton = (ImageButton) findViewById(ident);
                    imageButton.setImageResource(id);


                }


                if (game.hayJugador(i,j)==true){//Casilla ocupada por el jugador

                    if (colorPlayer==0){
                        id=R.drawable.casilla_humano;//por defecto, las fichas son verdes
                        ImageButton imageButton = (ImageButton) findViewById(ident);
                        imageButton.setImageResource(id);
                    }

                    if (colorPlayer==1){
                        id = R.drawable.casilla_morado;
                        ImageButton imageButton = (ImageButton) findViewById(ident);
                        imageButton.setImageResource(id);
                    }

                    if (colorPlayer==2){
                        id = R.drawable.casilla_amarillo;
                        ImageButton imageButton = (ImageButton) findViewById(ident);
                        imageButton.setImageResource(id);
                    }



                    }
                }

            }


    }//fin del metodo dibujarTablero


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.c4_menu, menu);
        return true;
    }//fin del metodo onCreateOptionsMenu


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAbout:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.preferences:
                startActivity(new Intent(this, CCCPreference.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }//fin del metodo onOptionsItemSelected



    public static void restartActivity(Activity actividad){
        Intent intent=new Intent();
        intent.setClass(actividad, actividad.getClass());
        //llamamos a la actividad
        actividad.startActivity(intent);
        //finalizamos la actividad actual
        actividad.finish();
    }//fin del metodo restartActivity


    protected void onResume(){
        super.onResume();

       if (music()){
           Music.play(this,R.raw.acousticbreeze);

       }
        String col = null;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.contains(CCCPreference.COLOR_KEY)){
            col = sharedPreferences.getString(CCCPreference.COLOR_KEY,CCCPreference.COLOR_DEFAULT);
            colorPlayer=Integer.parseInt(col);
            dibujarTablero();

        }

    }//fin del metodo onResume

    protected void onPause(){
        super.onPause();
        Music.stop(this);
    }

    public Boolean music(){
        Boolean play = false;
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.contains(CCCPreference.PLAY_MUSIC_KEY))
            play = sharedPreferences.getBoolean(CCCPreference.PLAY_MUSIC_KEY, false);
        return play;
    }//fin del metodo music

    public void setMusic (Boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(CCCPreference.PLAY_MUSIC_KEY, value);
        editor.commit();
    }//fin del metodo setMusic

/*
    public void setColor(int color){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CCCPreference.FIGURE_KEY,color);
        editor.apply();


        }//fin del metodo setColor
        public int color(){
        int col=1;
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (sharedPreferences.contains(CCCPreference.FIGURE_KEY))
                col = sharedPreferences.getInt(CCCPreference.FIGURE_KEY, 1);

        return col;
    }//fin del metodo color
*/
}//fin de la clase
