package umg.edu.progra.bst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ejemplo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ejemplo.class);

    public static void main(String[] args) {
        Empleado e1 = new Empleado(43, "Walter Cordova", "IT", "A-255");
        Empleado e2 = new Empleado(10, "Roxana Escobar", "RRHH", "A-255");
        Empleado e3 = new Empleado(8, "Bryan Orellana", "Ventas", "A-255");
        Empleado e4 = new Empleado(53, "Mario Ruano", "Logistica", "A-255");
        Empleado e5 = new Empleado(15, "Salazar Bitsco", "IT", "A-255");
        Empleado e6 = new Empleado(50, "Jonny Bravo", "RRHH", "A-255");
        Empleado e7 = new Empleado(54, "Sergio Espinoza", "Marketing", "A-255");
        Empleado e8 = new Empleado(25, "Belter Hernandez", "Contabilidad", "A-255");

        BST bst = new BST();
        System.out.println("Esta vacio: " + bst.esVacio() + " y es hoja " + bst.esHoja());
        LOGGER.info("Esta vacio: {} y es hoja {}", bst.esVacio(), bst.esHoja());


        bst.insertar(e1);
        bst.insertar(e2);
        bst.insertar(e3);
        bst.insertar(e4);
        bst.insertar(e5);
        bst.insertar(e6);
        bst.insertar(e7);
        bst.insertar(e8);


        LOGGER.info("Esta vacio: {} y es hoja {}", bst.esVacio(), bst.esHoja());


        localizar(bst, 20);
        localizar(bst, 45);
        localizar(bst, 25);
        localizar(bst, 19);


        bst.inOrden();
    }

    private static void localizar(BST bst, int id) {
        if (bst.existe(id)) {
            LOGGER.info("Obtener {}: {}", id, bst.obtener(id));
        } else {
            LOGGER.error("No encuentro el empleado: {}", id);
        }
    }
}
