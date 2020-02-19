import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBopportunity } from 'app/shared/model/bopportunity.model';
import { BopportunityService } from './bopportunity.service';

@Component({
  templateUrl: './bopportunity-delete-dialog.component.html'
})
export class BopportunityDeleteDialogComponent {
  bopportunity?: IBopportunity;

  constructor(
    protected bopportunityService: BopportunityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bopportunityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bopportunityListModification');
      this.activeModal.close();
    });
  }
}
