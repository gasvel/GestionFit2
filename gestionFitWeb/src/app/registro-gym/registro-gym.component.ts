import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormGroup,FormControl,Validators,FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-registro-gym',
  templateUrl: './registro-gym.component.html',
  styleUrls: ['./registro-gym.component.css']
})
export class RegistroGymComponent implements OnInit {
  public register: FormGroup = this.formBuilder.group({
    nameAndSurname: new FormControl('',Validators.compose([
        Validators.minLength(6),
        Validators.required
      ])),
    mail: new FormControl('',Validators.compose([
        Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$'),
        Validators.required
      ])),
    gym: new FormControl('',Validators.required),

    });
  constructor(private routerServ: Router,private formBuilder: FormBuilder) { }

  ngOnInit() {
  }

  guardar(){

  }

}
