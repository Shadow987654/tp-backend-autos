package org.example.common.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Vehiculos")
@Data
@NoArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "PATENTE", nullable = false)
    private String patente;

    @Column(name = "ID_MODELO")
    private Integer idModelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MODELO", insertable = false, updatable = false)
    private Modelo modelo;

    // Getters y setters explícitos
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public Integer getIdModelo() { return idModelo; }
    public void setIdModelo(Integer idModelo) { this.idModelo = idModelo; }

    public Modelo getModelo() { return modelo; }
    public void setModelo(Modelo modelo) { this.modelo = modelo; }
}
