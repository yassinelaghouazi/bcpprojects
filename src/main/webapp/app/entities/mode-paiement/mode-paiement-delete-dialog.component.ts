import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModePaiement } from 'app/shared/model/mode-paiement.model';
import { ModePaiementService } from './mode-paiement.service';

@Component({
  templateUrl: './mode-paiement-delete-dialog.component.html'
})
export class ModePaiementDeleteDialogComponent {
  modePaiement?: IModePaiement;

  constructor(
    protected modePaiementService: ModePaiementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.modePaiementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modePaiementListModification');
      this.activeModal.close();
    });
  }
}
