package negocio;

import java.util.List;
import entidad.Prestamos;

public interface PrestamoService {
    List<Prestamos> listarPrestamos();
    Prestamos obtenerPrestamoPorId(int id);
    boolean agregarPrestamo(Prestamos prestamo);
    boolean actualizarPrestamo(Prestamos prestamo);
    List<Prestamos> listarPrestamosPorEstado(String estado);
    List<Prestamos> listarPrestamosPorIdCliente(int idCliente);
}
