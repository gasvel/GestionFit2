package app.model;

import java.util.ArrayList;
import java.util.List;

public class MeasurementsAdapter {
	
	public long day;
	public List<Measurement> measures;
	
	public MeasurementsAdapter() {
		this.measures = new ArrayList<Measurement>();
	}

	
	public MeasurementsAdapter(long dayM,List<Measurement> measuresM) {
		this.day = dayM;
		this.measures = measuresM;
	}
}
