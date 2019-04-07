import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlumnosComponent } from './alumnos/alumnos.component';
import { LoginComponent } from './login/login.component';
import { NuevoAlumnoComponent } from './nuevo-alumno/nuevo-alumno.component';

const routes: Routes = [
  {path:"",component:LoginComponent},
  {path:"alumnos",component:AlumnosComponent},
  {path:"alumnos/nuevo",component:NuevoAlumnoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
