package edu.irving.spring.piloto;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class PilotoResource {
	
	@Autowired private PilotoRepository pilotoRepository;
	
	@GetMapping("/pilotos")
	public List<Piloto> devuelvePilotos() {
		return pilotoRepository.findAll();
	}

	@GetMapping("/pilotos/{Numero}")
	public Piloto devueltePiloto(@PathVariable long Numero) {
		Optional<Piloto> Piloto = pilotoRepository.findById(Numero);

		if (!Piloto.isPresent())
			throw new PilotoNotFoundException("Numero-" + Numero);

		return Piloto.get();
	}
	
	@DeleteMapping("/pilotos/{Numero}")
	public void deletePiloto(@PathVariable long Numero) {
		pilotoRepository.deleteById(Numero);
	}
	
	@PostMapping("/pilotos")
	public ResponseEntity<Object> createPiloto(@RequestBody Piloto Piloto) {
		Piloto savedPiloto = pilotoRepository.save(Piloto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Numero}")
				.buildAndExpand(savedPiloto.getNumero()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/pilotos/{Numero}")
	public ResponseEntity<Object> updatePiloto(@RequestBody Piloto Piloto, @PathVariable long Numero) {

		Optional<Piloto> PilotoOptional = pilotoRepository.findById(Numero);

		if (!PilotoOptional.isPresent())
			return ResponseEntity.notFound().build();

		Piloto.setNumero(Numero);
		
		pilotoRepository.save(Piloto);

		return ResponseEntity.noContent().build();
	}
	
}
