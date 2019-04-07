package app.persistence;

import org.springframework.stereotype.Repository;

import app.model.Measure;

@Repository
public class MeasureDAO extends GenericDAO<Measure>{
	
	public MeasureDAO() {
		super(Measure.class);
	}

}
