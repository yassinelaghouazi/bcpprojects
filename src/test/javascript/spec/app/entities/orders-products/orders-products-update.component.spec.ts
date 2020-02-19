import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { OrdersProductsUpdateComponent } from 'app/entities/orders-products/orders-products-update.component';
import { OrdersProductsService } from 'app/entities/orders-products/orders-products.service';
import { OrdersProducts } from 'app/shared/model/orders-products.model';

describe('Component Tests', () => {
  describe('OrdersProducts Management Update Component', () => {
    let comp: OrdersProductsUpdateComponent;
    let fixture: ComponentFixture<OrdersProductsUpdateComponent>;
    let service: OrdersProductsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [OrdersProductsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrdersProductsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrdersProductsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrdersProductsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrdersProducts(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrdersProducts();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
