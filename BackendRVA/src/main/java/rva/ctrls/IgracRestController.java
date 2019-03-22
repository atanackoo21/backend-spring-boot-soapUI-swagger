package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Igrac;
import rva.reps.IgracRepository;

@RestController
public class IgracRestController {
	
	@Autowired
	private IgracRepository igracRepository;
	
	@GetMapping("/")
	public String opet(){
		return "Hello";
	}
	
	@GetMapping("/igraci")
	public Collection<Igrac> getIgraci(){
		return igracRepository.findAll();
	}
	
	@GetMapping("/igracId/{id}")
	public Igrac getIgrac (@PathVariable Integer id) {
		return igracRepository.getOne(id);
	}
	
	@GetMapping("/igrac/{prezime}")
	public Collection<Igrac> getByPrezime(@PathVariable String prezime) {
		return igracRepository.findByPrezimeContainingIgnoreCase(prezime);
	}
	
	@DeleteMapping("/igrac/{id}")
	public ResponseEntity<HttpStatus> deleteIgraca(@PathVariable Integer id){
		if (igracRepository.existsById(id)) {
			igracRepository.deleteById(id);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping ("/igrac/{igrac}")
	public ResponseEntity<HttpStatus> addIgraca(@PathVariable Igrac igrac){
		if (igracRepository.existsById(igrac.getId())) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
		}
		igracRepository.save(igrac);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PutMapping ("/igrac/{igrac}")
	public ResponseEntity<HttpStatus> updateIgraca(@PathVariable Igrac igrac){
		if (igracRepository.existsById(igrac.getId())) {
			igracRepository.save(igrac);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);

	}

}
