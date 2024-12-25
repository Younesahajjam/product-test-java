import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, Routes } from "@angular/router";
import { ProductListComponent } from "./features/product-list/product-list.component";
import { LoginComponent } from "app/pages/login/login.component";

export const PRODUCTS_ROUTES: Routes = [
	{path: "login",component: LoginComponent,},
	{ path: "**", redirectTo: "login" },
];
