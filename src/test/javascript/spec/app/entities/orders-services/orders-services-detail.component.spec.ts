import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { OrdersServicesDetailComponent } from 'app/entities/orders-services/orders-services-detail.component';
import { OrdersServices } from 'app/shared/model/orders-services.model';

describe('Component Tests', () => {
  describe('OrdersServices Management Detail Component', () => {
    let comp: OrdersServicesDetailComponent;
    let fixture: ComponentFixture<OrdersServicesDetailComponent>;
    const route = ({ data: of({ ordersServices: new OrdersServices(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [OrdersServicesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrdersServicesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrdersServicesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ordersServices on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ordersServices).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
