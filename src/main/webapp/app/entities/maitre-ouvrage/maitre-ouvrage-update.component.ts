import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMaitreOuvrage, MaitreOuvrage } from 'app/shared/model/maitre-ouvrage.model';
import { MaitreOuvrageService } from './maitre-ouvrage.service';
import { IBopportunity } from 'app/shared/model/bopportunity.model';
import { BopportunityService } from 'app/entities/bopportunity/bopportunity.service';
import { ICaution } from 'app/shared/model/caution.model';
import { CautionService } from 'app/entities/caution/caution.service';

type SelectableEntity = IBopportunity | ICaution;

@Component({
  selector: 'jhi-maitre-ouvrage-update',
  templateUrl: './maitre-ouvrage-update.component.html'
})
export class MaitreOuvrageUpdateComponent implements OnInit {
  isSaving = false;
  bopportunities: IBopportunity[] = [];
  cautions: ICaution[] = [];

  editForm = this.fb.group({
    id: [],
    idMaitreOuvrage: [],
    nom: [],
    email: [],
    tel: [],
    contactPersonne: [],
    bopportunityId: [],
    cautionId: []
  });

  constructor(
    protected maitreOuvrageService: MaitreOuvrageService,
    protected bopportunityService: BopportunityService,
    protected cautionService: CautionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ maitreOuvrage }) => {
      this.updateForm(maitreOuvrage);

      this.bopportunityService.query().subscribe((res: HttpResponse<IBopportunity[]>) => (this.bopportunities = res.body || []));

      this.cautionService.query().subscribe((res: HttpResponse<ICaution[]>) => (this.cautions = res.body || []));
    });
  }

  updateForm(maitreOuvrage: IMaitreOuvrage): void {
    this.editForm.patchValue({
      id: maitreOuvrage.id,
      idMaitreOuvrage: maitreOuvrage.idMaitreOuvrage,
      nom: maitreOuvrage.nom,
      email: maitreOuvrage.email,
      tel: maitreOuvrage.tel,
      contactPersonne: maitreOuvrage.contactPersonne,
      bopportunityId: maitreOuvrage.bopportunityId,
      cautionId: maitreOuvrage.cautionId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const maitreOuvrage = this.createFromForm();
    if (maitreOuvrage.id !== undefined) {
      this.subscribeToSaveResponse(this.maitreOuvrageService.update(maitreOuvrage));
    } else {
      this.subscribeToSaveResponse(this.maitreOuvrageService.create(maitreOuvrage));
    }
  }

  private createFromForm(): IMaitreOuvrage {
    return {
      ...new MaitreOuvrage(),
      id: this.editForm.get(['id'])!.value,
      idMaitreOuvrage: this.editForm.get(['idMaitreOuvrage'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      email: this.editForm.get(['email'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      contactPersonne: this.editForm.get(['contactPersonne'])!.value,
      bopportunityId: this.editForm.get(['bopportunityId'])!.value,
      cautionId: this.editForm.get(['cautionId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaitreOuvrage>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
