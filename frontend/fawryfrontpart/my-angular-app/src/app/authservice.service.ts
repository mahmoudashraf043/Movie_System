import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './User';
import { UserCredentials } from './UserCredentials';
import { response } from 'express';
import{Role} from './Role'
import { BehaviorSubject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {
  private apiUrl = 'http://localhost:8080/login';
  private tokenSubject = new BehaviorSubject<string | null>(this.getToken());
  token$ = this.tokenSubject.asObservable();
  constructor(private http: HttpClient) { }
  login(user:User): Observable<UserCredentials> {
     return this.http.post<UserCredentials>(this.apiUrl,user);
  }
  storeToken(token: string): void {
    localStorage.setItem('jwt_token', token);
    this.tokenSubject.next(token);
  }
  storeRole(role:Role):void{
    localStorage.setItem('role', role);
  }

  // Retrieve token from localStorage
  getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }


}
