import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrdersProducts, OrdersProducts } from 'app/shared/model/orders-products.model';
import { OrdersProductsService } from './orders-products.service';

@Component({
  selector: 'jhi-orders-products-update',
  templateUrl: './orders-products-update.component.html'
})
export class OrdersProductsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idOrdersProducts: [null, [Validators.required]],
    unitPrice: [],
    quantite: [],
    tva: [],
    totalHT: [],
    montantTVA: [],
    totalTTC: []
  });

  constructor(protected ordersProductsService: OrdersProductsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ordersProducts }) => {
      this.updateForm(ordersProducts);
    });
  }

  updateForm(ordersProducts: IOrdersProducts): void {
    this.editForm.patchValue({
      id: ordersProducts.id,
      idOrdersProducts: ordersProducts.idOrdersProducts,
      unitPrice: ordersProducts.unitPrice,
      quantite: ordersProducts.quantite,
      tva: ordersProducts.tva,
      totalHT: ordersProducts.totalHT,
      montantTVA: ordersProducts.montantTVA,
      totalTTC: ordersProducts.totalTTC
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ordersProducts = this.createFromForm();
    if (ordersProducts.id !== undefined) {
      this.subscribeToSaveResponse(this.ordersProductsService.update(ordersProducts));
    } else {
      this.subscribeToSaveResponse(this.ordersProductsService.create(ordersProducts));
    }
  }

  private createFromForm(): IOrdersProducts {
    return {
      ...new OrdersProducts(),
      id: this.editForm.get(['id'])!.value,
      idOrdersProducts: this.editForm.get(['idOrdersProducts'])!.value,
      unitPrice: this.editForm.get(['unitPrice'])!.value,
      quantite: this.editForm.get(['quantite'])!.value,
      tva: this.editForm.get(['tva'])!.value,
      totalHT: this.editForm.get(['totalHT'])!.value,
      montantTVA: this.editForm.get(['montantTVA'])!.value,
      totalTTC: this.editForm.get(['totalTTC'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrdersProducts>>): void {
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
