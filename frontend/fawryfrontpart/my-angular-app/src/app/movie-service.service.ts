import { Injectable,Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import{movie} from './movie';
import { HttpHeaders,HttpParams } from '@angular/common/http';
import { AuthserviceService } from './authservice.service';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';
import { MovieOMDBAPI } from './MovieOMDBAPI';
import { PaginatedResponse } from './PaginatedResponse';
@Injectable({
  providedIn: 'root'
})
export class MovieServiceService {

  private apiUrl = 'http://localhost:8080/movie';
  constructor(private http: HttpClient, private auth:AuthserviceService,@Inject(PLATFORM_ID) private platformId: Object) { }


  getmovie(id:number): Observable<movie[]>{
    const token=this.auth.getToken()
    console.log(id)
   const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`,  // Attach the JWT token to the request header
    'Content-Type': 'application/json'
  });
    return this.http.get<movie[]>(`${this.apiUrl}/${id}`,{headers});
  }

  getpaginatedmovies(page: number=0):Observable<PaginatedResponse<movie>[]>{
    const token=this.auth.getToken()
    const params = new HttpParams()
    .set('pageNumber', page.toString());
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,  // Attach the JWT token to the request header
      'Content-Type': 'application/json'
    });
  return this.http.get<PaginatedResponse<movie>[]>(`${this.apiUrl}`,{params,headers})
  }

  getpaginatedmoviesfromomdb(title:string,page: number,):Observable<PaginatedResponse<MovieOMDBAPI>[]>{
    const token=this.auth.getToken()
    page = 1;
    console.log(token)
    const params = new HttpParams()
      .set('title',title)
    .set('pageNumber', page);

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,  // Attach the JWT token to the request header
      'Content-Type': 'application/json'
    });
  return this.http.get<PaginatedResponse<MovieOMDBAPI>[]>(`${this.apiUrl}/list`,{params,headers})
  }

  addMovie(movie: movie): Observable<string> {
    const token=this.auth.getToken()
   const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`,  // Attach the JWT token to the request header
    'Content-Type': 'application/json'
  });
    return this.http.post<string>(`${this.apiUrl}`, movie.imdbId,{headers});
  }
  deleteMovie(id: number): Observable<string> {
    const token=this.auth.getToken()
   const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`,  // Attach the JWT token to the request header
    'Content-Type': 'application/json'
  });
    return this.http.delete<string>(`${this.apiUrl}/${id}`,{headers});
  }

}
