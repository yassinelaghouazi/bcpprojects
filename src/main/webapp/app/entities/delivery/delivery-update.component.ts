import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDelivery, Delivery } from 'app/shared/model/delivery.model';
import { DeliveryService } from './delivery.service';
import { IReglement } from 'app/shared/model/reglement.model';
import { ReglementService } from 'app/entities/reglement/reglement.service';

@Component({
  selector: 'jhi-delivery-update',
  templateUrl: './delivery-update.component.html'
})
export class DeliveryUpdateComponent implements OnInit {
  isSaving = false;
  reglements: IReglement[] = [];
  deliveryDateDp: any;

  editForm = this.fb.group({
    id: [],
    idDelivery: [null, [Validators.required]],
    deliveryDescription: [],
    deliveryDate: [],
    subTotal: [],
    vatamount: [],
    total: [],
    reglementId: []
  });

  constructor(
    protected deliveryService: DeliveryService,
    protected reglementService: ReglementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ delivery }) => {
      this.updateForm(delivery);

      this.reglementService.query().subscribe((res: HttpResponse<IReglement[]>) => (this.reglements = res.body || []));
    });
  }

  updateForm(delivery: IDelivery): void {
    this.editForm.patchValue({
      id: delivery.id,
      idDelivery: delivery.idDelivery,
      deliveryDescription: delivery.deliveryDescription,
      deliveryDate: delivery.deliveryDate,
      subTotal: delivery.subTotal,
      vatamount: delivery.vatamount,
      total: delivery.total,
      reglementId: delivery.reglementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const delivery = this.createFromForm();
    if (delivery.id !== undefined) {
      this.subscribeToSaveResponse(this.deliveryService.update(delivery));
    } else {
      this.subscribeToSaveResponse(this.deliveryService.create(delivery));
    }
  }

  private createFromForm(): IDelivery {
    return {
      ...new Delivery(),
      id: this.editForm.get(['id'])!.value,
      idDelivery: this.editForm.get(['idDelivery'])!.value,
      deliveryDescription: this.editForm.get(['deliveryDescription'])!.value,
      deliveryDate: this.editForm.get(['deliveryDate'])!.value,
      subTotal: this.editForm.get(['subTotal'])!.value,
      vatamount: this.editForm.get(['vatamount'])!.value,
      total: this.editForm.get(['total'])!.value,
      reglementId: this.editForm.get(['reglementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDelivery>>): void {
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

  trackById(index: number, item: IReglement): any {
    return item.id;
  }
}
