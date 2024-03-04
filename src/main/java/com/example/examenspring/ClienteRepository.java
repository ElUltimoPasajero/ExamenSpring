package com.example.examenspring;

import ch.qos.logback.core.net.server.Client;
import com.example.examenspring.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente getClienteById(Integer id);


  @Query("SELECT c FROM Cliente c WHERE c.estado='Activo' and  c.total >= :ventaTotal")
  List<Cliente> getClientePorVentaTotal(@PathVariable Integer ventaTotal);


    @Query("SELECT SUM(c.total) FROM Cliente c")
    Double sumarTotalVentas();

    @Query("SELECT AVG(c.total) FROM Cliente c WHERE c.estado = 'Activo'")
    Double calcularPromedioVentasActivos();
    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.estado = 'Inactivo' AND c.total > 0")
    Long contarClientesInactivosConVentasMayorCero();

  @Query("SELECT " +
          "CONCAT('Total de Ventas: ', SUM(c.total)), " +
          "CONCAT('Promedio de Ventas Activos: ', AVG(CASE WHEN c.estado = 'Activo' THEN c.total ELSE null END)), " +
          "CONCAT('Clientes Inactivos con Ventas Mayor a Cero: ', COUNT(CASE WHEN c.estado = 'Inactivo' AND c.total > 0 THEN 1 ELSE null END)) " +
          "FROM Cliente c")
  List<Object[]> getInformacionVentas();
}
