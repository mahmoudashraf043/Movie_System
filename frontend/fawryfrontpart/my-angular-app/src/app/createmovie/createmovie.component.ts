import { Component } from '@angular/core';
import { MovieServiceService } from '../movie-service.service';
import { FormsModule,ReactiveFormsModule,FormBuilder, FormGroup,Validators } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { response } from 'express';
import { NgIf,NgFor,NgClass } from '@angular/common';
import { Router } from '@angular/router'
@Component({
  selector: 'app-createmovie',
  standalone: true,
  imports: [FormsModule,HttpClientModule,ReactiveFormsModule,NgIf,NgFor,CommonModule,NgClass],
  templateUrl: './createmovie.component.html',
  styleUrl: './createmovie.component.css'
})
export class CreatemovieComponent {
  movieForm!: FormGroup;
  constructor(private fb: FormBuilder,private MovieService:MovieServiceService,private router:Router) {}
  ngOnInit(): void {

    this.movieForm = this.fb.group({
      imdbId: ['', Validators.required],
    });

  }
  addMovie():void {
    if (this.movieForm.valid) {
      this.MovieService.addMovie(this.movieForm.value).subscribe((res:any)=>{
        this.router.navigate(['/deletemoviedashboard']);
      },(error)=>{
        console.log(error);
      });




    }
  }
}
