import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class PRU04E08Parking_Ivan_Cabellos {

    private Scanner sc = new Scanner(System.in).useDelimiter("\\n");

    /**
     *
     * TODO Leer las matriculas que entran directamente al parking a traves de FICHERO o ARGUMENTOS
     * TODO Un 'garrulo' entrará aleatoriamente dentro del parking y aparcará en zona de minusválidos
     * TODO Métodos: leer matriculas; entra_coche, sale_coche; plazasOcupadas, plazasLibres; guardarMatriculas;
     *
     * TODO 1 o más entrarán al parking ocupando una plaza minusvalido
     * TODO alerta cuando tenga más del 95% de ocupacion
     * TODO alerta cuando tenga el 100% de ocupacion.
     *
     * TODO todo sera a traves de menu, controlar las excepciones, que no pare nunca y decidir cuando sale uno del menu
     *
     * */

    //PLAZAS DE PARKING
    private static String[] lugaresReservados = new String[100];
    private static String[] lugaresReservadosMinusvalidos = new String[5];
    private static HashMap<String, Byte> cochesDentroDelParking = new HashMap<String, Byte>();

    //ATRIBUTOS DE LOS COCHES
    private String matricula;
    private byte posicionCoche;
    private boolean parkingMinusvalido = false;
    private byte contadorCochesEntrados;

    //CONSTRUCTORES
    public PRU04E08Parking_Ivan_Cabellos(File ficheroMatriculas){

    }

    public PRU04E08Parking_Ivan_Cabellos(String args[]){

    }

    public PRU04E08Parking_Ivan_Cabellos(String matriculaNueva){
        setMatricula(matriculaNueva);
        generarEntradaCoche();
    }

    //GETTERS AND SETTERS
    public static String[] getLugaresReservados() {
        return lugaresReservados;
    }

    public static void setLugaresReservados(String[] lugaresReservados) {
        PRU04E08Parking_Ivan_Cabellos.lugaresReservados = lugaresReservados;
    }

    public static String[] getLugaresReservadosMinusvalidos() {
        return lugaresReservadosMinusvalidos;
    }

    public static void setLugaresReservadosMinusvalidos(String[] lugaresReservadosMinusvalidos) {
        PRU04E08Parking_Ivan_Cabellos.lugaresReservadosMinusvalidos = lugaresReservadosMinusvalidos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {

        try {
            if (isValid(matricula)){
                this.matricula = matricula;
            } else throw new Exception();

        } catch (Exception e){
            System.out.println("La matricula no es valida, introduce otra matricula");
            matricula = sc.next();
            setMatricula(matricula);
        }

    }

    public byte getPosicionCoche() {
        return posicionCoche;
    }

    public void setPosicionCoche(byte posicionCoche) {
        this.posicionCoche = posicionCoche;
    }

    //TO STRING

    @Override
    public String toString() {
        return "\nMATRICULA: '" + matricula + '\'' +
               "\nPOSICION DEL COCHE: " + posicionCoche +
               "\nparkingMinusvalido = " + parkingMinusvalido + "\n";
    }


    //METODOS:

    public void generarEntradaCoche(){
        byte randNum = (byte) ((Math.random() * 100) + 1);

        if (randNum >= 2){
            entraCoche();
            contadorCochesEntrados++;
        } else if (contadorCochesEntrados == 99){
            entraCocheMinusvalidos(); contadorCochesEntrados = 0;
        } else {
            entraCocheMinusvalidos(); contadorCochesEntrados = 0;
        }
        
    }

    /* Funcion que se basa cuando entra un coche dentro del parking y aparca en un sitio normal */
    public void entraCoche(){

        System.out.println("El coche con matricula " + this.matricula + " ha entrado en el parking");

        for (int i = 0; i < lugaresReservados.length; i++) {
            if (lugaresReservados[i] == null){
                lugaresReservados[i] = this.matricula;

                setPosicionCoche((byte) (i + 1));
                cochesDentroDelParking.put(this.matricula, this.posicionCoche);
                break;
            }
        }
    }

    /* Funcion que se basa cuando entra un coche dentro del parking y aparca en un sitio para minusvalidos */
    public void entraCocheMinusvalidos(){

        System.out.println("El coche con matricula " + this.matricula + " ha entrado en el parking de minusvalidos");

        for (int i = 0; i < lugaresReservadosMinusvalidos.length; i++) {
            if (lugaresReservadosMinusvalidos[i] == null){
                lugaresReservadosMinusvalidos[i] = this.matricula;

                setPosicionCoche((byte) (i + 1));
                parkingMinusvalido = true;
                cochesDentroDelParking.put(this.matricula, this.posicionCoche);
                break;
            }
        }
    }

    private boolean isValid(String matricula) {

        matricula = matricula.toUpperCase();

        if (matricula.length() > 7){
            return false;
        }

        for (int i = 0; i < matricula.length(); i++) {
            if (i <= 3){
                if (!(matricula.charAt(i) >= 48 && matricula.charAt(i) <= 57)){
                    return false;
                }
            } else {
                if (!(matricula.charAt(i) >= 65 && matricula.charAt(i) <= 90)){
                    return false;
                }
            }
        }
        return true;
    }

    /* MAIN DE PRUEBAS */
    public static void main(String[] args) {

        System.out.println(cochesDentroDelParking);

    }

}
