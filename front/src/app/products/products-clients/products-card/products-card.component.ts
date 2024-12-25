import { ItemDto } from 'app/services/models';
import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-products-card',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './products-card.component.html',
  styleUrl: './products-card.component.css'
})
export class ProductsCardComponent {

  

    private  _product: ItemDto = {};
    //private _productimgC:string | undefined;
    
  
  get product(): ItemDto{
    return this._product;
  
  }
  @Input()
  set product(value: ItemDto){
    this._product = value;
  }
  
   get productimageC(): string | undefined {
      if (this._product.image) {
        return 'data:image/jpg;base64,' + this._product.image;
      }
      return '';
    }
  @Output() private share: EventEmitter<ItemDto> = new EventEmitter<ItemDto>();
  @Output() private addToCart : EventEmitter<ItemDto> = new EventEmitter<ItemDto>();
  @Output() private borrow: EventEmitter<ItemDto> = new EventEmitter<ItemDto>();
  @Output() private edit:EventEmitter<ItemDto>=new EventEmitter<ItemDto>();
  @Output() private details:EventEmitter<ItemDto>= new EventEmitter<ItemDto>();
  
  
  onAddToPanier(){
    this.details.emit(this._product);
  }

    onShowDetails(){
      this.details.emit(this._product);
    }
  
    onBorrow(){
      this.borrow.emit(this._product);
    }
  
    onAddToCart(){
      this.addToCart.emit(this._product);
    }
  
    onEdit(){
      this.edit.emit(this._product);
    }
  
    onShare(){
      this.share.emit(this._product);
    }
  
   
  
  }

