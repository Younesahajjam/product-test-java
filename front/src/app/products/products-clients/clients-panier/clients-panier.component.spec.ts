import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientsPanierComponent } from './clients-panier.component';

describe('ClientsPanierComponent', () => {
  let component: ClientsPanierComponent;
  let fixture: ComponentFixture<ClientsPanierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientsPanierComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientsPanierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
