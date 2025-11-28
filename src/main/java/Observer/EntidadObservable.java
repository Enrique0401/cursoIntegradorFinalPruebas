package Observer;

import java.util.ArrayList;
import java.util.List;

public class EntidadObservable implements Observable {

    private final List<Observador> observadores = new ArrayList<>();

    @Override
    public void agregarObservador(Observador o) {
        if (o != null && !observadores.contains(o)) {
            observadores.add(o);
        }
    }

    @Override
    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }

    /**
     * Permite obtener la cantidad actual de observadores registrados.
     */
    public int getCantidadObservadores() {
        return observadores.size();
    }
}
