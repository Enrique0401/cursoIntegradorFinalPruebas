package Servicio;

import Model.Contacto;
import Repositorio.ContactoRepositorio;
import java.util.List;

public class ContactoService {

    private final ContactoRepositorio repositorio;

    public ContactoService(ContactoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Contacto> obtenerTodos() {
        return repositorio.obtenerTodos();
    }
}
