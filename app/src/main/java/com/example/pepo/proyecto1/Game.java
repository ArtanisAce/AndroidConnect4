package com.example.pepo.proyecto1;
import java.util.Random;
/**
 * Created by Pepo on 05/11/2015.
 */
public class Game {

    static final int nfil = 6;
    static final int ncol = 7;
    static final int vacio = 0;
    static final int maquina = 1;
    static final int jugador = 2;
    private boolean juego_activo = true;


    private int tablero[][];

    /*Marcamos las posiciones libres con 0, las ocupadas por la maquina con 1 y las ocupadas por el jugador con 2.*/

    public Game() {//constructor sin argumentos donde se instancia el array tablero y se inicializan sus elementos a 0 (vacio)
        tablero = new int[nfil][ncol];
        for (int i=0;i<nfil;i++){
            for (int j=0;j<ncol;j++){

        tablero[i][j] = vacio;
            }
        }
    }//fin del constructor


    public void jugadorFicha(int i, int j){

        tablero[i][j]=jugador;

    }//fin del metodo jugadorFicha

    public boolean hayJugador(int i, int j){

        if (tablero[i][j]==jugador)
            return true;

        return false;

    }//fin del metodo hayJugador

    public boolean hayMaquina(int i, int j){

        if (tablero[i][j]==maquina)
            return true;

        return false;

    }//fin del metodo hayMaquina

    public boolean sePuedeColocarFicha(int i, int j){

        /*Comprobamos si la casilla esta vacia, y si es asi, checkeamos que sea la mas baja no ocupada.
        Para ello, solo necesitamos comprobar que la casilla siguiente de la columna esta ocupada y la anterior vacia.*/

        //Las excepciones son la primera fila y la última, que se saldrían del tablero al comprobar
        if (tablero[i][j]==vacio){

            if ((i==0) || (i==5)){

                if (((i==0) && (tablero[i+1][j]!=vacio)) || ((i==5) && (tablero[i-1][j] == vacio))) {
                return true;
                }
                return false;
        }
            else if ((tablero[i-1][j] == vacio) && ((tablero[i+1][j]!=vacio))){
            return true;
            }
        }
    return false;
    }//fin del metodo sePuedeColocarFicha

    public void juegaMaquina(){

        Random  rnd = new Random();//generador de numeros aleatorios
        int rnd1, rnd2;

        //Hacemos que la maquina ocupe una casilla aleatoria
        boolean casillaEncontrada=false;

            while (casillaEncontrada==false){

                rnd1=rnd.nextInt(nfil);
                rnd2=rnd.nextInt(ncol);

                if (sePuedeColocarFicha(rnd1,rnd2)== true){

                    casillaEncontrada=true;
                    tablero[rnd1][rnd2]=maquina;
                }
        }//fin del bucle while

    }//fin del metodo juegaMaquina

    boolean comprobarFilas(int turno){

    int contador=0;
    //Recorremos el tablero. Cuando encontremos una ficha, comprobaremos si hay jugada completa en toda la fila
    for (int i=0;i<nfil;i++){
        for (int j=0;j<ncol;j++)
        {
            if ((tablero[i][j]==turno) && (j<4)){//nos aseguramos que hay ficha y que no nos vamos a salir del tablero
                //Recorremos para comprobar si hay que veamos 4 fichas seguidas
                for (int k=j+1;k<j+4;k++){
                    if (tablero[i][k]==turno){
                        contador++;
                    }
                }
                if (contador==3)
                    return true;
            }
            contador=0;//reseteamos el contador
        }
    }
    return false;
}//fin del metodo comprobarFilas

    boolean comprobarColumnas(int turno){

        int contador=0;
        //Recorremos el tablero. Cuando encontremos una ficha, comprobaremos si hay jugada completa en toda la columna
        for (int i=0;i<nfil;i++){
            for (int j=0;j<ncol;j++)
            {
                if ((tablero[i][j]==turno) && (i<3)){//nos aseguramos que hay ficha y que no nos vamos a salir del tablero
                    //Recorremos para comprobar si hay que veamos 4 fichas seguidas
                    for (int k=i+1;k<i+4;k++){
                        if (tablero[k][j]==turno){
                            contador++;
                        }
                    }
                    if (contador==3)
                        return true;
                }
                contador=0;//reseteamos el contador
            }
        }
        return false;
    }//fin del metodo comprobarColumnas

    boolean comprobarDiagonal(int turno){

        int contador=0;
        //Recorremos el tablero. Cuando encontremos una ficha, comprobaremos si hay jugada completa en diagonal hacia abajo (derecha e izquierda)
        for (int i=0;i<nfil;i++){
            for (int j=0;j<ncol;j++)
            {
                if ((tablero[i][j]==turno) && (i<3) && (j<3)){//nos aseguramos que hay ficha y que no nos vamos a salir del tablero (derecha)
                    //Recorremos para comprobar si hay 4 fichas seguidas
                    for (int k=1;k<4;k++){
                        if (tablero[i+k][j+k]==turno){
                            contador++;
                        }
                    }
                    if (contador==3)
                        return true;

                    contador=0;//reseteamos el contador para buscar diagonal hacia la izquierda

                    }

                    if ((tablero[i][j]==turno) && (i<3) && (j>2)){//nos aseguramos que hay ficha y que no nos vamos a salir del tablero (izquierda)
                        //Recorremos para comprobar si hay 4 fichas seguidas
                        for (int k=1;k<4;k++){
                            if (tablero[i+k][j-k]==turno){
                                contador++;
                            }
                        }
                        if (contador==3)
                            return true;
                }
                contador=0;//reseteamos el contador
            }
        }
        return false;
    }//fin del metodo comprobarDiagonal

    boolean comprobarCuatro(int turno){//Comprobamos si alguno de los jugadores ha ganado
        if ((comprobarFilas(turno)) || (comprobarColumnas(turno)) || (comprobarDiagonal(turno))){
            return true;
        }

        return false;
    }//fin del metodo comprobarCuatro

    public boolean tableroLleno() {

        for (int i = 0; i < nfil; i++)
            for (int j = 0; j < ncol; j++)
                if (tablero[i][j] == vacio)
                    return false;

        return true;
    }//fin del metodo tableroLleno

    public void restart(){



    }//fin del metodo restart


}//fin de la clase
