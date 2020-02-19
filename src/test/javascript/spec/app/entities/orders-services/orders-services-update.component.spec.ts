import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { OrdersServicesUpdateComponent } from 'app/entities/orders-services/orders-services-update.component';
import { OrdersServicesService } from 'app/entities/orders-services/orders-services.service';
import { OrdersServices } from 'app/shared/model/orders-services.model';

describe('Component Tests', () => {
  describe('OrdersServices Management Update Component', () => {
    let comp: OrdersServicesUpdateComponent;
    let fixture: ComponentFixture<OrdersServicesUpdateComponent>;
    let service: OrdersServicesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [OrdersServicesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrdersServicesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrdersServicesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrdersServicesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrdersServices(123);
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
        const entity = new OrdersServices();
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
