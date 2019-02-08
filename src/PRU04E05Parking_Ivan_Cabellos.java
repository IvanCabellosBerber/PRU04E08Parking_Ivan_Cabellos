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

    //PLAZAS DE PARKING
    private static int[] lugaresReservados;
    private static int[] lugaresReservadosMinusvalidos;
    private static HashMap<String, Byte> cochesDentroDelParkingNormal = new HashMap<String, Byte>();
    private static HashMap<String, Byte> cochesDentroDeMinusvalidos = new HashMap<String, Byte>();

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
    public int getPlacesOcupades() {
        return (cochesDentroDelParkingNormal.size() + cochesDentroDeMinusvalidos.size());
    }
    // Getter para plazas libres
    public int getPlacesLliures() {
        return ((lugaresReservados.length - cochesDentroDelParkingNormal.size()) +
                (lugaresReservadosMinusvalidos.length - cochesDentroDeMinusvalidos.size()));
    }

    // =================================================================================================================


    //MÉTODOS//

    //Metodos para coches     ==========================================================================================

    public int entraCotxe(String matricula) throws Exception{

        try {

            comprovarMatricula(matricula);
            compruebaSitioNormal();

            if (entraGarrulo()){
                entraCotxeDiscapacitat(matricula);
            } else {
                byte posicionCoche = 1;
                for (int i = 0; i < lugaresReservados.length; i++) {

                    if (lugaresReservados[i] == 0){
                        lugaresReservados[i] = posicionCoche;
                        cochesDentroDelParkingNormal.put(matricula, posicionCoche);
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
            comprovarMatricula(matricula);
            compruebaSitioDiscapacitados();
            byte posicionCocheMinusvalido = 1;
            for (int i = 0; i < lugaresReservadosMinusvalidos.length; i++) {

                if (lugaresReservadosMinusvalidos[i] == 0){
                    lugaresReservadosMinusvalidos[i] = posicionCocheMinusvalido;
                    cochesDentroDeMinusvalidos.put(matricula, posicionCocheMinusvalido);
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
            System.out.println("El sitio para minusválidos esta FULL, prueba de ir al parking normal");
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
        //TODO a traves de un fichero ir guardando las matriculas que nos pasen
    }

    public void guardarMatricules (String path){
        //TODO tiene que guardas las amtriculas que hay en el parking dentro de un fichero
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
