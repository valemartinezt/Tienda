
package com.tienda.service;
import com.tienda.entity.Persona;
import com.tienda.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PersonaService implements IPersonaService{
    
    @Autowired
    private PersonaRepository personaRepository;
    
    
    @Override
    public List<Persona> getAllPerson(){
        return(List<Persona>)personaRepository.findAll();
    }

    @Override
    public void savePerson(Persona persona){
        personaRepository.save(persona);
    }
    
    @Override
    public Persona getPersonById (long id){
        Persona persona;
        Optional<Persona> personaEncontrada = personaRepository.findById(id);
        if (personaEncontrada.isPresent()){
            persona  = new Persona(personaEncontrada.get());
            return persona;
        }
        return null;
    }
    
    @Override
    public void delete(long id){
        personaRepository.deleteById(id);
    }
     
    
}
