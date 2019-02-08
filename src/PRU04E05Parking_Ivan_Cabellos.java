import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class PRU04E05Parking_Ivan_Cabellos {

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

    //ATRIBUTOS PARA RESCATAR Y IMPRIMIR EN FICHERO
    private static BufferedReader br;
    private static BufferedWriter bw;

    //PLAZAS DE PARKING
    private static int[] lugaresReservados;
    private static int[] lugaresReservadosMinusvalidos;
    private static HashMap<String, Integer> cochesDentroDelParkingNormal = new HashMap<String, Integer>();
    private static HashMap<String, Integer> cochesDentroDeMinusvalidos = new HashMap<String, Integer>();
    private TipusPlacesParking tipusPlacesParking;

    //ATRIBUTOS DE LOS COCHES
    private String matricula;

    //CONSTRUCTORES
    public PRU04E05Parking_Ivan_Cabellos(int places_no_discapacitats, int places_discapacitats){

        lugaresReservados = new int[places_no_discapacitats];
        lugaresReservadosMinusvalidos = new int[places_discapacitats];

    }

    //GETTERS AND SETTERS

    // Getters para las plazas =========================================================================================
    // Getter para plazas ocupadas
    public int getPlacesOcupades(TipusPlacesParking tipusPlacesParking) {

        switch (tipusPlacesParking){
            case no_minusvalido:
                return cochesDentroDelParkingNormal.size();
            case minusvalido:
                return cochesDentroDeMinusvalidos.size();
        }

        return Integer.parseInt(null);
    }
    // Getter para plazas libres
    public int getPlacesLliures(TipusPlacesParking tipusPlacesParking) {

        switch (tipusPlacesParking){
            case no_minusvalido:
                return lugaresReservados.length - cochesDentroDelParkingNormal.size();
            case minusvalido:
                return lugaresReservadosMinusvalidos.length - cochesDentroDeMinusvalidos.size();
        }

        return Integer.parseInt(null);
    }

    public static HashMap<String, Integer> getCochesDentroDelParkingNormal() {
        return cochesDentroDelParkingNormal;
    }

    public static HashMap<String, Integer> getCochesDentroDeMinusvalidos() {
        return cochesDentroDeMinusvalidos;
    }

    // =================================================================================================================


    //MÉTODOS//

    //Metodos para coches     ==========================================================================================

    public int entraCotxe(String matricula) throws Exception{

        try {

            matricula = matricula.toUpperCase();

            comprovarMatricula(matricula);
            compruebaSitioNormal();

            if (entraGarrulo()){
                entraCotxeDiscapacitat(matricula);
            } else {
                Integer posicionCoche = 1;
                for (int i = 0; i < lugaresReservados.length; i++) {

                    if (lugaresReservados[i] == 0){
                        lugaresReservados[i] = posicionCoche;
                        cochesDentroDelParkingNormal.put(matricula, posicionCoche);
                        this.tipusPlacesParking = TipusPlacesParking.no_minusvalido;
                        break;
                    }
                    posicionCoche++;
                }
                return posicionCoche;
            }

        } catch (Exception e){
            System.err.println();
            System.out.println("No se ha podido meter el coche");
        }

        return -1;

    }

    public int entraCotxeDiscapacitat(String matricula) throws Exception{


        try {

            matricula = matricula.toUpperCase();

            comprovarMatricula(matricula);
            compruebaSitioDiscapacitados();
            Integer posicionCocheMinusvalido = 1;
            for (int i = 0; i < lugaresReservadosMinusvalidos.length; i++) {

                if (lugaresReservadosMinusvalidos[i] == 0){
                    lugaresReservadosMinusvalidos[i] = posicionCocheMinusvalido;
                    cochesDentroDeMinusvalidos.put(matricula, posicionCocheMinusvalido);
                    tipusPlacesParking = TipusPlacesParking.minusvalido;
                    break;
                }
                posicionCocheMinusvalido++;

            }
            return posicionCocheMinusvalido;
        } catch (Exception e){
            System.err.println();
            System.out.println("No se ha podido meter el coche dentro");
        }

        return -1;

    }

    private void compruebaSitioNormal(){

        try {
            if (cochesDentroDelParkingNormal.size() == lugaresReservados.length){
                throw new Exception();
            } else if (cochesDentroDelParkingNormal.size() == (lugaresReservados.length - 5)){
                System.out.println();
                System.out.println("======================================================");
                System.out.println("El parking esta casi lleno, le costará encontrar sitio");
                System.out.println("======================================================");
                System.out.println();
            }
        } catch (Exception e){
            System.out.println("El sitio esta FULL, prueba de ir a otro parking");
        }

    }

    private void compruebaSitioDiscapacitados() throws Exception {

        try {
            if (cochesDentroDeMinusvalidos.size() == lugaresReservadosMinusvalidos.length){
                throw new Exception();
            } else if (cochesDentroDeMinusvalidos.size() == (lugaresReservadosMinusvalidos.length - 1)){
                System.out.println("El parking de minusválidos esta casi lleno, le costará encontrar sitio");
            }
        } catch (Exception e){
            System.out.println("El sitio para minusválidos esta FULL, prueba de ir al parking no_minusvalido");
            entraCotxe(matricula);
        }

    }

    private boolean entraGarrulo(){

        try {
            int posibleGarrulo = (int) ((Math.random() * 100) + 1);
            int porcentajeDeGarrulos = 2;

            if (posibleGarrulo <= porcentajeDeGarrulos) {
                System.out.println("GARRULO DETECTED!");
                return true;
            }

        } catch (Exception e){
            System.err.println();
            System.out.println("Matricula no correcta del garrulo");
        }

        return false;
    }

    public void surtCotxe (String matricula) throws Exception{

        try {
            this.matricula = null;
            cochesDentroDelParkingNormal.remove(matricula);
        } catch (Exception e){
            System.err.println();
            System.out.println("No se ha podido encontrar la matricula, introducela de nuevo");
        }

    }

    public void surtCotxeDiscapacitat (String matricula) throws Exception{

        try {
            this.matricula = null;
            cochesDentroDeMinusvalidos.remove(matricula);
        } catch (Exception e){
            System.err.println();
            System.out.println("No se ha podido encontrar la matricula, introducela de nuevo");
        }

    }
    //==================================================================================================================

    //Metodo para leer matriculas desde un fichero =====================================================================
    public void llegirMatricules (String path){

        try {

            br = new BufferedReader(new FileReader(path));

            String matricula = br.readLine();

            while (matricula != null){
                entraCotxe(matricula);
                matricula = br.readLine();
            }

            br.close();

        } catch (IOException e){
            System.err.println();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void guardarMatricules (String path){
        //TODO tiene que guardas las matriculas que hay en el parking dentro de un fichero

        try {
            bw = new BufferedWriter(new FileWriter(path));



        } catch (IOException e){
            System.err.println();
        }

    }

    private void comprovarMatricula(String matricula) throws Exception {

        try {

            //Comprobar si cumple con la siguiente expresion regular: [0-9]{4}[A-Z]{3}
            if (matricula.length() != 7) throw new Exception();

            for (int i = 0; i < matricula.length(); i++) {
                if (i <= 3){
                    if (!(matricula.charAt(i) >= 48 && matricula.charAt(i) <= 57)){
                        throw new Exception();
                    }
                } else {
                    if (!(matricula.charAt(i) >= 65 && matricula.charAt(i) <= 90)){
                        throw new Exception();
                    }
                }
            }

            System.out.println("Matricula validada");

        } catch (Exception e){
            System.err.println();
            System.out.println("La matricula es incorrecta, vuelve a introducirla para comprobarla");
            String matriculaNueva = sc.next();
            comprovarMatricula(matriculaNueva);
        }


    }

    //==================================================================================================================

}
