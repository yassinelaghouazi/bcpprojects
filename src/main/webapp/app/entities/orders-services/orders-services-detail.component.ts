import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrdersServices } from 'app/shared/model/orders-services.model';

@Component({
  selector: 'jhi-orders-services-detail',
  templateUrl: './orders-services-detail.component.html'
})
export class OrdersServicesDetailComponent implements OnInit {
  ordersServices: IOrdersServices | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ordersServices }) => (this.ordersServices = ordersServices));
  }

  previousState(): void {
    window.history.back();
  }
}
