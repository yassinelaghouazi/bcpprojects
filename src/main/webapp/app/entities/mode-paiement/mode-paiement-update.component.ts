import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModePaiement, ModePaiement } from 'app/shared/model/mode-paiement.model';
import { ModePaiementService } from './mode-paiement.service';
import { IReglement } from 'app/shared/model/reglement.model';
import { ReglementService } from 'app/entities/reglement/reglement.service';

@Component({
  selector: 'jhi-mode-paiement-update',
  templateUrl: './mode-paiement-update.component.html'
})
export class ModePaiementUpdateComponent implements OnInit {
  isSaving = false;
  reglements: IReglement[] = [];

  editForm = this.fb.group({
    id: [],
    idModePaiement: [null, [Validators.required]],
    modePaiement: [],
    reglementId: []
  });

  constructor(
    protected modePaiementService: ModePaiementService,
    protected reglementService: ReglementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modePaiement }) => {
      this.updateForm(modePaiement);

      this.reglementService.query().subscribe((res: HttpResponse<IReglement[]>) => (this.reglements = res.body || []));
    });
  }

  updateForm(modePaiement: IModePaiement): void {
    this.editForm.patchValue({
      id: modePaiement.id,
      idModePaiement: modePaiement.idModePaiement,
      modePaiement: modePaiement.modePaiement,
      reglementId: modePaiement.reglementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modePaiement = this.createFromForm();
    if (modePaiement.id !== undefined) {
      this.subscribeToSaveResponse(this.modePaiementService.update(modePaiement));
    } else {
      this.subscribeToSaveResponse(this.modePaiementService.create(modePaiement));
    }
  }

  private createFromForm(): IModePaiement {
    return {
      ...new ModePaiement(),
      id: this.editForm.get(['id'])!.value,
      idModePaiement: this.editForm.get(['idModePaiement'])!.value,
      modePaiement: this.editForm.get(['modePaiement'])!.value,
      reglementId: this.editForm.get(['reglementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModePaiement>>): void {
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

  trackById(index: number, item: IReglement): any {
    return item.id;
  }
}
