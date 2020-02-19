import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMaitreOuvrage } from 'app/shared/model/maitre-ouvrage.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MaitreOuvrageService } from './maitre-ouvrage.service';
import { MaitreOuvrageDeleteDialogComponent } from './maitre-ouvrage-delete-dialog.component';

@Component({
  selector: 'jhi-maitre-ouvrage',
  templateUrl: './maitre-ouvrage.component.html'
})
export class MaitreOuvrageComponent implements OnInit, OnDestroy {
  maitreOuvrages?: IMaitreOuvrage[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected maitreOuvrageService: MaitreOuvrageService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.maitreOuvrageService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IMaitreOuvrage[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInMaitreOuvrages();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMaitreOuvrage): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMaitreOuvrages(): void {
    this.eventSubscriber = this.eventManager.subscribe('maitreOuvrageListModification', () => this.loadPage());
  }

  delete(maitreOuvrage: IMaitreOuvrage): void {
    const modalRef = this.modalService.open(MaitreOuvrageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.maitreOuvrage = maitreOuvrage;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IMaitreOuvrage[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/maitre-ouvrage'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.maitreOuvrages = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
