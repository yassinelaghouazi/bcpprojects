import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrdersProducts } from 'app/shared/model/orders-products.model';
import { OrdersProductsService } from './orders-products.service';

@Component({
  templateUrl: './orders-products-delete-dialog.component.html'
})
export class OrdersProductsDeleteDialogComponent {
  ordersProducts?: IOrdersProducts;

  constructor(
    protected ordersProductsService: OrdersProductsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ordersProductsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ordersProductsListModification');
      this.activeModal.close();
    });
  }
}
