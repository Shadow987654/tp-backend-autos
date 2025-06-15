package org.example.common.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Pruebas")
@NoArgsConstructor
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_VEHICULO")
    private Integer idVehiculo;

    @Column(name = "ID_INTERESADO")
    private Integer idInteresado;

    @Column(name = "ID_EMPLEADO")
    private Integer idEmpleado;

    @Column(name = "FECHA_HORA_INICIO")
    private String fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private String fechaHoraFin;

    @Column(name = "COMENTARIOS")
    private String comentarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VEHICULO", insertable = false, updatable = false)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INTERESADO", insertable = false, updatable = false)
    private Interesado interesado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", insertable = false, updatable = false)
    private Empleado empleado;

    // Getters y setters explícitos
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

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Interesado getInteresado() { return interesado; }
    public void setInteresado(Interesado interesado) { this.interesado = interesado; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
}