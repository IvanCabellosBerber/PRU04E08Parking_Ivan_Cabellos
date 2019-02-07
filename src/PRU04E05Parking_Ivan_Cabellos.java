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

    // Getters para las plazas ==========================================================================================
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

    public int entraCotxeDiscapacitat(String matricula) throws Exception{

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

    }

    public void surtCotxe (String matricula) throws Exception{

        this.matricula = null;
        cochesDentroDelParkingNormal.remove(matricula);

    }

    public void surtCotxeDiscapacitat (String matricula) throws Exception{

        this.matricula = null;
        cochesDentroDeMinusvalidos.remove(matricula);

    }
    //==================================================================================================================

    //Metodo para leer matriculas desde un fichero
    public void llegirMatricules (String path){
        //TODO a traves de un fichero ir guardando las matriculas que nos pasen
    }

    public void guardarMatricules (String path){
        //TODO tiene que guardas las amtriculas que hay en el parking dentro de un fichero
    }

}
