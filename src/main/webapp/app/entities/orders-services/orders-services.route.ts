import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrdersServices, OrdersServices } from 'app/shared/model/orders-services.model';
import { OrdersServicesService } from './orders-services.service';
import { OrdersServicesComponent } from './orders-services.component';
import { OrdersServicesDetailComponent } from './orders-services-detail.component';
import { OrdersServicesUpdateComponent } from './orders-services-update.component';

@Injectable({ providedIn: 'root' })
export class OrdersServicesResolve implements Resolve<IOrdersServices> {
  constructor(private service: OrdersServicesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrdersServices> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ordersServices: HttpResponse<OrdersServices>) => {
          if (ordersServices.body) {
            return of(ordersServices.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrdersServices());
  }
}

export const ordersServicesRoute: Routes = [
  {
    path: '',
    component: OrdersServicesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'btpprojectsApp.ordersServices.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrdersServicesDetailComponent,
    resolve: {
      ordersServices: OrdersServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.ordersServices.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrdersServicesUpdateComponent,
    resolve: {
      ordersServices: OrdersServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.ordersServices.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrdersServicesUpdateComponent,
    resolve: {
      ordersServices: OrdersServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.ordersServices.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
