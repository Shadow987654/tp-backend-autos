package org.example.dto.response;

import java.util.List;

public class IncidenteReporte {
    private Integer idPrueba;
    private String fechaPrueba;
    private String patenteVehiculo;
    private String nombreEmpleado;
    private String nombreInteresado;
    private List<IncidenteDetalle> incidentes;
    
    // Getters y setters
    public Integer getIdPrueba() { return idPrueba; }
    public void setIdPrueba(Integer idPrueba) { this.idPrueba = idPrueba; }
    
    public String getFechaPrueba() { return fechaPrueba; }
    public void setFechaPrueba(String fechaPrueba) { this.fechaPrueba = fechaPrueba; }
    
    public String getPatenteVehiculo() { return patenteVehiculo; }
    public void setPatenteVehiculo(String patenteVehiculo) { this.patenteVehiculo = patenteVehiculo; }
    
    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }
    
    public String getNombreInteresado() { return nombreInteresado; }
    public void setNombreInteresado(String nombreInteresado) { this.nombreInteresado = nombreInteresado; }
    
    public List<IncidenteDetalle> getIncidentes() { return incidentes; }
    public void setIncidentes(List<IncidenteDetalle> incidentes) { this.incidentes = incidentes; }
    
    public static class IncidenteDetalle {
        private String tipo;
        private String fechaHora;
        private String mensaje;
        
        // Getters y setters
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        
        public String getFechaHora() { return fechaHora; }
        public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
        
        public String getMensaje() { return mensaje; }
        public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    }
}
