import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import { AuthenticationRequest } from 'app/services/models';
import { AuthService } from 'app/services/services';
import { TokenService } from 'app/services/token/token.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(
  private router: Router,
  private authServices: AuthService,
  private tokenService: TokenService
  ){}
  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = []; 

  
  login() {
    const email = this.authRequest.email;
    
    // Appel au service d'authentification
    this.authServices.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
        console.log('Token reçu du backend: ', res.token);

        // Enregistrer le token reçu dans le localStorage et le service TokenService
        this.tokenService.setToken(res.token as string);

        const token = res.token || '';  // Obtenir le token ou une chaîne vide si null
        localStorage.setItem('auth_token', token);

        // Redirection basée sur l'email de l'utilisateur
        if (email === 'admin@admin.com') {
          this.router.navigate(['products']);  // Redirection vers la page admin
        } else {
          this.router.navigate(['products_clients']);  // Redirection vers la page client
        }
      },
      error: (err) => {
        console.log('Erreur lors de l\'authentification: ', err);
        // Gérer les erreurs (par exemple, afficher un message d'erreur)
        this.errorMsg.push('Erreur de connexion. Veuillez vérifier vos informations.');
      }
    });
  }

  register(){
    this.router.navigate(['register']);
  }

}
