package org.example.dto.request;

public class FechaRangoRequest {
    private String fechaInicio;
    private String fechaFin;
    
    // Getters y setters
    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
}
