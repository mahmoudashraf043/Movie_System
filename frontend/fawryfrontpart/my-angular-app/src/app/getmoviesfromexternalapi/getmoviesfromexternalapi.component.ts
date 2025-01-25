import {Component} from '@angular/core';
import {MovieServiceService} from '../movie-service.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule, NgClass, NgFor, NgIf} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-getmoviesfromexternalapi',
  standalone: true,
  imports: [FormsModule, HttpClientModule, ReactiveFormsModule, NgIf, NgFor, CommonModule, NgClass],
  templateUrl: './getmoviesfromexternalapi.component.html',
  styleUrl: './getmoviesfromexternalapi.component.css'
})
export class GetmoviesfromexternalapiComponent {
  movies: any[] = [];
  movietitle = ''

  constructor(private movieService: MovieServiceService, private router: Router) {
  }

  ngOnInit(): void {

  }

  loadMovies(): void {

    console.log(this.movietitle)
    this.router.navigate(['loadmoviedashboard'], {queryParams: {title: this.movietitle}});

  }


}
