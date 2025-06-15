package org.example.dto.response;

import org.example.common.models.Prueba;

public class PruebaResponse {
    private Integer id;
    private Integer idVehiculo;
    private Integer idInteresado;
    private Integer idEmpleado;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private String comentarios;
    private String patenteVehiculo;
    private String nombreInteresado;
    private String apellidoInteresado;
    private String nombreEmpleado;
    
    // Métodos getters y setters explícitos
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(Integer idVehiculo) { this.idVehiculo = idVehiculo; }
    
    public Integer getIdInteresado() { return idInteresado; }
    public void setIdInteresado(Integer idInteresado) { this.idInteresado = idInteresado; }
    
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    
    public String getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(String fechaHoraInicio) { this.fechaHoraInicio = fechaHoraInicio; }
    
    public String getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(String fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }
    
    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
    
    public String getPatenteVehiculo() { return patenteVehiculo; }
    public void setPatenteVehiculo(String patenteVehiculo) { this.patenteVehiculo = patenteVehiculo; }
    
    public String getNombreInteresado() { return nombreInteresado; }
    public void setNombreInteresado(String nombreInteresado) { this.nombreInteresado = nombreInteresado; }
    
    public String getApellidoInteresado() { return apellidoInteresado; }
    public void setApellidoInteresado(String apellidoInteresado) { this.apellidoInteresado = apellidoInteresado; }
    
    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }
    
    public static PruebaResponse fromEntity(Prueba prueba) {
        PruebaResponse response = new PruebaResponse();
        
        try {
            response.setId(prueba.getId());
            response.setIdVehiculo(prueba.getIdVehiculo());
            response.setIdInteresado(prueba.getIdInteresado());
            response.setIdEmpleado(prueba.getIdEmpleado());
            response.setFechaHoraInicio(prueba.getFechaHoraInicio());
            response.setFechaHoraFin(prueba.getFechaHoraFin());
            response.setComentarios(prueba.getComentarios());
            
            if (prueba.getVehiculo() != null) {
                response.setPatenteVehiculo(prueba.getVehiculo().getPatente());
            }
            
            if (prueba.getInteresado() != null) {
                response.setNombreInteresado(prueba.getInteresado().getNombre());
                response.setApellidoInteresado(prueba.getInteresado().getApellido());
            }
            
            if (prueba.getEmpleado() != null) {
                response.setNombreEmpleado(prueba.getEmpleado().getNombre());
            }
        } catch (Exception e) {
            System.err.println("Error mapeando Prueba a PruebaResponse: " + e.getMessage());
            e.printStackTrace();
        }
        
        return response;
    }
}