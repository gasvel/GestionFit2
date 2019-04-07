package app.persistence;

import org.springframework.stereotype.Repository;

import app.model.MeasuringTable;

@Repository
public class MeasuringTableDAO extends GenericDAO<MeasuringTable>{

	public MeasuringTableDAO() {
		super(MeasuringTable.class);
	}
}
