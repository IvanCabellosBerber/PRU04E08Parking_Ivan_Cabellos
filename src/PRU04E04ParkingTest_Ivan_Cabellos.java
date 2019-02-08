import java.util.Scanner;

public class PRU04E04ParkingTest_Ivan_Cabellos {

    private static Scanner sc = new Scanner(System.in).useDelimiter("\\n");
    private static PRU04E05Parking_Ivan_Cabellos parking_ivan_cabellos = crearParking();

    public static void main(String[] args) {

        int numMenu = menu();
        while (numMenu < 7){
            menuFunctions(numMenu);
            numMenu = menu();
        }

        System.out.println("Nos vemos pronto en Palma's Parking 24h");

    }

    private static PRU04E05Parking_Ivan_Cabellos crearParking(){
        System.out.println("Primero de todo tienes que crear un parking, o si quieres puedes usar uno predeterminado");
        System.out.println("Si quieres crear uno dime cuantas plazas no_minusvalido/minusvalidos quieres, si quieres uno " +
                "predeterminado introduce 'default'");

        String controlParking = sc.next().toUpperCase();

        try {

            if ("DEFAULT".equals(controlParking)) {
                return new PRU04E05Parking_Ivan_Cabellos(100, 5);
            } else {
                int numPlazasNormales = Integer.parseInt(controlParking);
                int numPlazasDiscapacitados = sc.nextInt();
                return new PRU04E05Parking_Ivan_Cabellos(numPlazasNormales, numPlazasDiscapacitados);
            }

        } catch (Exception e){
            System.err.println();
            System.out.println("No se ha podido crear el parking, vuelve a intentarlo");
            crearParking();
        }

        return null;
    }

    private static int menu(){

        System.out.println(" ---------   BIENVENIDOS A PALMA'S PARKING  ---------");
        System.out.println("|====================================================|");
        System.out.println("|                                                    |");
        System.out.println("|        Dime que es lo que quieres hacer            |");
        System.out.println("| [1] Llenar el parking con un fichero de matriculas |");
        System.out.println("| [2] Meter un coche en el parking                   |");
        System.out.println("| [3] Meter un coche para discapacitados             |");
        System.out.println("| [4] Sacar coche del parking                        |");
        System.out.println("| [5] Sacar un coche de discapacitados del parking   |");
        System.out.println("| [6] Mostrar coches dentro del parking              |");
        System.out.println("| [7] Guardar las matrículas del parking en fichero  |");
        System.out.println("|                                                    |");
        System.out.println("| [8] Salir del menú                                 |");
        System.out.println("|                                                    |");
        System.out.println("|====================================================|");

        String controlMenu = sc.next();


        try {
            int numControl = Integer.parseInt(controlMenu);
            if (numControl >= 1 && numControl <= 9) {
                return numControl;
            } else throw new Exception();

        } catch (Exception e){
            System.out.println();
            System.out.println("Introduce el numero adecuado");
            menu();
        }

        return -1;

    }

    private static void menuFunctions (int numControl){

        try {

            switch (numControl){
                case 1:
                    System.out.println("Has seleccionado meter las matriculas por fichero");
                    System.out.println("[TODO]");
                    System.out.println("Dime el path del fichero donde estan las matriculas: ");
                    String ficheroMatriculas = sc.next();

                    try {
                        parking_ivan_cabellos.llegirMatricules(ficheroMatriculas);
                        System.out.println("Parece que funciono todo correcto");
                    } catch (Exception e){
                        System.out.println("Algo no fue bien, vuelve a introducir el fichero");
                        break;
                    }
                    
                    break;

                case 2:
                    System.out.println("Has seleccionado meter un coche en el parking");
                    System.out.println("==============================================================");
                    System.out.println("Dime la matricula del coche que ha entrado, 'menu' para volver");
                    String matriculaNueva = sc.next();
                    if (matriculaNueva.equals("menu")){
                        break;
                    } else meterCocheParking(matriculaNueva);
                    break;

                case 3:
                    System.out.println("Has seleccionado meter un coche en el parking de minusválidos");
                    System.out.println("==============================================================");
                    System.out.println("Dime la matricula del coche que ha entrado, 'menu' para volver");
                    String matriculaNuevaMinusvalidos = sc.next();

                    if (matriculaNuevaMinusvalidos.equals("menu")){
                        break;
                    } else meterCocheMinusvalidos(matriculaNuevaMinusvalidos);

                    break;

                case 4:
                    System.out.println("Has seleccionado sacar un coche del parking");
                    System.out.println("=============================================================");
                    System.out.println("Dime la matricula del coche que ha salido, 'menu' para volver");
                    String matriculaNuevaSacar = sc.next();

                    if (matriculaNuevaSacar.equals("menu")){
                        break;
                    } else sacarCocheParkingNormal(matriculaNuevaSacar);
                    break;

                case 5:
                    System.out.println("Has seleccionado sacar un coche del parking");
                    System.out.println("==============================================================");
                    System.out.println("Dime la matricula del coche que ha salido, 'menu' para volver:");
                    String matriculaNuevaSacarMinusvalido = sc.next();

                    if (matriculaNuevaSacarMinusvalido.equals("menu")){
                        break;
                    } else sacarCocheParkingMinusvalido(matriculaNuevaSacarMinusvalido);
                    break;

                case 6:

                    System.out.println("Dime si quieres ver el parking normal o de minusvalidos: ");
                    String tipoDeParking = sc.next();

                    switch (tipoDeParking){
                        case "no_minusvalido":
                            System.out.println("===========================================");
                            System.out.println("   COCHES DENTRO DEL PARKING AHORA MISMO");
                            System.out.println("         Ahora mismo hay: " + parking_ivan_cabellos.getPlacesOcupades(TipusPlacesParking.no_minusvalido));
                            System.out.println("         Quedan: " + parking_ivan_cabellos.getPlacesLliures(TipusPlacesParking.no_minusvalido));
                            System.out.println("===========================================");
                            System.out.println();
                            System.out.println(PRU04E05Parking_Ivan_Cabellos.getCochesDentroDelParkingNormal());
                            System.out.println();
                            break;

                        case "minusvalido":
                            System.out.println("===========================================");
                            System.out.println("   COCHES DENTRO DEL PARKING AHORA MISMO");
                            System.out.println("         Ahora mismo hay: " + parking_ivan_cabellos.getPlacesOcupades(TipusPlacesParking.minusvalido));
                            System.out.println("         Quedan: " + parking_ivan_cabellos.getPlacesLliures(TipusPlacesParking.minusvalido));
                            System.out.println("===========================================");
                            System.out.println();
                            System.out.println(PRU04E05Parking_Ivan_Cabellos.getCochesDentroDelParkingNormal());
                            System.out.println();
                            break;
                    }
            }

        } catch (Exception e){
            System.err.println();
        }


    }

    private static void meterCocheParking (String matricula){
        try {
            parking_ivan_cabellos.entraCotxe(matricula);
        } catch (Exception e){
            String matriculaCorrecta = sc.next();
            meterCocheParking(matriculaCorrecta);
        }
    }

    private static void meterCocheMinusvalidos (String matricula){
        try {
            parking_ivan_cabellos.entraCotxeDiscapacitat(matricula);
        } catch (Exception e){
            System.err.println();
            System.out.println("Introduce la nueva matricula: ");
            String matriculaCorrecta = sc.next();
            meterCocheMinusvalidos(matriculaCorrecta);
        }
    }

    private static void sacarCocheParkingNormal (String matricula){
        try {
            parking_ivan_cabellos.surtCotxe(matricula);
        } catch (Exception e){
            System.err.println();
            System.out.println("No se ha podido encontrar el coche, vuelve a introducir la matricula:");
            String nuevaMatricula = sc.next();
            sacarCocheParkingNormal(nuevaMatricula);
        }
    }
    private static void sacarCocheParkingMinusvalido (String matricula){
        try {
            parking_ivan_cabellos.surtCotxeDiscapacitat(matricula);
        } catch (Exception e){
            System.err.println();
            System.out.println("No se ha podido encontrar el coche, vuelve a introducir la matricula:");
            String nuevaMatricula = sc.next();
            sacarCocheParkingNormal(nuevaMatricula);
        }
    }

}
