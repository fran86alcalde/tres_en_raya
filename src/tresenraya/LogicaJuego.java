package tresenraya;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;

public class LogicaJuego {
    int turno, pX, pO; // Turno del jugador
    boolean habilitado; // Habilita y deshabilita el tablero

    /**
     * Inicializaremos el juego con las siguientes variables_
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     * @return 
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     * @param turno 
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }
    
    /**
     * Llamaremos a este método para cambiar de turno
     */
    public void cambioTurno(){
        // Inserta código aquí...
        if (turno==0){
        turno=1;
                }
        else {turno=0;}
    }
    
    /**
     * Comprobar si se ha conseguido un tres en raya, 
     * en caso que se haya conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     * @param matriz (Tablero de juego)
     * @return 
     */
    public int comprobarJuego(int matriz[][]){
        // Inserta código aquí...
        
        // Comprobar si existe tres en raya
        // Comprobar horizontal
        if(matriz[0][0]==matriz[0][1] && matriz[0][0]== matriz[0][2]){
        return 1;}
        if(matriz[1][0]==matriz[1][1] && matriz[1][0]== matriz[1][2]){
        return 1;}
        if(matriz[2][0]==matriz[2][1] && matriz[2][0]== matriz[2][2]){
        return 1;}
        
        //Comprobar vertical
          if(matriz[0][0]==matriz[1][0] && matriz[0][0]== matriz[2][0]){
        return 1;}
        if(matriz[0][1]==matriz[1][1] && matriz[0][1]== matriz[2][1]){
        return 1;}
        if(matriz[0][2]==matriz[1][2] && matriz[0][2]== matriz[2][2]){
        return 1;}
        
        //Comprobar en diagonal
         if(matriz[0][0]==matriz[1][1] && matriz[0][0]== matriz[2][2]){
        return 1;}
        if(matriz[2][0]==matriz[1][1] && matriz[2][0]== matriz[0][2]){
        return 1;}
        
        // Si no hay tres en raya
        return 0;
    }
    
    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha en ese hueco
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return 
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y, int matriz[][], javax.swing.JPanel jp, javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
        
        // Deshabilita el botón
        bt.setEnabled(false);
        
        // Insertar la ficha en el botón
        ponerFicha(matriz,x,y,bt);
               
        // Comprobar si se ha ganado la partida
        comprobarJuego(matriz);
         
         // Deshabilitar tablero
         if(comprobarJuego(matriz)==1){
         habilitado = false;
         habilitarTablero(jp);
         ganador(lX,lO);
         JOptionPane.showMessageDialog(null,"GANASTE CRACK","GANADOR",JOptionPane.INFORMATION_MESSAGE);
        
         }
         cambioTurno();
         
         return 0;
    }
    
    /**
     * Actualizar la puntuación de cada jugador al ganar la partida
     * Al finalizar el incremento de puntuación es necesario cambiar de turno
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
         if (getTurno()==0){
           int valor_JLabel=Integer.parseInt (lX.getText());
           valor_JLabel ++;
           lX.setText(""+valor_JLabel);
          }
          else{
           int valor_JLabel=Integer.parseInt (lO.getText());
           valor_JLabel ++;
           lO.setText(""+valor_JLabel);
              
          }
        
        // Incrementa jugador ganador e inserta resultado en jLabel    
 
    }
    
    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la variable habilitado
     * @param jp  (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero( javax.swing.JPanel jp){
        // Inserta código aquí...
        Component [] mis_componentes=jp.getComponents();
        for(Component micomponente:mis_componentes){
        
            if (habilitado){
                micomponente.setEnabled(true);
            } else {micomponente.setEnabled(false);
            
            }
        }
                   
        // Bloquea todos los elementos del JPanel
        
    }
    
    /**
     * Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     * @param matriz (Tablero de juego)
     * @param t (Turno)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt){
        // Inserta código aquí...
        if (turno==0){
            matriz[x][y]=0;
        }else matriz[x][y]=1;
        
        pintarFicha(bt);
            

        // Insertar ficha en la posición de la matriz
        
    }
    
    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt){
        // Inserta código aquí...
        
        // Si el turno es de 0 pintará una X en rojo
        if (turno==0){
            bt.setText("X");
            bt.setForeground(Color.red);
        
        }else{
            bt.setText("O");
            bt.setForeground(Color.blue);
            

         // Si el turno es de 1, pintará una O en azul 
         
        }
    }
    
    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y habilita el tablero
     * 
     * ¡¡¡¡No es necesario modificar este método!!!!.
     * 
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp){
        // Rellenamos la matriz por primera vez, evitando que se repitan los números
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                matriz[x][y]=(x+10)*(y+10);
            }
        }

        // Habilitar tablero
         habilitado = true;
         habilitarTablero(jp);
    }
}
