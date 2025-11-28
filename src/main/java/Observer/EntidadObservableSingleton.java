package Observer;

/**
 * Singleton que garantiza una única instancia de EntidadObservable. Ideal para
 * notificar cambios globales en el sistema (usuarios, dueños, etc.)
 */
public class EntidadObservableSingleton {

    private static final EntidadObservable instancia = new EntidadObservable();

    private EntidadObservableSingleton() {
        // Evita la creación de instancias externas
    }

    /**
     * Devuelve la instancia única del observable global.
     */
    public static EntidadObservable getInstancia() {
        return instancia;
    }
}
