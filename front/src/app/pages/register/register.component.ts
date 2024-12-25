import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from 'app/services/models';
import { AuthService } from 'app/services/services';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerRequest: RegisterRequest={name: '', email: '', password: ''};
  errorMsg: Array<string> = [];
  constructor(private router: Router, private authService: AuthService){}

  register(){
   // this.errorMsg=[];
    this.authService.regester({
      body: this.registerRequest
    }).subscribe({
      next:()=> {
        this.router.navigate(['login']);
      },
     // error: (err)=> {
        //this.errorMsg=err.error.validationErrors;
    
    //  }
    })
  };

  login(){
    this.router.navigate(['login']);
  }

}
