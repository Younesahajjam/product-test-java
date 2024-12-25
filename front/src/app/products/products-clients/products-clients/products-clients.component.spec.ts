import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsClientsComponent } from './products-clients.component';

describe('ProductsClientsComponent', () => {
  let component: ProductsClientsComponent;
  let fixture: ComponentFixture<ProductsClientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductsClientsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductsClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
