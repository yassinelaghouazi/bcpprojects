import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProject, Project } from 'app/shared/model/project.model';
import { ProjectService } from './project.service';
import { ProjectComponent } from './project.component';
import { ProjectDetailComponent } from './project-detail.component';
import { ProjectUpdateComponent } from './project-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectResolve implements Resolve<IProject> {
  constructor(private service: ProjectService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProject> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((project: HttpResponse<Project>) => {
          if (project.body) {
            return of(project.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Project());
  }
}

export const projectRoute: Routes = [
  {
    path: '',
    component: ProjectComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'btpprojectsApp.project.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProjectDetailComponent,
    resolve: {
      project: ProjectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.project.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProjectUpdateComponent,
    resolve: {
      project: ProjectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.project.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProjectUpdateComponent,
    resolve: {
      project: ProjectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'btpprojectsApp.project.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
