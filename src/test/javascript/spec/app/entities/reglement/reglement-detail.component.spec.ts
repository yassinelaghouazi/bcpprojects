import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { ReglementDetailComponent } from 'app/entities/reglement/reglement-detail.component';
import { Reglement } from 'app/shared/model/reglement.model';

describe('Component Tests', () => {
  describe('Reglement Management Detail Component', () => {
    let comp: ReglementDetailComponent;
    let fixture: ComponentFixture<ReglementDetailComponent>;
    const route = ({ data: of({ reglement: new Reglement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [ReglementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ReglementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReglementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load reglement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reglement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
