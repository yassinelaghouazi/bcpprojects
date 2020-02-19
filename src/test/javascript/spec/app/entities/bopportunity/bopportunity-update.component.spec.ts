import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BtpprojectsTestModule } from '../../../test.module';
import { BopportunityUpdateComponent } from 'app/entities/bopportunity/bopportunity-update.component';
import { BopportunityService } from 'app/entities/bopportunity/bopportunity.service';
import { Bopportunity } from 'app/shared/model/bopportunity.model';

describe('Component Tests', () => {
  describe('Bopportunity Management Update Component', () => {
    let comp: BopportunityUpdateComponent;
    let fixture: ComponentFixture<BopportunityUpdateComponent>;
    let service: BopportunityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BtpprojectsTestModule],
        declarations: [BopportunityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BopportunityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BopportunityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BopportunityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bopportunity(123);
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
        const entity = new Bopportunity();
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
