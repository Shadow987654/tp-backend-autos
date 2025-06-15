package org.example.services;

import org.example.common.models.Interesado;
import org.example.repositories.InteresadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteresadoService {

    private final InteresadoRepository interesadoRepository;

    @Autowired
    public InteresadoService(InteresadoRepository interesadoRepository) {
        this.interesadoRepository = interesadoRepository;
    }

    public List<Interesado> getAllInteresados() {
        return interesadoRepository.findAll();
    }

    public Optional<Interesado> getInteresadoById(Integer id) {
        return interesadoRepository.findById(id);
    }

    public Interesado saveInteresado(Interesado interesado) {
        return interesadoRepository.save(interesado);
    }

    public void deleteInteresado(Integer id) {
        interesadoRepository.deleteById(id);
    }
}
