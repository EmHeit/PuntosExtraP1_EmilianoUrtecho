package puntosextra_emilianourtecho;
import java.util.Random;
import java.util.Scanner;
public class PuntosExtra_EmilianoUrtecho {
    public static void main(String[] args) {
        Scanner escan = new Scanner (System.in);
        System.out.println("BIENVENIDO A BATTLESHIP");
        System.out.println("ELIJA MODO DE JUEGO: ");
        System.out.println("    1. Modo de un jugador");
        System.out.println("    2. Modo de dos jugadores");
        System.out.print("Usted elija: ");
        byte modo = escan.nextByte();        
        do {
            switch (modo){
                case 1:
                    jugadorModo_1_Jugador();
                    break;
                case 2:
                    jugadorModo_2_Jugadores();
                    break;
                case 3:
                    System.out.println("Antes de salir, piense sabiamente en lo que quiere...");
                    break;
                default:
                    System.out.println("Por el momento no puedo leer eso, vuelva a intentarlo");
                    break;
            }
        System.out.println("Ya que termino la batalla, desea volver a intentarlo o salir? ");
        System.out.println("ELIJA MODO DE JUEGO: ");
        System.out.println("    1. Modo de un jugador");
        System.out.println("    2. Modo de dos jugadores");
        System.out.println("    3. SALIR");
        System.out.print("Usted elija: ");
        modo = escan.nextByte();
        } while (modo != 3);
        System.out.println("Gracias por jugar!! Se le aprecia");

    }
    
    public static void tablero (char[][]tabla){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                tabla [i][j] = '*';
            }
        }
    }
    public static void barcos (char[][]tablero){
        Random random = new Random();
        int posicionBarcos = 0;
        do {
            int fila = random.nextInt(6);
            int columna = random.nextInt(5);
            if (tablero[fila][columna]== '*') {
                tablero[fila][columna]='B';
                posicionBarcos++;
   
            }
        } while (posicionBarcos < 3);
    }
    public static void imprimirTablero (char[][]tablero, boolean modoSilencioso){
        System.out.println("Tablero: ");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (modoSilencioso) {
                    if (tablero[i][j]=='X') {
                        System.out.print("X");
                    }else if (tablero[i][j]=='O') {
                        System.out.print("O");
                    }else{
                        System.out.print(" ");
                    }
                }else{
                    System.out.print(tablero[i][j]+" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    public static void jugadorModo_1_Jugador (){
        Scanner escan = new Scanner (System.in);
        char [][] tablero = new char [6][5];
        tablero(tablero);
        barcos(tablero); //ESta parte del codigo lo tenia en el main pero mejor lo hice dentro un metodo
        int golpes = 0;
        boolean modoSilencioso = false;
        System.out.println("Modo de un jugador seleccionado.");
        System.out.println("Que dificultad desea?: ");
        System.out.println("    1. Modo Silencioso");
        System.out.println("    2. Modo Simple");
        System.out.println("    3. SALIR");
        System.out.print("Que desea ver?: ");
        byte dificultad = escan.nextByte();
        
        switch(dificultad){
            case 1:
                modoSilencioso = true;
                break;
            case 2:
                modoSilencioso = false;
                break;
            case 3:
                System.out.println("Si sale ahora, todos sus datos que no has guardado se perderan. ¿Seguro que quiere salir?");
                break;
            default:
                System.out.println("No puedo leer eso, lo siento :(");
                break;
        }
        while(golpes < 3){
            imprimirTablero(tablero, modoSilencioso);
            System.out.print("Ingrese fila (0-5): "); //Me dijo un excompanero de programacion que hiciera esto porque me pensaba que el limite era 6
            int fila = escan.nextInt();
            System.out.print("Ingrese columna (0-4): ");//Lo mismo pero con las columnas
            int columna = escan.nextInt();
            
            if (fila>=0 && fila<6 && columna >= 0 && columna < 5) {//Admito que esta condicion me la corrigio un amigo
                if (tablero[fila][columna] == 'B') {
                    System.out.println("Un barco fue derribado!!!");
                    tablero[fila][columna] = 'X';
                    golpes++;
                }else{
                    System.out.println("BOMBA AL AGUA");
                    tablero[fila][columna] = 'O';
                }
            }else{
                System.out.println("Las coordenadas que ha ingresado son invalidas, vuelva a intentarlo");
            }
        }
        System.out.println("Has hundido los 3 barcos. HAS GANADO");
    }
    public static void jugadorModo_2_Jugadores(){
        Scanner escan = new Scanner(System.in);
        char[][] tablero1 = new char [6][5];
        char[][] tablero2 = new char [6][5];
        tablero(tablero1);
        tablero(tablero2);
        barcos(tablero1);
        barcos(tablero2);
        
        int golpesJugador1 = 0;
        int golpesJugador2 = 0;
        boolean normal = true;
        boolean modoSilencioso = false;
        System.out.println("Has seleccionados el modo 2 JUGADORES");
        
        while (golpesJugador1 < 3 && golpesJugador2 < 3){
            char[][] tableroAtacado;
            char[][] mostrarTablero;
            int golpes;
            
            if (normal) {
                tableroAtacado = tablero2;
                mostrarTablero = tablero1;
                golpes = golpesJugador1;
                System.out.println("Turno del jugador 1: ");                
            }else{
                tableroAtacado = tablero1;
                mostrarTablero = tablero2;
                golpes = golpesJugador2;
                System.out.println("Turno del jugador 2: ");
            }
            imprimirTablero(mostrarTablero, modoSilencioso);
            System.out.print("Ingrese fila (0-5): ");
            int fila = escan.nextInt();
            System.out.print("Ingrese columna (0-4): ");
            int columna = escan.nextInt();
            
            if (fila>=0 && fila<6 && columna>=0 && columna < 5) {
                if (tableroAtacado[fila][columna] == 'B') {
                    System.out.println("Un barco ha sido derribado!!!");
                    tableroAtacado[fila][columna] = 'X';
                    golpes++;
                    if (normal) {
                        golpesJugador1 = golpes;
                    }else{
                        golpesJugador2 = golpes;
                    }
                }else{
                    System.out.println("BOMBA AL AGUA");
                    tableroAtacado[fila][columna] = 'O';
                }
            }else{
                System.out.println("Las coordenadas son invalidas, vuelva a intentarlo");
            }
            normal = !normal;
        }
        if (golpesJugador1 >= 3) {
            System.out.println("El ganador es ... EL JUGADOR 1!!!");
        }else{
            System.out.println("EL ganador es ... EL JUGADOR 2!!!");
        }
    }
}
