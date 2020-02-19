import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrders, Orders } from 'app/shared/model/orders.model';
import { OrdersService } from './orders.service';
import { IDelivery } from 'app/shared/model/delivery.model';
import { DeliveryService } from 'app/entities/delivery/delivery.service';
import { IOrdersProducts } from 'app/shared/model/orders-products.model';
import { OrdersProductsService } from 'app/entities/orders-products/orders-products.service';
import { IOrdersServices } from 'app/shared/model/orders-services.model';
import { OrdersServicesService } from 'app/entities/orders-services/orders-services.service';
import { IReglement } from 'app/shared/model/reglement.model';
import { ReglementService } from 'app/entities/reglement/reglement.service';

type SelectableEntity = IDelivery | IOrdersProducts | IOrdersServices | IReglement;

@Component({
  selector: 'jhi-orders-update',
  templateUrl: './orders-update.component.html'
})
export class OrdersUpdateComponent implements OnInit {
  isSaving = false;
  deliveries: IDelivery[] = [];
  ordersproducts: IOrdersProducts[] = [];
  ordersservices: IOrdersServices[] = [];
  reglements: IReglement[] = [];
  ordersDateDp: any;
  expectedDeliveryDp: any;

  editForm = this.fb.group({
    id: [],
    idOrders: [null, [Validators.required]],
    ordersDesc: [],
    totalht: [],
    totaltva: [],
    totalttc: [],
    ordersDate: [],
    expectedDelivery: [],
    tvamoyenne: [],
    deliveryId: [],
    ordersProductsId: [],
    ordersServicesId: [],
    reglementId: []
  });

  constructor(
    protected ordersService: OrdersService,
    protected deliveryService: DeliveryService,
    protected ordersProductsService: OrdersProductsService,
    protected ordersServicesService: OrdersServicesService,
    protected reglementService: ReglementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orders }) => {
      this.updateForm(orders);

      this.deliveryService.query().subscribe((res: HttpResponse<IDelivery[]>) => (this.deliveries = res.body || []));

      this.ordersProductsService.query().subscribe((res: HttpResponse<IOrdersProducts[]>) => (this.ordersproducts = res.body || []));

      this.ordersServicesService.query().subscribe((res: HttpResponse<IOrdersServices[]>) => (this.ordersservices = res.body || []));

      this.reglementService.query().subscribe((res: HttpResponse<IReglement[]>) => (this.reglements = res.body || []));
    });
  }

  updateForm(orders: IOrders): void {
    this.editForm.patchValue({
      id: orders.id,
      idOrders: orders.idOrders,
      ordersDesc: orders.ordersDesc,
      totalht: orders.totalht,
      totaltva: orders.totaltva,
      totalttc: orders.totalttc,
      ordersDate: orders.ordersDate,
      expectedDelivery: orders.expectedDelivery,
      tvamoyenne: orders.tvamoyenne,
      deliveryId: orders.deliveryId,
      ordersProductsId: orders.ordersProductsId,
      ordersServicesId: orders.ordersServicesId,
      reglementId: orders.reglementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orders = this.createFromForm();
    if (orders.id !== undefined) {
      this.subscribeToSaveResponse(this.ordersService.update(orders));
    } else {
      this.subscribeToSaveResponse(this.ordersService.create(orders));
    }
  }

  private createFromForm(): IOrders {
    return {
      ...new Orders(),
      id: this.editForm.get(['id'])!.value,
      idOrders: this.editForm.get(['idOrders'])!.value,
      ordersDesc: this.editForm.get(['ordersDesc'])!.value,
      totalht: this.editForm.get(['totalht'])!.value,
      totaltva: this.editForm.get(['totaltva'])!.value,
      totalttc: this.editForm.get(['totalttc'])!.value,
      ordersDate: this.editForm.get(['ordersDate'])!.value,
      expectedDelivery: this.editForm.get(['expectedDelivery'])!.value,
      tvamoyenne: this.editForm.get(['tvamoyenne'])!.value,
      deliveryId: this.editForm.get(['deliveryId'])!.value,
      ordersProductsId: this.editForm.get(['ordersProductsId'])!.value,
      ordersServicesId: this.editForm.get(['ordersServicesId'])!.value,
      reglementId: this.editForm.get(['reglementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrders>>): void {
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
