import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MovieDashboardComponent } from './movie-dashboard/movie-dashboard.component';

import { addmovieComponent } from './addmovie/addmovie.component';
import { DeletemovieComponent } from './deletemovie/deletemovie.component';
import { LoadmoviesComponent } from './loadmovies/loadmovies.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule ,HTTP_INTERCEPTORS} from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { MoviedetailComponent } from './moviedetail/moviedetail.component';
import { GetmoviesfromexternalapiComponent } from './getmoviesfromexternalapi/getmoviesfromexternalapi.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,FormsModule,ReactiveFormsModule/*BrowserModule*/,LoginComponent,MovieDashboardComponent,addmovieComponent,DeletemovieComponent,LoadmoviesComponent,MoviedetailComponent,GetmoviesfromexternalapiComponent],

  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'my-angular-app';
}
