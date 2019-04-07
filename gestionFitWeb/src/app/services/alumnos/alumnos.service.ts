import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User_Student } from './../../model/user-student';
import { HttpClient,HttpHeaders } from '@angular/common/http';

@Injectable()
export class AlumnosService {

  apiUrl : String="http://localhost:8080/api/";
  //apiUrl : String="https://gestion-fit-prod.herokuapp.com/api/";

  httpOptions: any;

  constructor(public http: HttpClient) {
    console.log('Hello UserProvider Provider');

  }


  getCalendar(): Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.get(this.apiUrl+"calendar", this.httpOptions);

  }

  getUsersStudents(gymId): Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};


  	return this.http.get(this.apiUrl+"alumnos/" + gymId, this.httpOptions);
  }

  getUsersInstructors(): Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};


  	return this.http.get(this.apiUrl+"instructores", this.httpOptions);
  }

  addNewUserStudent(newUser): Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};



  	return this.http.post(this.apiUrl+"alumno", newUser, this.httpOptions);
  }

  updateUser(user):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    return this.http.put(this.apiUrl + "user/" + user.id,user);
  }

  getUser(id): Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
  	return this.http.get(this.apiUrl+"user/"+id, this.httpOptions);
  }

  getTabla(id):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

  	return this.http.get(this.apiUrl+"user/"+id+"/table", this.httpOptions);
  }

  updateTable(id,measures):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

  	return this.http.put(this.apiUrl+"user/"+id+"/nuevaMedicion",measures,this.httpOptions);
  }

  getRutines(id):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.get(this.apiUrl+"user/"+id+"/rutinas",this.httpOptions);
  }

  updateRutines(id, rutines):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.put(this.apiUrl+"user/"+id+"/nuevasRutinas",rutines, this.httpOptions);
  }

  addLessons(id,lessons,diasElegidos):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.put(this.apiUrl + "addLessons/" + id + "/" + lessons,diasElegidos,this.httpOptions);
  }

  sendPromo(promo):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    console.log(promo);
    return this.http.post(this.apiUrl + "promo", promo, this.httpOptions);
  }

  addLessonsInstructor(id, dias):Observable<any> {
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    return this.http.put(this.apiUrl + "instructor/addDays/" + id, dias, this.httpOptions);
  }

  addNewInstructor(instructor):Observable<any> {
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    return this.http.post(this.apiUrl + "instructor", instructor, this.httpOptions);
  }

  getInstructorDays():Observable<any> {
    return this.http.get(this.apiUrl + "daysInstructor");
  }

  markAssist(id):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    return this.http.post(this.apiUrl + "assist/student/" + id,this.httpOptions);
  }

  getReport(id):Observable<any>{
    this.httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    return this.http.get(this.apiUrl + "/user/assistReport/" + id);
  }

}
