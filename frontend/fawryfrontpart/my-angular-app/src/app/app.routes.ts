import {RouterModule, Routes} from '@angular/router';

import {NgModule} from '@angular/core';
import {LoginComponent} from './login/login.component';
import {MovieDashboardComponent} from './movie-dashboard/movie-dashboard.component';

import {addmovieComponent} from './addmovie/addmovie.component';
import {DeletemovieComponent} from './deletemovie/deletemovie.component';
import {LoadmoviesComponent} from './loadmovies/loadmovies.component';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
//import { JwtInterceptor } from './jwt.interceptor';
import {MoviedetailComponent} from './moviedetail/moviedetail.component';
import {GetmoviesfromexternalapiComponent} from './getmoviesfromexternalapi/getmoviesfromexternalapi.component';

export const routes: Routes = [{path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent}, {
    path: 'moviedashboard',
    component: MovieDashboardComponent
  }, {path: 'addmoviedashboard', component: addmovieComponent}, {
    path: 'deletemoviedashboard',
    component: DeletemovieComponent
  }, {path: 'loadmoviedashboard', component: LoadmoviesComponent}, {
    path: 'moviedetail/:id',
    component: MoviedetailComponent
  }, {path: 'loadmovies', component: GetmoviesfromexternalapiComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes), HttpClientModule, BrowserModule],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
