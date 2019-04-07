package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.Exercise;
import app.persistence.ExerciseDAO;

@Service
public class ExerciseService {

	@Autowired
	private ExerciseDAO exerciseDAO;
	
	public ExerciseService(){
		this.exerciseDAO = new ExerciseDAO();
	}
	
	@Transactional
	public void save(Exercise newExercise){
		ArgumentsValidator.validateExercise(newExercise);
		this.exerciseDAO.save(newExercise);
	}
	
	@Transactional
	public void update(Exercise newExercise){
		ArgumentsValidator.validateExercise(newExercise);
		this.exerciseDAO.update(newExercise);
	}
	
	@Transactional
	public void delete(Exercise exercise){
		this.exerciseDAO.delete(exercise);
	}
	
	@Transactional
	public Exercise getById(Long id){
		return this.exerciseDAO.getById(id);
	}
	
	@Transactional
	public List<Exercise> getAll(){
		return this.exerciseDAO.getAll();
	}
}
