package Util;

import Model.Cliente;
import Model.Dueno;

/**
 * Clase utilitaria para gestionar la sesión activa en el sistema. Solo puede
 * haber un tipo de usuario autenticado a la vez: Cliente o Dueño.
 */
public final class Session {

    private static Cliente clienteActual;
    private static Dueno duenoActual;

    // Constructor privado: evita instanciación
    private Session() {
    }

    // ===============================
    // 🟢 MÉTODOS PARA INICIAR SESIÓN
    // ===============================
    public static void iniciarSesionCliente(Cliente cliente) {
        clienteActual = cliente;
        duenoActual = null;
        System.out.println("✅ Sesión iniciada como ADMINISTRADOR: " + cliente.getNombreCliente());
    }

    public static void iniciarSesionDueno(Dueno dueno) {
        duenoActual = dueno;
        clienteActual = null;
        System.out.println("✅ Sesión iniciada como DUEÑO: " + dueno.getNombreDueno());
    }

    // ===============================
    // 🔴 MÉTODOS PARA CERRAR SESIÓN
    // ===============================
    public static void cerrarSesion() {
        clienteActual = null;
        duenoActual = null;
        System.out.println("🚪 Sesión cerrada correctamente.");
    }

    // ===============================
    // 🟡 VERIFICACIONES DE SESIÓN
    // ===============================
    /**
     * Verifica si hay alguna sesión activa.
     */
    public static boolean haySesionActiva() {
        return clienteActual != null || duenoActual != null;
    }

    /**
     * Devuelve el tipo de usuario que tiene la sesión activa.
     */
    public static String tipoSesionActiva() {
        if (clienteActual != null) {
            return "CLIENTE";
        }
        if (duenoActual != null) {
            return "DUEÑO";
        }
        return "NINGUNO";
    }

    // ===============================
    // 🧩 GETTERS DE USUARIOS ACTIVOS
    // ===============================
    public static Cliente getClienteActual() {
        return clienteActual;
    }

    public static Dueno getDuenoActual() {
        return duenoActual;
    }
}
