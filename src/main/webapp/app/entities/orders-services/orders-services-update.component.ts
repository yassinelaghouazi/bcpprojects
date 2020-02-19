import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrdersServices, OrdersServices } from 'app/shared/model/orders-services.model';
import { OrdersServicesService } from './orders-services.service';

@Component({
  selector: 'jhi-orders-services-update',
  templateUrl: './orders-services-update.component.html'
})
export class OrdersServicesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idOrdersServices: [null, [Validators.required]],
    tarifJournalier: [],
    joursHomme: []
  });

  constructor(protected ordersServicesService: OrdersServicesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ordersServices }) => {
      this.updateForm(ordersServices);
    });
  }

  updateForm(ordersServices: IOrdersServices): void {
    this.editForm.patchValue({
      id: ordersServices.id,
      idOrdersServices: ordersServices.idOrdersServices,
      tarifJournalier: ordersServices.tarifJournalier,
      joursHomme: ordersServices.joursHomme
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ordersServices = this.createFromForm();
    if (ordersServices.id !== undefined) {
      this.subscribeToSaveResponse(this.ordersServicesService.update(ordersServices));
    } else {
      this.subscribeToSaveResponse(this.ordersServicesService.create(ordersServices));
    }
  }

  private createFromForm(): IOrdersServices {
    return {
      ...new OrdersServices(),
      id: this.editForm.get(['id'])!.value,
      idOrdersServices: this.editForm.get(['idOrdersServices'])!.value,
      tarifJournalier: this.editForm.get(['tarifJournalier'])!.value,
      joursHomme: this.editForm.get(['joursHomme'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrdersServices>>): void {
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
}
