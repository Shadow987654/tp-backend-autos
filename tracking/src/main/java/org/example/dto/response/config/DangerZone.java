package org.example.dto.response.config;

public class DangerZone {
    private String idZona;
    private String nombreZona;
    private Location coordenadas;
    private Integer radioMetros;
    
    public String getIdZona() { return idZona; }
    public void setIdZona(String idZona) { this.idZona = idZona; }
    
    public String getNombreZona() { return nombreZona; }
    public void setNombreZona(String nombreZona) { this.nombreZona = nombreZona; }
    
    public Location getCoordenadas() { return coordenadas; }
    public void setCoordenadas(Location coordenadas) { this.coordenadas = coordenadas; }
    
    public Integer getRadioMetros() { return radioMetros; }
    public void setRadioMetros(Integer radioMetros) { this.radioMetros = radioMetros; }
}
