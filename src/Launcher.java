
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Launcher {

    /**
     * @param args the command line arguments
     * @return 
     */
    
    public static int generarCartas(){
        return (int) Math.ceil(Math.random()*13);
    }
    
    public static ArrayList repartirCartas(ArrayList cartas, int jug1, int jug2, int maq1, int maq2){
        System.out.println("Sus cartas son: " + verificarCarta(jug1) + " " + verificarCarta(jug2));
        System.out.println("La maquina tiene: " + verificarCarta(maq1));
        cartas.add(verificarCarta(jug1));
        cartas.add(verificarCarta(jug2));
        cartas.add(verificarCarta(maq1));
        cartas.add(verificarCarta(maq2));
        cartas.add(2);
        cartas.add(2);
        jugar1(cartas);
        return cartas;
    }
    
    public static ArrayList jugar1(ArrayList cartas){
        if (sumaCartas(cartas)<21){
            System.out.println("Que desea hacer?");
            System.out.println(" 1 Pedir carta");
            System.out.println(" 2 Quedarse");
            if ((new Scanner(System.in)).nextInt()==1){
                pedirNuevaCarta(cartas, generarCartas());
                jugar1(cartas);
            }
        }
        if (sumaCartas(cartas)>21){
            if(asJugador(cartas)){
                cartas.set(cartas.indexOf("A"),"a");
                System.out.println("La suma de sus cartas es: " + sumaCartas(cartas));
                jugar1(cartas);
            }else {
                System.out.println("Perdio");
            }
        }else {
            jugarMaquina(cartas);
        }
        return cartas;
    }
    
    public static boolean asJugador(ArrayList cartas){
        if (((int)cartas.get(4))==3){
            if ("A".equals(cartas.get(0).toString()) || "A".equals(cartas.get(1).toString()) || "A".equals(cartas.get(6).toString())){
                return true;
            }
        }else if (((int)cartas.get(4))==4){
            if ("A".equals(cartas.get(0).toString()) || "A".equals(cartas.get(1).toString()) || "A".equals(cartas.get(6).toString())
                    || "A".equals(cartas.get(7).toString())){
                return true;
            }
        }else if (((int)cartas.get(4))==5){
            if ("A".equals(cartas.get(0).toString()) || "A".equals(cartas.get(1).toString()) || "A".equals(cartas.get(6).toString())
                    || "A".equals(cartas.get(7).toString()) || "A".equals(cartas.get(8).toString())){
                return true;
            }
        }else{
            if ("A".equals(cartas.get(0).toString()) || "A".equals(cartas.get(1).toString())){
                return true;
            }
        }
        return false;
    }
    
    public static boolean asMaquina(ArrayList cartas){
        if (((int)cartas.get(5))==3){
            if ("A".equals(cartas.get(2).toString()) || "A".equals(cartas.get(3).toString()) || "A".equals((((int)cartas.get(4))+4)+"")){
                return true;
            }
        }else if (((int)cartas.get(5))==4){
            if ("A".equals(cartas.get(2).toString()) || "A".equals(cartas.get(3).toString()) || "A".equals((((int)cartas.get(4))+4)+"")
                    || "A".equals((((int)cartas.get(4))+5)+"")){
                return true;
            }
        }else if (((int)cartas.get(5))==5){
            if ("A".equals(cartas.get(2).toString()) || "A".equals(cartas.get(3).toString()) || "A".equals((((int)cartas.get(4))+4)+"")
                    || "A".equals((((int)cartas.get(4))+5)+"") || "A".equals((((int)cartas.get(4))+4)+"")){
                return true;
            }
        }else{
            if ("A".equals(cartas.get(2).toString()) || "A".equals(cartas.get(3).toString())){
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList pedirNuevaCarta(ArrayList cartas, int jug3){
        System.out.println("Su nueva carta es: " + verificarCarta(jug3));
        if (((int) cartas.get(4))==2)
            System.out.println("Sus cartas son: " + cartas.get(0) + " " + cartas.get(1) + " " + verificarCarta(jug3));
        else if (((int) cartas.get(4))==3)
            System.out.println("Sus cartas son: " + cartas.get(0) + " " + cartas.get(1) + " " + cartas.get(6) + " " + verificarCarta(jug3));
        else if (((int) cartas.get(4))==4)
            System.out.println("Sus cartas son: " + cartas.get(0) + " " + cartas.get(1) + " " + cartas.get(6) + " " + cartas.get(7) + " " + verificarCarta(jug3));
        System.out.println("La maquina tiene: " + cartas.get(2));
        cartas.set(4,((int) cartas.get(4))+1);
        cartas.add(verificarCarta(jug3));
        return cartas;
    }
    public static ArrayList pedirCartaMaquina(ArrayList cartas, int maq3){
        cartas.set(5,((int) cartas.get(5))+1);
        cartas.add(verificarCarta(maq3));
        return cartas;
    }
    
    public static ArrayList jugarMaquina(ArrayList cartas){
        if(sumaCartas(cartas)>sumaCartasMaquina(cartas) && sumaCartasMaquina(cartas)<21){
            pedirCartaMaquina(cartas, generarCartas());
            jugarMaquina(cartas);
        }else if (sumaCartas(cartas)==sumaCartasMaquina(cartas)){
            if(((int)cartas.get(4))<=((int)cartas.get(5))){
                System.out.println("Perdio por cantidad de cartas");
            }else{
                System.out.println("Gano por cantidad de cartas");
            }
        }else if (sumaCartasMaquina(cartas)>21){
            if(asMaquina(cartas)){
                cartas.set(cartas.indexOf("A"),"a");
                jugarMaquina(cartas);
            }else{
                System.out.println("Gano");
            }
        }else{
            System.out.println("Perdio");
        }return cartas;
    }
    
    //ArrayList
    //pos(0) carta 1 jugador
    //pos(1) carta 2 jugador
    //pos(2) carta 1 maquina
    //pos(3) carta 2 maquina
    //pos(4) cantidad cartas jugador
    //pos(5) cantidad cartas maquina
    //pos(6) carta nueva jugador
    
    public static void jugar(ArrayList cartas){
        System.out.println("    Bienvenido al Juego     ");
        repartirCartas(cartas, generarCartas(), generarCartas(), generarCartas(), generarCartas());
        System.out.println("La suma de sus cartas fue: "+sumaCartas(cartas));
        System.out.println("La suma de las cartas de la maquina fue: "+sumaCartasMaquina(cartas));
    }
    
    public static int sumaCartas(ArrayList cartas){
        if (((int)cartas.get(4))==3)
            return verificarValor(cartas,0)+verificarValor(cartas,1)+verificarValor(cartas,6);
        else if (((int)cartas.get(4))==4)
           return verificarValor(cartas,0)+verificarValor(cartas,1)+verificarValor(cartas,6)+verificarValor(cartas,7);
        else if (((int)cartas.get(4))==5)
           return verificarValor(cartas,0)+verificarValor(cartas,1)+verificarValor(cartas,6)+verificarValor(cartas,7)+verificarValor(cartas,8);
        else
            return verificarValor(cartas,0)+verificarValor(cartas,1);
    }
    
    public static int sumaCartasMaquina(ArrayList cartas){
        if (((int)cartas.get(5))==3)
            return verificarValor(cartas,2)+verificarValor(cartas,3)+verificarValor(cartas,((int)cartas.get(4))+4);
        else if (((int)cartas.get(5))==4)
           return verificarValor(cartas,2)+verificarValor(cartas,3)+verificarValor(cartas,((int)cartas.get(4))+4)+verificarValor(cartas,((int)cartas.get(4))+5);
        else if (((int)cartas.get(5))==5)
           return verificarValor(cartas,2)+verificarValor(cartas,3)+verificarValor(cartas,((int)cartas.get(4))+4)+verificarValor(cartas,((int)cartas.get(4))+5)
                   +verificarValor(cartas,((int)cartas.get(4))+6);
        else
            return verificarValor(cartas,2)+verificarValor(cartas,3);
    }
    
    public static int verificarValor(ArrayList cartas,int posicion){
        switch (cartas.get(posicion).toString()) {
            case "a":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "J":
                return 10;
            case "Q":
                return 10;
            case "K":
                return 10;
            default:
                return 11;
        }
    }
    
    public static String verificarCarta(int carta){
        if (carta == 1)
            return "A";
        else if (carta == 11)
            return "J";
        else if (carta == 12)
            return "Q";
        else if (carta == 13)
            return "K";
        else
            return carta+"";
    } 
    public static void main(String[] args) {
        jugar(new ArrayList());
    }
}