package app.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.exception.NotFoundException;
import app.model.Exercise;
import app.model.Exercise_Type;
import app.model.Routine;
import app.model.Routine_Type;
import app.service.RoutineService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RoutineController {
	
	@Autowired
	private RoutineService routineServ = new RoutineService();
	

	@GetMapping(value = "/routines", produces = "application/json")   
	public List<Routine> getRoutines() {
		return this.routineServ.getAll();

	}
	
	@GetMapping(value = "/routines/templates", produces = "application/json")   
	public List<Routine> getRoutinesTemplates() {
		return this.routineServ.getAllTemplates();

	}
	
	@GetMapping(value = "/routines/types", produces = "application/json")   
	public Routine_Type[] getRoutinesTypes() {
		return Routine_Type.values();

	}
	
	@GetMapping(value = "/exercises/types", produces = "application/json")   
	public Exercise_Type[] getExercisesTypes() {
		return Exercise_Type.values();

	}

	@GetMapping(value = "/exercises", produces = "application/json")   
	public List<Exercise> getExercises(){
		return this.routineServ.getExercises();
	}
	
	@GetMapping(value = "/exercises/templates", produces = "application/json")   
	public List<Exercise> getExercisesTemplates(){
		return this.routineServ.getExercisesTemplates();
	}
	
	@GetMapping(value= "/exercise/{id}",produces= "application/json")
	public Exercise getExercise(@PathVariable("id") Long idExercise){
		Exercise exercise = this.routineServ.getExercise(idExercise);
		if(exercise == null) {
			throw new NotFoundException("Ejercicio no encontrado");
		}
		
		return exercise;
	}

	@PostMapping(value = "/routine", produces = "application/json")
	public ResponseEntity<Void> createRoutine(@RequestBody Routine routine) {
			this.routineServ.save(routine);
			
			return new ResponseEntity<Void>(HttpStatus.CREATED);

	}
	
	@PostMapping(value = "/exercise", produces = "application/json")   
	public ResponseEntity<Void> createExercise(@RequestBody Exercise exercise) {
			this.routineServ.saveExercise(exercise);
			return new ResponseEntity<Void>(HttpStatus.CREATED);

	}
	
	@GetMapping(value= "/routine/{id}",produces= "application/json")
	public Routine getRoutine(@PathVariable("id") Long idRoutine){
		Routine routine = this.routineServ.getById(idRoutine);
		if(routine == null) {
			throw new NotFoundException("Rutina no encontrada");
		}
		
		return routine;
	}
	
	@PutMapping(value = "/routine/{id}",produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> updateRoutine(@PathVariable("id") Long idRoutine,@RequestBody Routine routine){
		Routine existingRoutine = this.routineServ.getById(idRoutine);
		if(existingRoutine == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		routine.id = (idRoutine);
		this.routineServ.update(routine);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/exercise/{id}",produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> updateExercise(@PathVariable("id") Long idExercise,@RequestBody Exercise exercise){
		Exercise existingExercise = this.routineServ.getExercise(idExercise);
		if(existingExercise == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		exercise.id = (idExercise);
		this.routineServ.updateExercise(exercise);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/routine/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteRoutine(@PathVariable("id") Long idRoutine){
		this.routineServ.delete(this.routineServ.getById(idRoutine));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/exercise/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteExercise(@PathVariable("id") Long idExercise){
		this.routineServ.deleteExercise(this.routineServ.getExercise(idExercise));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
