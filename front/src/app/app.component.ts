import {
  Component,
  OnInit,
} from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { PanierServiceService } from "./services/panier/panier-service.service";
import { TokenService } from "./services/token/token.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule,RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent],
})
export class AppComponent implements OnInit {
  title = "ALTEN SHOP";

  // Variable pour stocker la quantité des produits dans le panier
  totalItems: number = 0;  

  constructor(private panierService: PanierServiceService
    ,private tokenService: TokenService
    ,private router: Router

  ) {}

  ngOnInit() {
    // S'abonner au total des articles
    this.panierService.totalItems$.subscribe((count) => {
      this.totalItems = count;  
    });
  }

  logout() {
    // Supprimer le token ou toute autre information d'authentification
    this.tokenService.removeToken();  

    // Rediriger l'utilisateur vers la page de connexion ou une autre page
    this.router.navigate(['/login']);  
  }
}
