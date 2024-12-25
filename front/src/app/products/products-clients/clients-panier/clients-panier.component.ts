import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProductsCardComponent } from '../products-card/products-card.component';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { ItemDto, PageItemDto } from 'app/services/models';
import { ControllerCartService } from 'app/services/services';
import { TokenService } from 'app/services/token/token.service';


@Component({
  selector: 'app-clients-panier',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule,ProductsCardComponent,RouterOutlet],
  templateUrl: './clients-panier.component.html',
  styleUrl: './clients-panier.component.css'
})
export class ClientsPanierComponent implements OnInit {

  productRequest: ItemDto = {
      cartId: undefined,          
      category: '',               
      code: '',                   
      createdAt: undefined,       
      description: '',            
      image: '',                  
      internalReference: '',      
      inventoryStatus: undefined, 
      name: '',                   
      oid: undefined,             
      price: 0,                   
      quantity: undefined,       
      rating: undefined,          
      shellId: undefined,         
      updatedAt: undefined,       
    };

   productResponse:PageItemDto={};
    page=0;
    size=5;
    message='';
    level='success';
    errorMsg: Array<string> = [];
  
    constructor(
    private router: Router,
    private itemCartServices: ControllerCartService,
    private tokenService: TokenService,
    private activateRoute: ActivatedRoute
    ){}

    ngOnInit(): void {

    this.findAllProducts();
    
    }
private findAllProducts():void{
  const custmerId= this.activateRoute.snapshot.params['costumerId'];
  if (custmerId) {
    this.itemCartServices.getOrCreateActiveCart({
      'costumerId':custmerId
    }).subscribe({
      next: (product)=>{
        this.productRequest = product
       },
      error: (err:any)=>{
        this.errorMsg=err.error.validationErrors;
      }
    })
    
  }
}

deleteProduct(itemId: number): void {
  const cartId = this.productRequest.cartId;
  if (!cartId) {
    console.error("Cart ID is missing.");
    return;
  }

  this.itemCartServices.deleteProductFromCart({
    cartId: cartId,
    itemId: itemId
  }).subscribe({
    next: (response) => {
      this.message = 'Produit supprimé avec succès';
      this.findAllProducts(); 
    },
    error: (err) => {
      this.message = 'Erreur lors de la suppression du produit';
      console.error(err);
    }
  });
}




    gotToFirstPage(){
      this.page=0;
      this.findAllProducts();
    }
    
    gotToPreviousPage(){
      this.page--;
      this.findAllProducts();
    }
    
    gotToPage(page:number){
      this.page=page;
      this.findAllProducts();
    }
    gotToNextPage(){
      this.page++;
      this.findAllProducts();
    }
    
    gotToLastPage(){
       this.page = this.productResponse.totalPages as number - 1;
       this.findAllProducts();
    }
    
    
    get isLastPage(): boolean{
      return this.page == this.productResponse.totalPages as number - 1;
    }
    
}

