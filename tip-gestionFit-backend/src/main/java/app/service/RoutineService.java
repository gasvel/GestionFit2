package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.Exercise;
import app.model.Routine;
import app.persistence.ExerciseDAO;
import app.persistence.RoutineDAO;

@Service
public class RoutineService {

	@Autowired
	private RoutineDAO routineDAO;
	
	@Autowired
	private ExerciseDAO exerciseDAO;
	
	public RoutineService(){
		this.routineDAO = new RoutineDAO();
	}
	
	@Transactional
	public void save(Routine newRoutine){
		ArgumentsValidator.validateRoutine(newRoutine);
		this.routineDAO.save(newRoutine);
	}
	
	@Transactional
	public void saveExercise(Exercise newExercise){
		ArgumentsValidator.validateExercise(newExercise);
		this.exerciseDAO.save(newExercise);
	}
	
	@Transactional
	public void update(Routine newRoutine){
		ArgumentsValidator.validateRoutine(newRoutine);
		this.routineDAO.update(newRoutine);
	}
	
	@Transactional
	public void delete(Routine routine){
		this.routineDAO.delete(routine);
	}
	
	@Transactional
	public Routine getById(Long id){
		return this.routineDAO.getById(id);
	}
	
	@Transactional
	public List<Routine> getAll(){
		return this.routineDAO.getAll();
	}
	
	@Transactional
	public List<Routine> getAllTemplates(){
		return this.routineDAO.getAllTemplates();
	}

	@Transactional
	public List<Exercise> getExercises() {
		return this.exerciseDAO.getAll();
	}
	
	@Transactional
	public Exercise getExercise(Long id) {
		return this.exerciseDAO.getById(id);
	}
	
	@Transactional
	public void updateExercise(Exercise newExercise){
		ArgumentsValidator.validateExercise(newExercise);
		this.exerciseDAO.update(newExercise);
	}

	@Transactional
	public List<Exercise> getExercisesTemplates() {
		return this.exerciseDAO.getAllTemplates();

	}
	
	@Transactional
	public void deleteExercise(Exercise exercise){
		this.exerciseDAO.delete(exercise);
	}
	
}
