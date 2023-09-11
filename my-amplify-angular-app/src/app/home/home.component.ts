import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  username: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.getCurrentUser()
      .then(user => {
        this.username = user ? user.username : '';
      })
      .catch(error => {
        console.error('Error getting current user:', error);
      });
  }

  async onLogout(): Promise<void> {
    try {
      await this.authService.signOut();
      // Redirect to the login page on successful logout
      this.router.navigate(['/login']);
    } catch (error) {
      console.error('Logout error:', error);
    }
  }

}
