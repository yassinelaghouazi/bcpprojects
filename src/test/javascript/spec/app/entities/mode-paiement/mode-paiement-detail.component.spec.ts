import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { ModePaiementDetailComponent } from 'app/entities/mode-paiement/mode-paiement-detail.component';
import { ModePaiement } from 'app/shared/model/mode-paiement.model';

describe('Component Tests', () => {
  describe('ModePaiement Management Detail Component', () => {
    let comp: ModePaiementDetailComponent;
    let fixture: ComponentFixture<ModePaiementDetailComponent>;
    const route = ({ data: of({ modePaiement: new ModePaiement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [ModePaiementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ModePaiementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModePaiementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load modePaiement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modePaiement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
