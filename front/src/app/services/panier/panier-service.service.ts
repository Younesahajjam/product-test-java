import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PanierServiceService {

  private totalItemsSubject = new BehaviorSubject<number>(0); // Stockage du total des produits
  totalItems$ = this.totalItemsSubject.asObservable(); // Observable pour suivre les mises à jour

  // Méthode pour mettre à jour la quantité d'articles dans le panier
  updateTotalItems(count: number) {
    this.totalItemsSubject.next(count);
  }
}
