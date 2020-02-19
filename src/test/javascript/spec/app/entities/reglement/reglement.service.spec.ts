import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ReglementService } from 'app/entities/reglement/reglement.service';
import { IReglement, Reglement } from 'app/shared/model/reglement.model';

describe('Service Tests', () => {
  describe('Reglement Service', () => {
    let injector: TestBed;
    let service: ReglementService;
    let httpMock: HttpTestingController;
    let elemDefault: IReglement;
    let expectedResult: IReglement | IReglement[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ReglementService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Reglement(0, 0, currentDate, currentDate, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateEffetReglement: currentDate.format(DATE_FORMAT),
            dateReglement: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Reglement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateEffetReglement: currentDate.format(DATE_FORMAT),
            dateReglement: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEffetReglement: currentDate,
            dateReglement: currentDate
          },
          returnedFromService
        );

        service.create(new Reglement()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Reglement', () => {
        const returnedFromService = Object.assign(
          {
            idReglement: 1,
            dateEffetReglement: currentDate.format(DATE_FORMAT),
            dateReglement: currentDate.format(DATE_FORMAT),
            montantReglement: 1,
            commentaire: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEffetReglement: currentDate,
            dateReglement: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Reglement', () => {
        const returnedFromService = Object.assign(
          {
            idReglement: 1,
            dateEffetReglement: currentDate.format(DATE_FORMAT),
            dateReglement: currentDate.format(DATE_FORMAT),
            montantReglement: 1,
            commentaire: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEffetReglement: currentDate,
            dateReglement: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Reglement', () => {
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
