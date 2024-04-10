package umg.edu.progra.bst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BST implements IBST<Empleado> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BST.class);

    public Empleado valor;
    public BST izdo, dcho;
    public BST padre;


    public void insertar(Empleado emp) {
        insertarImp(emp, null);
    }

    private void insertarImp(Empleado emp, BST padre) {
        if (valor == null) {
            this.valor = emp;
            this.padre = padre;
        } else {
            if (emp.compareTo(valor) < 0) {
                if (izdo == null) {
                    izdo = new BST();
                }
                izdo.insertarImp(emp, this);
            } else if (emp.compareTo(valor) > 0) {
                if (dcho == null) {
                    dcho = new BST();
                }
                dcho.insertarImp(emp, this);
            } else {
                throw new RuntimeException("Insertando elemento duplicado");
            }
        }
    }

    public boolean existe(int id) {
        if (valor != null) {
            if (id == valor.getId()) {
                return true;
            } else if (izdo != null && id < valor.getId()) {
                return izdo.existe(id);
            } else if (dcho != null && id > valor.getId()) {
                return dcho.existe(id);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Empleado obtener(int id) {
        if (valor != null) {
            if (id == valor.getId()) {
                return valor;
            } else if (izdo != null && id < valor.getId()) {
                return izdo.obtener(id);
            } else if (dcho != null && id > valor.getId()) {
                return dcho.obtener(id);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean esHoja() {
        return valor != null && izdo == null && dcho == null;
    }

    public boolean esVacio() {
        return valor == null;
    }

    public void preOrden() {
        preordenImpl("");
    }

    public void postOrden() {
        postordenImpl("");
    }

    public void inOrden() {
        inordenImpl("");
    }

    private void inordenImpl(String prefijo) {
        if (izdo != null) {
            izdo.inordenImpl(prefijo + "  ");
        }
        LOGGER.info("{}", prefijo + valor);
        if (dcho != null) {
            dcho.inordenImpl(prefijo + "  ");
        }
    }

    private void postordenImpl(String prefijo) {
        if (izdo != null) {
            izdo.postordenImpl(prefijo + "  ");
        }
        if (dcho != null) {
            dcho.postordenImpl(prefijo + "  ");
        }
        LOGGER.info("{}", prefijo + valor);
    }

    private void preordenImpl(String prefijo) {
        if (valor != null) {
            LOGGER.info("{}", prefijo + valor);
            if (izdo != null) {
                izdo.preordenImpl(prefijo + "  ");
            }
            if (dcho != null) {
                dcho.preordenImpl(prefijo + "  ");
            }
        }
    }

    private void eliminarImpl(int id) {
        if (izdo != null && dcho != null) {
            BST sucesor = dcho.obtenerMinimo();
            this.valor = sucesor.valor;
            dcho.eliminar(sucesor.valor.getId());
        } else if (izdo != null || dcho != null) {
            BST hijo = (izdo != null) ? izdo : dcho;
            if (padre != null) {
                if (this == padre.izdo) {
                    padre.izdo = hijo;
                } else {
                    padre.dcho = hijo;
                }
                hijo.padre = padre;
            } else {
                this.valor = hijo.valor;
                this.izdo = hijo.izdo;
                this.dcho = hijo.dcho;
            }
        } else {
            if (padre != null) {
                if (this == padre.izdo) {
                    padre.izdo = null;
                } else {
                    padre.dcho = null;
                }
                padre = null;
            } else {
                valor = null;
            }
        }
    }

    public void eliminar(int id) {
        if (valor != null) {
            if (id == valor.getId()) {
                eliminarImpl(id);
            } else if (izdo != null && id < valor.getId()) {
                izdo.eliminar(id);
            } else if (dcho != null && id > valor.getId()) {
                dcho.eliminar(id);
            }
        }
    }

    private BST obtenerMinimo() {
        if (izdo == null) {
            return this;
        } else {
            return izdo.obtenerMinimo();
        }
    }
}
