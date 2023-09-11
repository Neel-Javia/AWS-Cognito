import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username!: string;
  password!: string;
  loginErrorMessage!: string;

  constructor(private authService: AuthService, private router: Router) {
    this.password = '';
    this.loginErrorMessage = '';
  }

  async login(): Promise<void> {
    if (!this.username || !this.password) {
      this.loginErrorMessage = 'Both username and password are required.';
      return;
    }

    if (this.password.length < 8) {
      this.loginErrorMessage = 'Password must be at least 8 characters long.';
      return;
    }

    try {
      await this.authService.login(this.username, this.password);
      // Redirect to the home page or another appropriate route on successful login
      this.router.navigate(['/home']);

    } catch (error) {
      this.loginErrorMessage = 'Invalid username or password.';
      console.error('Login error:', error);
    }
  }

}
