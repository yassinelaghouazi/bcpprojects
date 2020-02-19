import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { OrdersProductsDetailComponent } from 'app/entities/orders-products/orders-products-detail.component';
import { OrdersProducts } from 'app/shared/model/orders-products.model';

describe('Component Tests', () => {
  describe('OrdersProducts Management Detail Component', () => {
    let comp: OrdersProductsDetailComponent;
    let fixture: ComponentFixture<OrdersProductsDetailComponent>;
    const route = ({ data: of({ ordersProducts: new OrdersProducts(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [OrdersProductsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrdersProductsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrdersProductsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ordersProducts on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ordersProducts).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
