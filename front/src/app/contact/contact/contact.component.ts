import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [ FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent implements OnInit {
  contactForm!: FormGroup;
  successMessage: string | null = null;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    // Initialisation du formulaire avec validation
    this.contactForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],  // Validation email
      message: ['', [Validators.required, Validators.maxLength(300)]]  // Validation du message
    });
  }

  // Méthode pour envoyer le formulaire
  onSubmit(): void {
    if (this.contactForm.valid) {
      // Si le formulaire est valide, afficher un message de succès
      this.successMessage = 'Demande de contact envoyée avec succès';
      // Réinitialiser le formulaire après envoi
      this.contactForm.reset();
    }
  }

  // Accesseurs pour faciliter l'accès aux contrôles du formulaire
  get email() {
    return this.contactForm.get('email');
  }

  get message() {
    return this.contactForm.get('message');
  }

}
