import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProducts, Products } from 'app/shared/model/products.model';
import { ProductsService } from './products.service';
import { IOrdersProducts } from 'app/shared/model/orders-products.model';
import { OrdersProductsService } from 'app/entities/orders-products/orders-products.service';

@Component({
  selector: 'jhi-products-update',
  templateUrl: './products-update.component.html'
})
export class ProductsUpdateComponent implements OnInit {
  isSaving = false;
  ordersproducts: IOrdersProducts[] = [];

  editForm = this.fb.group({
    id: [],
    idProducts: [null, [Validators.required]],
    productsDesc: [],
    recommendedPrice: [],
    ordersProductsId: []
  });

  constructor(
    protected productsService: ProductsService,
    protected ordersProductsService: OrdersProductsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ products }) => {
      this.updateForm(products);

      this.ordersProductsService.query().subscribe((res: HttpResponse<IOrdersProducts[]>) => (this.ordersproducts = res.body || []));
    });
  }

  updateForm(products: IProducts): void {
    this.editForm.patchValue({
      id: products.id,
      idProducts: products.idProducts,
      productsDesc: products.productsDesc,
      recommendedPrice: products.recommendedPrice,
      ordersProductsId: products.ordersProductsId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const products = this.createFromForm();
    if (products.id !== undefined) {
      this.subscribeToSaveResponse(this.productsService.update(products));
    } else {
      this.subscribeToSaveResponse(this.productsService.create(products));
    }
  }

  private createFromForm(): IProducts {
    return {
      ...new Products(),
      id: this.editForm.get(['id'])!.value,
      idProducts: this.editForm.get(['idProducts'])!.value,
      productsDesc: this.editForm.get(['productsDesc'])!.value,
      recommendedPrice: this.editForm.get(['recommendedPrice'])!.value,
      ordersProductsId: this.editForm.get(['ordersProductsId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProducts>>): void {
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

  trackById(index: number, item: IOrdersProducts): any {
    return item.id;
  }
}
