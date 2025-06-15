package org.example.dto.response.config;

import java.util.List;

public class AgencyConfig {
    private Location ubicacionAgencia;
    private Integer radioMaximoMetros;
    private List<DangerZone> zonasPeligrosas;
    
    public Location getUbicacionAgencia() { return ubicacionAgencia; }
    public void setUbicacionAgencia(Location ubicacionAgencia) { this.ubicacionAgencia = ubicacionAgencia; }
    
    public Integer getRadioMaximoMetros() { return radioMaximoMetros; }
    public void setRadioMaximoMetros(Integer radioMaximoMetros) { this.radioMaximoMetros = radioMaximoMetros; }
    
    public List<DangerZone> getZonasPeligrosas() { return zonasPeligrosas; }
    public void setZonasPeligrosas(List<DangerZone> zonasPeligrosas) { this.zonasPeligrosas = zonasPeligrosas; }
}
