import { PanierServiceService } from './../../../services/panier/panier-service.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { ItemDto, PageItemDto } from 'app/services/models';
import { ControllerCartService, ItemControllerService } from 'app/services/services';
import { TokenService } from 'app/services/token/token.service';
import { ProductsCardComponent } from '../products-card/products-card.component';

@Component({
  selector: 'app-products-clients',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule,ProductsCardComponent,RouterOutlet],
  templateUrl: './products-clients.component.html',
  styleUrl: './products-clients.component.css'
})
export class ProductsClientsComponent implements OnInit {

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
 

  constructor(
  private router: Router,
  private itemServices: ItemControllerService,
  private tokenService: TokenService,
  private carteService: ControllerCartService,
  private activateRoute: ActivatedRoute,
  private panierService: PanierServiceService
  ){}
  ngOnInit(): void {
    this.findAllProducts();
    const clientId= this.activateRoute.snapshot.params['clientId'];
}

private findAllProducts():void{
  this.itemServices.searchRejetMessages({
    page: this.page,
    size: this.size
  }).subscribe({
    next:(products)=>{
      this.productResponse=products;

    }
  });
}


public onAddToPanier(product: ItemDto) {
  const clientId = this.activateRoute.snapshot.params['clientId'];  // Récupérez `clientId` depuis un service ou autre source

  this.carteService.addProductsToCart({
    body: [this.productRequest],  
    clientId: clientId           
  }).subscribe({
    next: () => { 
      this.updateCartCount(clientId);  
      this.router.navigate(['producte-client']);
    },
    error: (err) => {
      console.error("Erreur lors de l'ajout au panier", err);
    }
  });
}



private updateCartCount(clientId: number) {
  // Récupérer le panier du client
  this.carteService.getCart({ cartId: clientId }).subscribe((cartDto) => {
    const totalItems = cartDto.items?.reduce((sum, item) => sum + (item.quantity || 0), 0) ?? 0;
    this.panierService.updateTotalItems(totalItems) ;  
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
