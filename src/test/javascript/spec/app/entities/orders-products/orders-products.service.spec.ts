import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OrdersProductsService } from 'app/entities/orders-products/orders-products.service';
import { IOrdersProducts, OrdersProducts } from 'app/shared/model/orders-products.model';

describe('Service Tests', () => {
  describe('OrdersProducts Service', () => {
    let injector: TestBed;
    let service: OrdersProductsService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrdersProducts;
    let expectedResult: IOrdersProducts | IOrdersProducts[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrdersProductsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new OrdersProducts(0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OrdersProducts', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new OrdersProducts()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OrdersProducts', () => {
        const returnedFromService = Object.assign(
          {
            idOrdersProducts: 1,
            unitPrice: 1,
            quantite: 1,
            tva: 1,
            totalHT: 1,
            montantTVA: 1,
            totalTTC: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of OrdersProducts', () => {
        const returnedFromService = Object.assign(
          {
            idOrdersProducts: 1,
            unitPrice: 1,
            quantite: 1,
            tva: 1,
            totalHT: 1,
            montantTVA: 1,
            totalTTC: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a OrdersProducts', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
