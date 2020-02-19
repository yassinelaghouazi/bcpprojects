import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { BtpprojectsTestModule } from '../../../test.module';
import { ModePaiementComponent } from 'app/entities/mode-paiement/mode-paiement.component';
import { ModePaiementService } from 'app/entities/mode-paiement/mode-paiement.service';
import { ModePaiement } from 'app/shared/model/mode-paiement.model';

describe('Component Tests', () => {
  describe('ModePaiement Management Component', () => {
    let comp: ModePaiementComponent;
    let fixture: ComponentFixture<ModePaiementComponent>;
    let service: ModePaiementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [ModePaiementComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(ModePaiementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModePaiementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModePaiementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModePaiement(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modePaiements && comp.modePaiements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModePaiement(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modePaiements && comp.modePaiements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
