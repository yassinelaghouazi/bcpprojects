import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReglement, Reglement } from 'app/shared/model/reglement.model';
import { ReglementService } from './reglement.service';
import { ReglementComponent } from './reglement.component';
import { ReglementDetailComponent } from './reglement-detail.component';
import { ReglementUpdateComponent } from './reglement-update.component';

@Injectable({ providedIn: 'root' })
export class ReglementResolve implements Resolve<IReglement> {
  constructor(private service: ReglementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReglement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((reglement: HttpResponse<Reglement>) => {
          if (reglement.body) {
            return of(reglement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Reglement());
  }
}

export const reglementRoute: Routes = [
  {
    path: '',
    component: ReglementComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'btpprojectsApp.reglement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ReglementDetailComponent,
    resolve: {
      reglement: ReglementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.reglement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ReglementUpdateComponent,
    resolve: {
      reglement: ReglementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.reglement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ReglementUpdateComponent,
    resolve: {
      reglement: ReglementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.reglement.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
