import { Component, OnInit } from '@angular/core';
import { AlumnosService } from '../services/alumnos/alumnos.service';
import { Router } from '@angular/router';
declare var jquery:any;
declare var $ :any;

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

  alumnos=[];
  alumnoSel={};
  constructor(private alumnosServ : AlumnosService,private routerServ: Router) { }

  ngOnInit() {
    this.alumnosServ.getUsersStudents(JSON.parse(localStorage.getItem('gym')).id).subscribe(
      res => {this.alumnos=res},
      err => {console.log(err)}
    );
  }


  gymName(){
    return JSON.parse(localStorage.getItem("gym")).name;
  }

  detalleAlumno(alumno){
    this.alumnoSel = alumno;
    $('#modalAlumno').modal('show');
  }

  nuevoAlumno(){
    this.routerServ.navigate(["/alumnos/nuevo"]);
  }

  mostrarAlertaSucc(){
    $('#alertSucc').alert();

  }

  mostrarAlertaErr(){
    $('#alertErr').alert();

  }

  marcarAsistenciaAlumno(alumno){
    this.alumnosServ.markAssist(alumno.id).subscribe(
      ()=> this.mostrarAlertaSucc(),
      err => this.mostrarAlertaErr()
    );
  }

  reporteAsistencia(alumno){
    this.alumnosServ.getReport(alumno.id).subscribe(
      res => {return res},
      error => console.log(error)
    )
  }

}
