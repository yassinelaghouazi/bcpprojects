import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BopportunityService } from 'app/entities/bopportunity/bopportunity.service';
import { IBopportunity, Bopportunity } from 'app/shared/model/bopportunity.model';

describe('Service Tests', () => {
  describe('Bopportunity Service', () => {
    let injector: TestBed;
    let service: BopportunityService;
    let httpMock: HttpTestingController;
    let elemDefault: IBopportunity;
    let expectedResult: IBopportunity | IBopportunity[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BopportunityService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bopportunity(0, 0, currentDate, 0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateRemisePlis: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bopportunity', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateRemisePlis: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateRemisePlis: currentDate
          },
          returnedFromService
        );

        service.create(new Bopportunity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bopportunity', () => {
        const returnedFromService = Object.assign(
          {
            idBopportunity: 1,
            dateRemisePlis: currentDate.format(DATE_FORMAT),
            montantCaution: 1,
            objetAffaire: 'BBBBBB',
            estimationBudget: 1,
            commentaire: 'BBBBBB',
            numeroAppelOffre: 'BBBBBB',
            numeroMarche: 'BBBBBB',
            url: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateRemisePlis: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bopportunity', () => {
        const returnedFromService = Object.assign(
          {
            idBopportunity: 1,
            dateRemisePlis: currentDate.format(DATE_FORMAT),
            montantCaution: 1,
            objetAffaire: 'BBBBBB',
            estimationBudget: 1,
            commentaire: 'BBBBBB',
            numeroAppelOffre: 'BBBBBB',
            numeroMarche: 'BBBBBB',
            url: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateRemisePlis: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Bopportunity', () => {
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
