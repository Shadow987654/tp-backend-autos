package org.example.common.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Interesados")
@Data
@NoArgsConstructor
public class Interesado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    private String tipoDocumento;

    @Column(name = "DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "RESTRINGIDO")
    private Integer restringido;

    @Column(name = "NRO_LICENCIA")
    private Integer nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private String fechaVencimientoLicencia;

    // Getters y setters explícitos
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Integer getRestringido() { return restringido; }
    public void setRestringido(Integer restringido) { this.restringido = restringido; }

    public Integer getNroLicencia() { return nroLicencia; }
    public void setNroLicencia(Integer nroLicencia) { this.nroLicencia = nroLicencia; }

    public String getFechaVencimientoLicencia() { return fechaVencimientoLicencia; }
    public void setFechaVencimientoLicencia(String fechaVencimientoLicencia) { this.fechaVencimientoLicencia = fechaVencimientoLicencia; }
}
