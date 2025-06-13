package tpv.agencia.services;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado getEmpleadoById(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado not found with id " + id));
    }

    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> getEmpleadoById(int legajo) {
        return empleadoRepository.findById(legajo);
    }

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public void deleteEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}