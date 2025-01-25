import {Component} from '@angular/core';
import {Router} from '@angular/router'
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {AuthserviceService} from '../authservice.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {


  user = {
    id: 0,
    username: '',
    password: '',
  }

  constructor(private router: Router, private authService: AuthserviceService) {
  }

  onSubmit() {
    this.authService.login(this.user).subscribe(
      (response) => {
        // After successful login, store the JWT token in localStorage
        this.authService.storeToken(response.accessToken);
        this.authService.storeRole(response.role);
        console.log(response.role);
        console.log(response.accessToken);
        if (response.role == "admin") {
          this.router.navigate(['deletemoviedashboard']);
        }
        if (response.role == "user") {
          this.router.navigate(['/moviedashboard']);
        }

        // Handle successful login logic (redirect user, etc.)
      },
      (error) => {
        console.error('Login failed', error);
        alert('Invalid credentials');
      }
    );
  }
}
