package ui;
import java.util.Scanner;
import model.Bus;

public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        Bus bus = new Bus();
        boolean flag = true;


        System.out.println("Bienvenido/a a la aplicacion de buses");
        while (flag) {

            System.out.println("Por favor ingrese una opcion: \n" +
                    "1. Añadir una estacion.\n" +
                    "2. Eliminar una estacion.\n" +
                    "3. Mostrar las estaciones.\n" +
                    "4. Ordenar las estaciones por nombre en orden alfabetico.\n" +
                    "5. Ordenar intercambiando referencias.\n" +
                    "6. Añadir street stop.\n" +
                    "7. Eliminar street stop.\n" +
                    "8. Salir.\n");

            int op = reader.nextInt();
            reader.nextLine();


            switch (op) {

                case 1:
                    System.out.println("Por favor ingrese el nombre de la estacion: ");
                    String name = reader.nextLine();
                    System.out.println("Por favor ingrese el codigo de la estacion: ");
                    int code = reader.nextInt();




                    if(bus.addNewStation(name, code)){
                        System.out.println("El estacion se ha agregado correctamente.");
                    } else {
                        System.out.println("El estacion no se agrego");
                    }


                break;

                case 2:

                    System.out.println("Por favor ingrese el nombre de la estacion que desea eliminar: ");
                    String stationToRemove = reader.nextLine();

                    if(bus.removeStation(stationToRemove)){
                        System.out.println("La estacion se ha elimindado correctamente.");
                    } else {
                        System.out.println("la estacion no ha sido encontrada.");
                    }


                    break;

                case 3:
                    System.out.println(bus.showAllStations());
                    break;

                case 4:

                    bus.sortByName();
                    System.out.println("Listo, ahora puedes imprimir las estaciones.");
                    break;

                case 5:
                    bus.sortByNameExchangeRef();
                    System.out.println("Listo, ahora puedes imprimir las estaciones.");
                    break;

                case 6:

                    System.out.println("Por favor ingrese el nombre del street stop: ");
                    String nameStreetStop = reader.nextLine();
                    System.out.println("Por favor ingrese la ubicacion del street stop ");
                    String location = reader.nextLine();


                    if(bus.addStreetStop(nameStreetStop, location)){
                        System.out.println("La street stop se ha añadido correctamente.");
                    } else {
                        System.out.println("La street stop no se agrego");
                    }

                    break;

                case 7:

                    System.out.println("Por favor ingrese el nombre de la street stop que desea eliminar: ");
                    String strStopToRemove = reader.nextLine();

                    if(bus.removeStreetStop(strStopToRemove)){
                        System.out.println("La street stop se ha elimindado correctamente.");
                    } else {
                        System.out.println("La street stop no ha sido encontrada.");
                    }
                    break;

                case 8:
                    System.out.println("Hasta luego feliz dia.");
                    flag = false;
                    break;

                default:

                System.out.println("Opcion no valida, por favor ingrese solo 1 2 3 o 4.");
            }
        }


    }
}
