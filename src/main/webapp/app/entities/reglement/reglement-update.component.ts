import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IReglement, Reglement } from 'app/shared/model/reglement.model';
import { ReglementService } from './reglement.service';

@Component({
  selector: 'jhi-reglement-update',
  templateUrl: './reglement-update.component.html'
})
export class ReglementUpdateComponent implements OnInit {
  isSaving = false;
  dateEffetReglementDp: any;
  dateReglementDp: any;

  editForm = this.fb.group({
    id: [],
    idReglement: [null, [Validators.required]],
    dateEffetReglement: [],
    dateReglement: [],
    montantReglement: [],
    commentaire: []
  });

  constructor(protected reglementService: ReglementService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reglement }) => {
      this.updateForm(reglement);
    });
  }

  updateForm(reglement: IReglement): void {
    this.editForm.patchValue({
      id: reglement.id,
      idReglement: reglement.idReglement,
      dateEffetReglement: reglement.dateEffetReglement,
      dateReglement: reglement.dateReglement,
      montantReglement: reglement.montantReglement,
      commentaire: reglement.commentaire
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reglement = this.createFromForm();
    if (reglement.id !== undefined) {
      this.subscribeToSaveResponse(this.reglementService.update(reglement));
    } else {
      this.subscribeToSaveResponse(this.reglementService.create(reglement));
    }
  }

  private createFromForm(): IReglement {
    return {
      ...new Reglement(),
      id: this.editForm.get(['id'])!.value,
      idReglement: this.editForm.get(['idReglement'])!.value,
      dateEffetReglement: this.editForm.get(['dateEffetReglement'])!.value,
      dateReglement: this.editForm.get(['dateReglement'])!.value,
      montantReglement: this.editForm.get(['montantReglement'])!.value,
      commentaire: this.editForm.get(['commentaire'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReglement>>): void {
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
}
