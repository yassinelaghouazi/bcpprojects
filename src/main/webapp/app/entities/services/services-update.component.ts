import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IServices, Services } from 'app/shared/model/services.model';
import { ServicesService } from './services.service';
import { IOrdersServices } from 'app/shared/model/orders-services.model';
import { OrdersServicesService } from 'app/entities/orders-services/orders-services.service';

@Component({
  selector: 'jhi-services-update',
  templateUrl: './services-update.component.html'
})
export class ServicesUpdateComponent implements OnInit {
  isSaving = false;
  ordersservices: IOrdersServices[] = [];

  editForm = this.fb.group({
    id: [],
    idServices: [null, [Validators.required]],
    servicesDesc: [],
    ordersServicesId: []
  });

  constructor(
    protected servicesService: ServicesService,
    protected ordersServicesService: OrdersServicesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ services }) => {
      this.updateForm(services);

      this.ordersServicesService.query().subscribe((res: HttpResponse<IOrdersServices[]>) => (this.ordersservices = res.body || []));
    });
  }

  updateForm(services: IServices): void {
    this.editForm.patchValue({
      id: services.id,
      idServices: services.idServices,
      servicesDesc: services.servicesDesc,
      ordersServicesId: services.ordersServicesId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const services = this.createFromForm();
    if (services.id !== undefined) {
      this.subscribeToSaveResponse(this.servicesService.update(services));
    } else {
      this.subscribeToSaveResponse(this.servicesService.create(services));
    }
  }

  private createFromForm(): IServices {
    return {
      ...new Services(),
      id: this.editForm.get(['id'])!.value,
      idServices: this.editForm.get(['idServices'])!.value,
      servicesDesc: this.editForm.get(['servicesDesc'])!.value,
      ordersServicesId: this.editForm.get(['ordersServicesId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServices>>): void {
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

  trackById(index: number, item: IOrdersServices): any {
    return item.id;
  }
}
