import { Component,OnInit } from '@angular/core';
import { MovieServiceService } from '../movie-service.service';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { response } from 'express';
import { NgIf,NgFor,NgClass } from '@angular/common';
import { HttpHeaders } from '@angular/common/http';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';
import { Injectable,Inject } from '@angular/core';
import { AuthserviceService } from '../authservice.service';
import { Router } from '@angular/router'
import {movie} from "../movie";
@Component({
  selector: 'app-movie-dashboard',
  standalone: true,
  imports: [FormsModule,HttpClientModule,ReactiveFormsModule,NgIf,NgFor,CommonModule,NgClass],
  templateUrl: './movie-dashboard.component.html',
  styleUrl: './movie-dashboard.component.css'
})
export class MovieDashboardComponent {
  movies: movie[] = [];

  newMovie = {
    id:0,
    title: '',
    genre: '',
    year: '',
    description:'',
    poster:'',
    director:'',
  };
  page:number = 0;
  totalPages:number = 0;
  totalElements:number = 0;
  constructor(private movieService: MovieServiceService,@Inject(PLATFORM_ID) private platformId: Object,private auth:AuthserviceService,private router:Router) {}

  ngOnInit(): void {

    this.loadMovies();
  }
  loadMovies():void{
    let headers = new HttpHeaders();
       if (isPlatformBrowser(this.platformId)) {
        const tokenstr = 'Bearer ' + window.localStorage.getItem('jwt_token');
        if (tokenstr) {
          headers = headers.set('Authorization', tokenstr);
        }
      }
    this.movieService.getpaginatedmovies(this.page).subscribe(

      (res: any) => {

        this.movies = res.content;
        this.totalPages = res.totalPages; // Total pages
        this.totalElements = res.totalElements;

      },
      (error) => {
        const token1=this.auth.getToken();
        console.error('Error fetching movies:', error);
      }
    );
  }


 moviedetail(id:number):void{
  this.router.navigate(['/moviedetail',id]);
 }
 nextPage() {
  console.log(2)
  if (this.page < this.totalPages - 1) {
    this.page++;
    this.loadMovies();

  }

}

previousPage() {
  console.log("h");
  if (this.page > 0) {
    this.page--;
    this.loadMovies();
    console.log(this.page)
  }
}
}
