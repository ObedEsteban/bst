package umg.edu.progra.bst;

public class BSTDeletion {

    public static void eliminarNodo(BST bst, int id) {
        if (bst != null) {
            bst.eliminar(id);
        }
    }

    private static void eliminarImpl(BST nodo, int id) {
        if (nodo == null) {
            return;
        }

        if (id < nodo.valor.getId()) {
            eliminarImpl(nodo.izdo, id);
        } else if (id > nodo.valor.getId()) {
            eliminarImpl(nodo.dcho, id);
        } else {
            if (nodo.izdo == null) {
                reemplazarNodo(nodo, nodo.dcho);
            } else if (nodo.dcho == null) {
                reemplazarNodo(nodo, nodo.izdo);
            } else {
                BST sucesor = obtenerMinimo(nodo.dcho);
                nodo.valor = sucesor.valor;
                eliminarImpl(nodo.dcho, sucesor.valor.getId());
            }
        }
    }

    private static BST obtenerMinimo(BST nodo) {
        BST actual = nodo;
        while (actual.izdo != null) {
            actual = actual.izdo;
        }
        return actual;
    }

    private static void reemplazarNodo(BST nodoEliminar, BST nodoReemplazo) {
        if (nodoEliminar.padre != null) {
            if (nodoEliminar == nodoEliminar.padre.izdo) {
                nodoEliminar.padre.izdo = nodoReemplazo;
            } else {
                nodoEliminar.padre.dcho = nodoReemplazo;
            }
        }
        if (nodoReemplazo != null) {
            nodoReemplazo.padre = nodoEliminar.padre;
        }
    }
}
