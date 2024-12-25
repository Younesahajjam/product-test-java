import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ItemDto, PageItemDto } from 'app/services/models';
import { ItemControllerService } from 'app/services/services';

@Component({
  selector: 'app-product-admin',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './product-admin.component.html',
  styleUrl: './product-admin.component.css'
})
export class ProductAdminComponent implements OnInit {
  product: ItemDto = {};
  productId: number | undefined;
  isFormVisible: boolean = false;  // Variable de contrôle pour afficher/masquer le formulaire
  products: ItemDto[] | undefined= [];

  constructor(
    private itemService: ItemControllerService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getAllProducts();
    this.productId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.productId) {
      this.getProduct(this.productId);
    }
  }

  // Toggle l'affichage du formulaire
  toggleFormVisibility(): void {
    this.isFormVisible = !this.isFormVisible;
    if (!this.isFormVisible) {
      this.resetForm();
    }
  }

  // Méthode pour sauvegarder un produit
  saveProduct(): void {
    if (this.productId) {
      this.updateProduct(this.productId);
    } else {
      this.addProduct();
    }
    this.toggleFormVisibility(); // Cacher le formulaire après avoir sauvegardé
  }

  resetForm(): void {
    this.product = {}; // Réinitialiser le produit
  }

  // Ajouter un produit
  addProduct(): void {
    this.itemService.addItem({ body: this.product }).subscribe({
      next: (newProduct) => {
        console.log('Produit ajouté', newProduct);
        this.product = newProduct;
      },
      error: (err) => console.error('Erreur lors de l\'ajout du produit', err)
    });
  }

  // Méthode pour supprimer un produit
  deleteProduct(id: number): void {
    this.itemService.deleteItem({ itemId: id }).subscribe({
      next: () => {
        console.log('Produit supprimé');
        this.getAllProducts(); // Rafraîchir la liste des produits après suppression
      },
      error: (err) => console.error('Erreur lors de la suppression du produit', err)
    });
  }

  // Mettre à jour un produit
  updateProduct(id: number): void {
    this.itemService.updateItem({ itemId: id, body: this.product }).subscribe({
      next: (updatedProduct) => {
        console.log('Produit mis à jour', updatedProduct);
        this.product = updatedProduct;
      },
      error: (err) => console.error('Erreur lors de la mise à jour du produit', err)
    });
  }

  // Méthode pour récupérer tous les produits
  getAllProducts(): void {
    this.itemService.searchRejetMessages().subscribe({
      next: (response: PageItemDto) => {
        this.products = response.content;
      },
      error: (err) => console.error('Erreur lors de la récupération des produits', err)
    });
  }

  // Méthode pour récupérer un produit
  getProduct(id: number): void {
    this.itemService.getItem({ itemId: id }).subscribe({
      next: (product) => {
        this.product = product;
        this.isFormVisible = true;  // Afficher le formulaire si on édite un produit
      },
      error: (err) => console.error('Erreur lors de la récupération du produit', err)
    });
  }
}
