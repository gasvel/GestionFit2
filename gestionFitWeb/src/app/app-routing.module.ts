import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlumnosComponent } from './alumnos/alumnos.component';
import { LoginComponent } from './login/login.component';
import { NuevoAlumnoComponent } from './nuevo-alumno/nuevo-alumno.component';
import { RegistroGymComponent } from './registro-gym/registro-gym.component';
import { PagosComponent } from './pagos/pagos.component';

const routes: Routes = [
  {path:"",component:LoginComponent},
  {path:"alumnos",component:AlumnosComponent},
  {path:"alumnos/nuevo",component:NuevoAlumnoComponent},
  {path:"registro",component:RegistroGymComponent},
  {path:"pagos",component:PagosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
