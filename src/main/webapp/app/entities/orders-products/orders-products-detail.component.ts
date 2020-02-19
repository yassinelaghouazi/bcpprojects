import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrdersProducts } from 'app/shared/model/orders-products.model';

@Component({
  selector: 'jhi-orders-products-detail',
  templateUrl: './orders-products-detail.component.html'
})
export class OrdersProductsDetailComponent implements OnInit {
  ordersProducts: IOrdersProducts | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ordersProducts }) => (this.ordersProducts = ordersProducts));
  }

  previousState(): void {
    window.history.back();
  }
}
