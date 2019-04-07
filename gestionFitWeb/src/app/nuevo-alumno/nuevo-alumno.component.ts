import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormGroup,FormControl,Validators,FormBuilder} from '@angular/forms';
import { LoginService } from '../login.service';


@Component({
  selector: 'app-nuevo-alumno',
  templateUrl: './nuevo-alumno.component.html',
  styleUrls: ['./nuevo-alumno.component.css']
})
export class NuevoAlumnoComponent implements OnInit {

  public alumno: FormGroup = this.formBuilder.group({
    nameAndSurname: new FormControl('',Validators.compose([
        Validators.minLength(6),
        Validators.required
      ])),
    mail: new FormControl('',Validators.compose([
        Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$'),
        Validators.required
      ])),
    weigth: new FormControl('',Validators.compose([
        Validators.min(30),
        Validators.max(150),
        Validators.required
      ])),
    birthday: new FormControl('',Validators.compose([
        Validators.nullValidator,
        Validators.required
      ])),
    objective: new FormControl('',Validators.required),
    telephone: new FormControl('',Validators.compose([
        Validators.minLength(8),
        Validators.required    ])),
    observations:new FormControl('',Validators.compose([
        Validators.minLength(0)
      ])),
    pathologies: new FormControl('',Validators.compose([
        Validators.minLength(0)
      ]))
    });
  constructor(private routerServ: Router,private formBuilder: FormBuilder,private serviceLogin : LoginService) { }

  ngOnInit() {
  }

  guardar(){
    let nuevoAlumnoJ = this.alumno.value;
    nuevoAlumnoJ.gym= JSON.parse(localStorage.getItem("gym")).id;
    this.serviceLogin.signup(nuevoAlumnoJ).subscribe(
      () => {
        this.routerServ.navigate(['/alumnos']);
      }
      ,err => {console.log(err);})
  }

}
