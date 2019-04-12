import { Component, OnInit } from '@angular/core';
import {LoginService} from '../login.service';
import {FormGroup,FormControl,Validators,FormBuilder} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'] 
})
export class LoginComponent implements OnInit {

  credentials : FormGroup= this.formBuilder.group({
    email: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  });
  public user: any;
  errorLogin = false;

  constructor(private loginServ: LoginService, private routerServ: Router,private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.loginServ.auth().subscribe(
      () => {this.routerServ.navigate(["/alumnos"]);},
      error => {console.log("Not logged");console.log(error);}
    );

  }

  onSubmit() { this.login(); }

  login() {
    //this.spinner.show();
    this.loginServ.logIn(this.credentials.value).subscribe(
      result => {
        console.log(result.body);
        let userJson = result.body[1];
        localStorage.setItem("user",JSON.stringify(userJson));
        localStorage.setItem("gym",JSON.stringify(result.body[2]));
        localStorage.setItem("token",result.body[0].token);
        //this.nameServ.setGymName(result.body.gymId);
        if(userJson.role === "ADMIN"){
          //this.routerServ.navigate(['/instructores']);
        } else {
          this.routerServ.navigate(['/alumnos']);
        }
        //this.spinner.hide();
      },
      error => {
        {if(error.status == 401){this.errorLogin=true;};console.log(error);};
      }
      );
  }

  isLogged() {
    return localStorage.getItem("token") != null;
  }

  registro(){
    this.routerServ.navigate(['/registro']);
  }

}
