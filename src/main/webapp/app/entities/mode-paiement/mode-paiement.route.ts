import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModePaiement, ModePaiement } from 'app/shared/model/mode-paiement.model';
import { ModePaiementService } from './mode-paiement.service';
import { ModePaiementComponent } from './mode-paiement.component';
import { ModePaiementDetailComponent } from './mode-paiement-detail.component';
import { ModePaiementUpdateComponent } from './mode-paiement-update.component';

@Injectable({ providedIn: 'root' })
export class ModePaiementResolve implements Resolve<IModePaiement> {
  constructor(private service: ModePaiementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModePaiement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modePaiement: HttpResponse<ModePaiement>) => {
          if (modePaiement.body) {
            return of(modePaiement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ModePaiement());
  }
}

export const modePaiementRoute: Routes = [
  {
    path: '',
    component: ModePaiementComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'btpprojectsApp.modePaiement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ModePaiementDetailComponent,
    resolve: {
      modePaiement: ModePaiementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.modePaiement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ModePaiementUpdateComponent,
    resolve: {
      modePaiement: ModePaiementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.modePaiement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ModePaiementUpdateComponent,
    resolve: {
      modePaiement: ModePaiementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.modePaiement.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
