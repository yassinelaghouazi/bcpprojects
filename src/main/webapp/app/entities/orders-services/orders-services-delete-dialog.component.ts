import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrdersServices } from 'app/shared/model/orders-services.model';
import { OrdersServicesService } from './orders-services.service';

@Component({
  templateUrl: './orders-services-delete-dialog.component.html'
})
export class OrdersServicesDeleteDialogComponent {
  ordersServices?: IOrdersServices;

  constructor(
    protected ordersServicesService: OrdersServicesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ordersServicesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ordersServicesListModification');
      this.activeModal.close();
    });
  }
}
