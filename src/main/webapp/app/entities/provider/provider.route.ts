import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProvider, Provider } from 'app/shared/model/provider.model';
import { ProviderService } from './provider.service';
import { ProviderComponent } from './provider.component';
import { ProviderDetailComponent } from './provider-detail.component';
import { ProviderUpdateComponent } from './provider-update.component';

@Injectable({ providedIn: 'root' })
export class ProviderResolve implements Resolve<IProvider> {
  constructor(private service: ProviderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProvider> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((provider: HttpResponse<Provider>) => {
          if (provider.body) {
            return of(provider.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Provider());
  }
}

export const providerRoute: Routes = [
  {
    path: '',
    component: ProviderComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'btpprojectsApp.provider.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProviderDetailComponent,
    resolve: {
      provider: ProviderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.provider.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProviderUpdateComponent,
    resolve: {
      provider: ProviderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.provider.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProviderUpdateComponent,
    resolve: {
      provider: ProviderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.provider.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
