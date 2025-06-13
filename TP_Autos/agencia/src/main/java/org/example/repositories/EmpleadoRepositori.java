package tp.agencia.repositories;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findById(int legajo);

    List<Empleado> findAll();

    void deleteById(Long id);
}