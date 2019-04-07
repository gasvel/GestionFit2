export class Exercise {
	name: String;
	description: String;
	type: Exercise_Type;
	checked: Boolean;

	constructor(nameE:String,descriptionE:String,typeE:Exercise_Type){
		this.name = nameE;
		this.description = descriptionE;
		this.type = typeE;
		this.checked = false;
	}
}

export enum Exercise_Type{
	Upper_Body,
	Lower_Body,
	Arms,
	Warm_Up
}