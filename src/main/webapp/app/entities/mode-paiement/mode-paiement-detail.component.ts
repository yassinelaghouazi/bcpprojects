import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModePaiement } from 'app/shared/model/mode-paiement.model';

@Component({
  selector: 'jhi-mode-paiement-detail',
  templateUrl: './mode-paiement-detail.component.html'
})
export class ModePaiementDetailComponent implements OnInit {
  modePaiement: IModePaiement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modePaiement }) => (this.modePaiement = modePaiement));
  }

  previousState(): void {
    window.history.back();
  }
}
