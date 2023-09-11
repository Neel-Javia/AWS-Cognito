import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  username: string = ''; 
  password: string = ''; 
  email: string = '';    
  verificationCode: string = ''; 
  signUpSuccessMessage: string = '';
  signUpErrorMessage: string = '';
  confirmSuccessMessage: string = '';
  confirmErrorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { } 

  async signUp(): Promise<void> {

    try {
      if (!this.username || !this.password || !this.email) {
        this.signUpErrorMessage = 'Please fill in all required fields.';
        return;
      }
      if (this.password.length < 8) {
        this.signUpErrorMessage = 'Password must be at least 8 characters long.';
        return;
      }

      await this.authService.signUp(this.username, this.password, this.email);
      this.signUpSuccessMessage = 'Sign-up successful. Check your email for a verification code.';
      this.signUpErrorMessage = '';
    } catch (error) {
      if (typeof error === 'string') {
        this.signUpErrorMessage = error;
      } else {
        this.signUpErrorMessage = 'An unknown error occurred during sign-up.';
      }
    }
  }

  async confirmSignUp(): Promise<void> {
    try {
      await this.authService.confirmSignUp(this.username, this.verificationCode);
      this.confirmSuccessMessage = 'Email verification successful. You can now login.';
      this.confirmErrorMessage = '';
      this.router.navigate(['/login']);
    } catch (error) {
      if (typeof error === 'string') {
        this.confirmErrorMessage = error; 
      } else {
        this.confirmErrorMessage = 'An unknown error occurred during confirmation.';
      }
    }
    
  }
}
