import { Component } from '@angular/core';
import { Router } from '@angular/router';
declare var jquery:any;
declare var $ :any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'GestionFit';
  name= 'Gaston';

  constructor(private routerServ : Router){}

  isLogged(){
    return (localStorage.getItem("token") != null);
  }


    userName(){
      return JSON.parse(localStorage.getItem("user")).nameAndSurname;
    }

  alumnos(){
    this.routerServ.navigate(['alumnos']);
  }

  cerrarSidebar(){
    $('#sidebar, #content').toggleClass('active');
    $('.collapse.in').toggleClass('in');
    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
  }

  logout(){
    localStorage.clear();
    this.cerrarSidebar();
    this.routerServ.navigate(['']);
  }
}
