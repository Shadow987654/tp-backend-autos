package org.example.dto.response;

public class VehiculoKilometrosReporte {
    private Integer idVehiculo;
    private String patente;
    private String fechaInicio;
    private String fechaFin;
    private Double totalKilometros;
    
    // Getters y setters
    public Integer getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(Integer idVehiculo) { this.idVehiculo = idVehiculo; }
    
    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    
    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
    
    public Double getTotalKilometros() { return totalKilometros; }
    public void setTotalKilometros(Double totalKilometros) { this.totalKilometros = totalKilometros; }
}
