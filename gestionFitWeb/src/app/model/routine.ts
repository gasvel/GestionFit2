import { Exercise } from './exercise'

export class Routine{
	name: String;
	creationDate: Date;
	type: Routine_Type;
	exercises: Array<Exercise>;
	checked: Boolean;

	constructor(nameR: String, creationDateR:Date,typeR:Routine_Type,exercisesR:Array<Exercise>){
		this.name = nameR;
		this.creationDate = creationDateR;
		this.type = typeR;
		this.exercises = exercisesR;
		this.checked = false;
	}  
}

export enum Routine_Type{
	Strength,
	Resistance,
	Strength_Resistance,
	Muscular_Definition,
	Explosive_Force
}