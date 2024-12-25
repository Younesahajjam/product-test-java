import { enableProdMode, importProvidersFrom } from "@angular/core";

import { registerLocaleData } from "@angular/common";
import {
  HTTP_INTERCEPTORS,
  provideHttpClient,
  withInterceptors,
  withInterceptorsFromDi,
} from "@angular/common/http";
import localeFr from "@angular/common/locales/fr";
import { BrowserModule, bootstrapApplication } from "@angular/platform-browser";
import { provideAnimations } from "@angular/platform-browser/animations";
import { provideRouter, Routes } from "@angular/router";
import { APP_ROUTES } from "app/app.routes";
import { ConfirmationService, MessageService } from "primeng/api";
import { DialogService } from "primeng/dynamicdialog";
import { AppComponent } from "./app/app.component";
import { environment } from "./environments/environment";
import { LoginComponent } from "app/pages/login/login.component";
import { ProductListComponent } from "app/products/features/product-list/product-list.component";
import { RegisterComponent } from "app/pages/register/register.component";
import { ProductsClientsComponent } from "app/products/products-clients/products-clients/products-clients.component";
import { ProductsCardComponent } from "app/products/products-clients/products-card/products-card.component";
import { ClientsPanierComponent } from "app/products/products-clients/clients-panier/clients-panier.component";
import { ContactComponent } from "app/contact/contact/contact.component";
import { ProductAdminComponent } from "app/products/products-admin/product-admin/product-admin.component";
import {tokenInterceptor } from "app/services/interceptor/interceptors/token-interceptor.service";
import { authGuardGuard } from "app/services/guard/auth-guard.guard";

if (environment.production) {
  enableProdMode();
}

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'appComp', component: AppComponent },
  { path: 'products_clients', component: ProductsClientsComponent,  },
  { path: 'products-card', component: ProductsCardComponent  },
  { path: 'client-panier', component: ClientsPanierComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'products', component: ProductAdminComponent,   }, // Pour ajouter un produit 
  {
      path: "list",
      component: ProductListComponent,
    },
    //{ path: "**", redirectTo: "list" },
    { path: '**', redirectTo: '/login', pathMatch: 'full' } // redirection vers login par défaut

  //{ path: 'register', component: RegisterComponent },
 
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
   // importProvidersFrom(BrowserModule),
    // provideHttpClient( withInterceptorsFromDi(), ),
    provideHttpClient(withInterceptors([tokenInterceptor])),
    provideAnimations(),
    {
      provide: HTTP_INTERCEPTORS,
      useValue: tokenInterceptor,
      multi: true
  }
    //provideRouter(APP_ROUTES),
    //ConfirmationService,
    //MessageService,
    //DialogService,
  ],
}).catch((err) => console.log(err));

//registerLocaleData(localeFr, "fr-FR");
