import { Injectable } from '@angular/core';
import { Auth } from 'aws-amplify';
import { Router } from '@angular/router'; // Import the Router for redirection

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router) { }

  async login(username: string, password: string): Promise<void> {
    try {
      await Auth.signIn(username, password);
      this.router.navigate(['/home']); // Redirect to the home page after login
    } catch (error: any) { // Explicitly type 'error' as 'any'
      console.error('Error signing in:', error);
      if (error.code === 'UserNotFoundException') {
        throw new Error('User not found. Please check your username or sign up.');
      } else {
        throw error; // Rethrow the error to handle it in the component
      }
    }
  }

  async signUp(username: string, password: string, email: string): Promise<any> {
    try {
      const result = await Auth.signUp({
        username,
        password,
        attributes: {
          email
        }
      });
      return result;
    } catch (error) {
      console.error('Error signing up:', error);
      throw error;
    }
  }

  async confirmSignUp(username: string, code: string): Promise<any> {
    try {
      const result = await Auth.confirmSignUp(username, code);
      return result;
    } catch (error) {
      console.error('Error confirming sign-up:', error);
      throw error;
    }
  }

  async signOut(): Promise<void> {
    try {
      await Auth.signOut();
      // Handle successful logout
      this.router.navigate(['/login']); // Redirect to the login page after logout
    } catch (error) {
      console.error('Error signing out:', error);
      throw error;
    }
  }

  async getCurrentUser(): Promise<any> {
    try {
      const user = await Auth.currentAuthenticatedUser();
      return user;
    } catch (error) {
      console.error('No user signed in:', error);
      return null;
    }
  }
}
