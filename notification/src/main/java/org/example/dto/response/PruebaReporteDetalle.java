package org.example.dto.response;

public class PruebaReporteDetalle {
    private Integer idPrueba;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private String comentarios;
    private String nombreEmpleado;
    private String nombreInteresado;
    private Boolean tuvoIncidentes;
    private Double distanciaRecorrida;
    
    // Getters y setters
    public Integer getIdPrueba() { return idPrueba; }
    public void setIdPrueba(Integer idPrueba) { this.idPrueba = idPrueba; }
    
    public String getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(String fechaHoraInicio) { this.fechaHoraInicio = fechaHoraInicio; }
    
    public String getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(String fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }
    
    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
    
    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }
    
    public String getNombreInteresado() { return nombreInteresado; }
    public void setNombreInteresado(String nombreInteresado) { this.nombreInteresado = nombreInteresado; }
    
    public Boolean getTuvoIncidentes() { return tuvoIncidentes; }
    public void setTuvoIncidentes(Boolean tuvoIncidentes) { this.tuvoIncidentes = tuvoIncidentes; }
    
    public Double getDistanciaRecorrida() { return distanciaRecorrida; }
    public void setDistanciaRecorrida(Double distanciaRecorrida) { this.distanciaRecorrida = distanciaRecorrida; }
}
