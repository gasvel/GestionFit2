import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  apiUrl: String = "http://localhost:8080/";
  httpOptions: any;

  constructor(public http: HttpClient) {
    console.log('Hello LoginProvider Provider');
  }

  logIn(cred): Observable<any> {
    return this.http.post(this.apiUrl + "auth/login", cred,{observe: 'response'});
  }

  auth(): Observable<any> {
    if(localStorage.getItem("token")!=null){
      this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
      return this.http.get(this.apiUrl + "authenticate/instructor",this.httpOptions);
    }
    else{
      return this.http.get(this.apiUrl + "authenticate/instructor")
    }
    
  }

  signup(newUser): Observable<any>{

 
  	return this.http.post(this.apiUrl+"auth/signup", newUser);
  }
}
