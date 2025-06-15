package org.example.common.models;

import javax.persistence.*;

@Entity(name = "Notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_EMPLEADO", nullable = false)
    private Integer idEmpleado;

    @Column(name = "ID_PRUEBA")
    private Integer idPrueba;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;  // 'LIMITE_EXCEDIDO', 'ZONA_PELIGROSA', 'PROMOCION'

    @Column(name = "MENSAJE", nullable = false)
    private String mensaje;

    @Column(name = "FECHA_HORA", nullable = false)
    private String fechaHora;

    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado = "ENVIADA"; // 'ENVIADA', 'LEIDA', 'PROCESADA'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRUEBA", insertable = false, updatable = false)
    private Prueba prueba;
    
    // Constructores
    public Notificacion() {
    }
    
    public Notificacion(Integer id, Integer idEmpleado, Integer idPrueba, String tipo, String mensaje, String fechaHora, String estado) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idPrueba = idPrueba;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }
    
    public Notificacion(Empleado empleado, String fechaHora, Integer id, Integer idEmpleado, Integer idPrueba, String mensaje, Prueba prueba, String tipo) {
        this.empleado = empleado;
        this.fechaHora = fechaHora;
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idPrueba = idPrueba;
        this.mensaje = mensaje;
        this.prueba = prueba;
        this.tipo = tipo;
    }

    // Getters y setters 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Integer idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "id=" + id +
                ", idEmpleado=" + idEmpleado +
                ", idPrueba=" + idPrueba +
                ", tipo='" + tipo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaHora='" + fechaHora + '\'' +
                ", estado='" + estado + '\'' +
                ", empleado=" + empleado +
                ", prueba=" + prueba +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notificacion)) return false;

        Notificacion that = (Notificacion) o;

        if (!id.equals(that.id)) return false;
        if (!idEmpleado.equals(that.idEmpleado)) return false;
        if (!tipo.equals(that.tipo)) return false;
        if (!mensaje.equals(that.mensaje)) return false;
        if (!fechaHora.equals(that.fechaHora)) return false;
        return estado.equals(that.estado);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + idEmpleado.hashCode();
        result = 31 * result + tipo.hashCode();
        result = 31 * result + mensaje.hashCode();
        result = 31 * result + fechaHora.hashCode();
        result = 31 * result + estado.hashCode();
        return result;
    }
}
