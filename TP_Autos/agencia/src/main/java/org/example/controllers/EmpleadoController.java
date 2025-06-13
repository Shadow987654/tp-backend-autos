package tpv.agencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/empleados")
public class Empleado Controller {

    private final EmpleadoServive empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }
}