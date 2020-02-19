import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IProvider, Provider } from 'app/shared/model/provider.model';
import { ProviderService } from './provider.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders/orders.service';
import { IProducts } from 'app/shared/model/products.model';
import { ProductsService } from 'app/entities/products/products.service';
import { IReglement } from 'app/shared/model/reglement.model';
import { ReglementService } from 'app/entities/reglement/reglement.service';

type SelectableEntity = IOrders | IProducts | IReglement;

@Component({
  selector: 'jhi-provider-update',
  templateUrl: './provider-update.component.html'
})
export class ProviderUpdateComponent implements OnInit {
  isSaving = false;
  orders: IOrders[] = [];
  products: IProducts[] = [];
  reglements: IReglement[] = [];

  editForm = this.fb.group({
    id: [],
    idProvider: [null, [Validators.required]],
    providerName: [],
    providerLogo: [],
    providerLogoContentType: [],
    ordersId: [],
    productsId: [],
    reglementId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected providerService: ProviderService,
    protected ordersService: OrdersService,
    protected productsService: ProductsService,
    protected reglementService: ReglementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ provider }) => {
      this.updateForm(provider);

      this.ordersService.query().subscribe((res: HttpResponse<IOrders[]>) => (this.orders = res.body || []));

      this.productsService.query().subscribe((res: HttpResponse<IProducts[]>) => (this.products = res.body || []));

      this.reglementService.query().subscribe((res: HttpResponse<IReglement[]>) => (this.reglements = res.body || []));
    });
  }

  updateForm(provider: IProvider): void {
    this.editForm.patchValue({
      id: provider.id,
      idProvider: provider.idProvider,
      providerName: provider.providerName,
      providerLogo: provider.providerLogo,
      providerLogoContentType: provider.providerLogoContentType,
      ordersId: provider.ordersId,
      productsId: provider.productsId,
      reglementId: provider.reglementId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('btpprojectsApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const provider = this.createFromForm();
    if (provider.id !== undefined) {
      this.subscribeToSaveResponse(this.providerService.update(provider));
    } else {
      this.subscribeToSaveResponse(this.providerService.create(provider));
    }
  }

  private createFromForm(): IProvider {
    return {
      ...new Provider(),
      id: this.editForm.get(['id'])!.value,
      idProvider: this.editForm.get(['idProvider'])!.value,
      providerName: this.editForm.get(['providerName'])!.value,
      providerLogoContentType: this.editForm.get(['providerLogoContentType'])!.value,
      providerLogo: this.editForm.get(['providerLogo'])!.value,
      ordersId: this.editForm.get(['ordersId'])!.value,
      productsId: this.editForm.get(['productsId'])!.value,
      reglementId: this.editForm.get(['reglementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProvider>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
