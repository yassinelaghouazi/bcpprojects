import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICaution, Caution } from 'app/shared/model/caution.model';
import { CautionService } from './caution.service';

@Component({
  selector: 'jhi-caution-update',
  templateUrl: './caution-update.component.html'
})
export class CautionUpdateComponent implements OnInit {
  isSaving = false;
  dateDemandeDp: any;
  dateRetraitDp: any;
  dateDepotDp: any;

  editForm = this.fb.group({
    id: [],
    idcaution: [null, [Validators.required]],
    montantCaution: [],
    objet: [],
    numeroCaution: [],
    dateDemande: [],
    dateRetrait: [],
    dateDepot: [],
    numeroMarche: [],
    statusCaution: [],
    typeCaution: []
  });

  constructor(protected cautionService: CautionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caution }) => {
      this.updateForm(caution);
    });
  }

  updateForm(caution: ICaution): void {
    this.editForm.patchValue({
      id: caution.id,
      idcaution: caution.idcaution,
      montantCaution: caution.montantCaution,
      objet: caution.objet,
      numeroCaution: caution.numeroCaution,
      dateDemande: caution.dateDemande,
      dateRetrait: caution.dateRetrait,
      dateDepot: caution.dateDepot,
      numeroMarche: caution.numeroMarche,
      statusCaution: caution.statusCaution,
      typeCaution: caution.typeCaution
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const caution = this.createFromForm();
    if (caution.id !== undefined) {
      this.subscribeToSaveResponse(this.cautionService.update(caution));
    } else {
      this.subscribeToSaveResponse(this.cautionService.create(caution));
    }
  }

  private createFromForm(): ICaution {
    return {
      ...new Caution(),
      id: this.editForm.get(['id'])!.value,
      idcaution: this.editForm.get(['idcaution'])!.value,
      montantCaution: this.editForm.get(['montantCaution'])!.value,
      objet: this.editForm.get(['objet'])!.value,
      numeroCaution: this.editForm.get(['numeroCaution'])!.value,
      dateDemande: this.editForm.get(['dateDemande'])!.value,
      dateRetrait: this.editForm.get(['dateRetrait'])!.value,
      dateDepot: this.editForm.get(['dateDepot'])!.value,
      numeroMarche: this.editForm.get(['numeroMarche'])!.value,
      statusCaution: this.editForm.get(['statusCaution'])!.value,
      typeCaution: this.editForm.get(['typeCaution'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaution>>): void {
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
