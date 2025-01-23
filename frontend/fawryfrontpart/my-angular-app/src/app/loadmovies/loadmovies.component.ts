import { Component } from '@angular/core';
import { MovieServiceService } from '../movie-service.service';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { response } from 'express';
import { NgIf,NgFor,NgClass } from '@angular/common';
import { ActivatedRoute ,Router} from '@angular/router'
import { movie } from '../movie';
import {MovieOMDBAPI} from "../MovieOMDBAPI";
@Component({
  selector: 'app-loadmovies',
  standalone: true,
  imports: [FormsModule,HttpClientModule,ReactiveFormsModule,NgIf,NgFor,CommonModule,NgClass],
  templateUrl: './loadmovies.component.html',
  styleUrl: './loadmovies.component.css'
})
export class LoadmoviesComponent {
movies: MovieOMDBAPI[] = [];
title:string = '';
  page:number = 0;
  totalPages:number = 0;
  totalElements:number = 0;

  constructor(private movieService: MovieServiceService,private router:ActivatedRoute,private route:Router) {}

  ngOnInit(): void {
    this.router.queryParams.subscribe(params => {
      this.title = params['title']; // 'title' is the query parameter key
    });
    this.loadMovies();
  }
  loadMovies():void{

    this.movieService.getpaginatedmoviesfromomdb(this.title,this.page).subscribe(

      (res: any) => {
        console.log('Received response:', res);
        this.totalPages = res.totalPages; // Total pages
        this.totalElements = res.totalElements;
        this.movies = res.Search;

      },
      (error) => {

        console.error('Error fetching movies from omdb api:', error);
      }
    );
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
  redirect(){
    this.route.navigate(['/deletemoviedashboard']);
  }

}
