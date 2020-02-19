import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { ModePaiementUpdateComponent } from 'app/entities/mode-paiement/mode-paiement-update.component';
import { ModePaiementService } from 'app/entities/mode-paiement/mode-paiement.service';
import { ModePaiement } from 'app/shared/model/mode-paiement.model';

describe('Component Tests', () => {
  describe('ModePaiement Management Update Component', () => {
    let comp: ModePaiementUpdateComponent;
    let fixture: ComponentFixture<ModePaiementUpdateComponent>;
    let service: ModePaiementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [ModePaiementUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ModePaiementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModePaiementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModePaiementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModePaiement(123);
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
        const entity = new ModePaiement();
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
