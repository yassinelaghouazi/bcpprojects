import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrdersProducts, OrdersProducts } from 'app/shared/model/orders-products.model';
import { OrdersProductsService } from './orders-products.service';
import { OrdersProductsComponent } from './orders-products.component';
import { OrdersProductsDetailComponent } from './orders-products-detail.component';
import { OrdersProductsUpdateComponent } from './orders-products-update.component';

@Injectable({ providedIn: 'root' })
export class OrdersProductsResolve implements Resolve<IOrdersProducts> {
  constructor(private service: OrdersProductsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrdersProducts> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ordersProducts: HttpResponse<OrdersProducts>) => {
          if (ordersProducts.body) {
            return of(ordersProducts.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrdersProducts());
  }
}

export const ordersProductsRoute: Routes = [
  {
    path: '',
    component: OrdersProductsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'btpprojectsApp.ordersProducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrdersProductsDetailComponent,
    resolve: {
      ordersProducts: OrdersProductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.ordersProducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrdersProductsUpdateComponent,
    resolve: {
      ordersProducts: OrdersProductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.ordersProducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrdersProductsUpdateComponent,
    resolve: {
      ordersProducts: OrdersProductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.ordersProducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
