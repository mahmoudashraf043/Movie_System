import {Component} from '@angular/core';
import {MovieServiceService} from '../movie-service.service';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule, NgClass, NgFor, NgIf} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {Router} from '@angular/router'

@Component({
  selector: 'app-createmovie',
  standalone: true,
  imports: [FormsModule, HttpClientModule, ReactiveFormsModule, NgIf, NgFor, CommonModule, NgClass],
  templateUrl: './addmovie.component.html',
  styleUrl: './addmovie.component.css'
})
export class addmovieComponent {
  movieForm!: FormGroup;

  constructor(private fb: FormBuilder, private MovieService: MovieServiceService, private router: Router) {
  }

  ngOnInit(): void {

    this.movieForm = this.fb.group({
      imdbId: ['', Validators.required],
    });

  }

  addMovie(): void {
    if (this.movieForm.valid) {
      this.MovieService.addMovie(this.movieForm.value).subscribe((res: any) => {
        this.router.navigate(['/deletemoviedashboard']);
      }, (error) => {
        console.log(error);
      });


    }
  }
}
