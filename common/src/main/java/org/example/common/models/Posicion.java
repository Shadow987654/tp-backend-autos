package org.example.common.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Posiciones")
@Data
@NoArgsConstructor
public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_VEHICULO")
    private Integer idVehiculo;

    @Column(name = "FECHA_HORA")
    private String fechaHora;

    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VEHICULO", insertable = false, updatable = false)
    private Vehiculo vehiculo;

    // Getters y setters explícitos
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdVehiculo() {
        return idVehiculo;
    }
    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    public String getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    public Double getLatitud() {
        return latitud;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    public Double getLongitud() {
        return longitud;
    }
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    @Override
    public String toString() {
        return "Posicion{" +
                "id=" + id +
                ", idVehiculo=" + idVehiculo +
                ", fechaHora='" + fechaHora + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", vehiculo=" + vehiculo +
                '}';
    }
}
