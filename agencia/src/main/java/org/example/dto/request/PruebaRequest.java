package org.example.dto.request;

public class PruebaRequest {
    private Integer idVehiculo;
    private Integer idInteresado;
    private Integer idEmpleado;
    
    // Getters y setters explícitos
    public Integer getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(Integer idVehiculo) { this.idVehiculo = idVehiculo; }
    
    public Integer getIdInteresado() { return idInteresado; }
    public void setIdInteresado(Integer idInteresado) { this.idInteresado = idInteresado; }
    
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
}