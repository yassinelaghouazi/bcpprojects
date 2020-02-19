import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBopportunity } from 'app/shared/model/bopportunity.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BopportunityService } from './bopportunity.service';
import { BopportunityDeleteDialogComponent } from './bopportunity-delete-dialog.component';

@Component({
  selector: 'jhi-bopportunity',
  templateUrl: './bopportunity.component.html'
})
export class BopportunityComponent implements OnInit, OnDestroy {
  bopportunities?: IBopportunity[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected bopportunityService: BopportunityService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.bopportunityService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IBopportunity[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInBopportunities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBopportunity): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBopportunities(): void {
    this.eventSubscriber = this.eventManager.subscribe('bopportunityListModification', () => this.loadPage());
  }

  delete(bopportunity: IBopportunity): void {
    const modalRef = this.modalService.open(BopportunityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bopportunity = bopportunity;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IBopportunity[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/bopportunity'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.bopportunities = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
