import java.util.Scanner;

public class PRU04E04ParkingTest_Ivan_Cabellos {

    public static void main(String[] args) {



        Scanner sc = new Scanner(System.in).useDelimiter("\\n");
        /*System.out.println("BIENVENIDOS A PALMA'S PARKING");
        System.out.println("Dime que es lo que quieres hacer");
        System.out.println("| [1] Mirar cuantas plazas de parking quedan libres |");
        System.out.println("| [2] Mirar "); */

        String matricula = "1234ABC";
        String matricula1 = "9876ZYX";

        PRU04E05Parking_Ivan_Cabellos parking = new PRU04E05Parking_Ivan_Cabellos(5, 1);

        try {
            parking.entraCotxe(matricula);
            parking.entraCotxeDiscapacitat(matricula1);



        } catch (Exception e){
            System.out.println("La matricula no es correcta");
            matricula = sc.next();
        }

    }

}
