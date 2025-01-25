import {Component} from '@angular/core';
import {MovieServiceService} from '../movie-service.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule, NgClass, NgFor, NgIf} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {Router} from '@angular/router'
import {movie} from "../movie";

@Component({
  selector: 'app-deletemovie',
  standalone: true,
  imports: [FormsModule, HttpClientModule, ReactiveFormsModule, NgIf, NgFor, CommonModule, NgClass],
  templateUrl: './deletemovie.component.html',
  styleUrl: './deletemovie.component.css'
})
export class DeletemovieComponent {
  movies: movie[] = [];

  page: number = 0;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(private movieService: MovieServiceService, private router: Router) {
  }

  ngOnInit(): void {

    this.loadMovies();
  }

  loadMovies(): void {

    this.movieService.getpaginatedmovies(this.page).subscribe(
      (res: any) => {
        console.log(res);
        this.movies = res.content;
        this.totalPages = res.totalPages;
        this.totalElements = res.totalElements;
      },
      (error) => {

        console.error('Error fetching movies:', error);
      }
    );
  }

  addmovie(): void {
    this.router.navigate(['/addmoviedashboard'])
  }

  loadmovie(): void {
    this.router.navigate(['/loadmovies']);

  }

  deletemovie(id: number): void {
    this.movieService.deleteMovie(id).subscribe(
      (res: any) => {
        window.location.reload();
      },
      (error) => {

        console.error('Error deleting movie:', error);
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

}
