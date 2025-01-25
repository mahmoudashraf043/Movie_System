import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router'
//import { response } from 'express';
import {CommonModule, NgClass, NgFor, NgIf} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

//import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {MovieServiceService} from '../movie-service.service';
import {movie} from "../movie";

@Component({
  selector: 'app-moviedetail',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, NgIf, NgFor, CommonModule, NgClass, HttpClientModule],
  templateUrl: './moviedetail.component.html',
  styleUrl: './moviedetail.component.css'
})
export class MoviedetailComponent {
  movie: movie | undefined;
  movieId!: number

  constructor(private movieservice: MovieServiceService, private router: ActivatedRoute, private route: Router) {
  }

  ngOnInit(): void {
    this.movieId = +this.router.snapshot.paramMap.get('id')!;
    this.fetchMovieDetails();
  }

  fetchMovieDetails(): void {
    this.movieservice.getmovie(this.movieId).subscribe(
      (res: any) => {

        this.movie = res;

      },
      (error) => {

        console.error('Error fetching movies:', error);
      }
    );
  }

  redirect(): void {

    this.route.navigate(['/moviedashboard']);
  }

}
