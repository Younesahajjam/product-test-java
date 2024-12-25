import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  // Enregistrer le token dans le localStorage
  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // Obtenir le token depuis le localStorage
  getToken(): string | null {
    const token = localStorage.getItem('token');
    console.log('Token retrieved from localStorage:', token);
    return token;
  }

  // Vérifier si le token est valide
  isTokenNotValid(): boolean {
    return !this.isTokenValid();
  }

  // Vérifier si le token est valide en fonction de son expiration
  isTokenValid(): boolean {
    const token = this.getToken();  // Récupérer le token
    if (!token) {
      return false;
    }
    const jwtHelper = new JwtHelperService();
    const isTokenExpired = jwtHelper.isTokenExpired(token); // Vérifier si le token est expiré
    if (isTokenExpired) {
      localStorage.clear();  // Si le token est expiré, nettoyer le localStorage
      return false;
    }
    return true;
  }

  // Supprimer le token du localStorage
  removeToken(): void {
    localStorage.removeItem('token');
  }
}
