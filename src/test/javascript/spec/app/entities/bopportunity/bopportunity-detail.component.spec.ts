import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { BopportunityDetailComponent } from 'app/entities/bopportunity/bopportunity-detail.component';
import { Bopportunity } from 'app/shared/model/bopportunity.model';

describe('Component Tests', () => {
  describe('Bopportunity Management Detail Component', () => {
    let comp: BopportunityDetailComponent;
    let fixture: ComponentFixture<BopportunityDetailComponent>;
    const route = ({ data: of({ bopportunity: new Bopportunity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [BopportunityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BopportunityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BopportunityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bopportunity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bopportunity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
