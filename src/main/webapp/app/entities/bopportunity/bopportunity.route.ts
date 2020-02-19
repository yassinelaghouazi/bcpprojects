import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBopportunity, Bopportunity } from 'app/shared/model/bopportunity.model';
import { BopportunityService } from './bopportunity.service';
import { BopportunityComponent } from './bopportunity.component';
import { BopportunityDetailComponent } from './bopportunity-detail.component';
import { BopportunityUpdateComponent } from './bopportunity-update.component';

@Injectable({ providedIn: 'root' })
export class BopportunityResolve implements Resolve<IBopportunity> {
  constructor(private service: BopportunityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBopportunity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bopportunity: HttpResponse<Bopportunity>) => {
          if (bopportunity.body) {
            return of(bopportunity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bopportunity());
  }
}

export const bopportunityRoute: Routes = [
  {
    path: '',
    component: BopportunityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'btpprojectsApp.bopportunity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BopportunityDetailComponent,
    resolve: {
      bopportunity: BopportunityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.bopportunity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BopportunityUpdateComponent,
    resolve: {
      bopportunity: BopportunityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.bopportunity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BopportunityUpdateComponent,
    resolve: {
      bopportunity: BopportunityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.bopportunity.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
