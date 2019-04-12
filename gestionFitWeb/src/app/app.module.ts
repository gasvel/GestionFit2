import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlumnosComponent } from './alumnos/alumnos.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login.service';
import { AlumnosService } from './services/alumnos/alumnos.service';
import { HttpClientModule } from '@angular/common/http';
import { NuevoAlumnoComponent } from './nuevo-alumno/nuevo-alumno.component';
import { PagosComponent } from './pagos/pagos.component';
import { ClasesComponent } from './clases/clases.component';
import { AgregarClasesAlumnoComponent } from './agregar-clases-alumno/agregar-clases-alumno.component';
import { RegistroGymComponent } from './registro-gym/registro-gym.component';


@NgModule({
  declarations: [
    AppComponent,
    AlumnosComponent,
    LoginComponent,
    NuevoAlumnoComponent,
    PagosComponent,
    ClasesComponent,
    AgregarClasesAlumnoComponent,
    RegistroGymComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [LoginService, AlumnosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
