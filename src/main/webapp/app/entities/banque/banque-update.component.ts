import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBanque, Banque } from 'app/shared/model/banque.model';
import { BanqueService } from './banque.service';
import { ICaution } from 'app/shared/model/caution.model';
import { CautionService } from 'app/entities/caution/caution.service';

@Component({
  selector: 'jhi-banque-update',
  templateUrl: './banque-update.component.html'
})
export class BanqueUpdateComponent implements OnInit {
  isSaving = false;
  cautions: ICaution[] = [];

  editForm = this.fb.group({
    id: [],
    idBanque: [null, [Validators.required]],
    banque: [null, [Validators.required]],
    contactEmail: [],
    contactTel: [],
    adresseAgence: [],
    cautionId: []
  });

  constructor(
    protected banqueService: BanqueService,
    protected cautionService: CautionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ banque }) => {
      this.updateForm(banque);

      this.cautionService.query().subscribe((res: HttpResponse<ICaution[]>) => (this.cautions = res.body || []));
    });
  }

  updateForm(banque: IBanque): void {
    this.editForm.patchValue({
      id: banque.id,
      idBanque: banque.idBanque,
      banque: banque.banque,
      contactEmail: banque.contactEmail,
      contactTel: banque.contactTel,
      adresseAgence: banque.adresseAgence,
      cautionId: banque.cautionId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const banque = this.createFromForm();
    if (banque.id !== undefined) {
      this.subscribeToSaveResponse(this.banqueService.update(banque));
    } else {
      this.subscribeToSaveResponse(this.banqueService.create(banque));
    }
  }

  private createFromForm(): IBanque {
    return {
      ...new Banque(),
      id: this.editForm.get(['id'])!.value,
      idBanque: this.editForm.get(['idBanque'])!.value,
      banque: this.editForm.get(['banque'])!.value,
      contactEmail: this.editForm.get(['contactEmail'])!.value,
      contactTel: this.editForm.get(['contactTel'])!.value,
      adresseAgence: this.editForm.get(['adresseAgence'])!.value,
      cautionId: this.editForm.get(['cautionId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBanque>>): void {
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

  trackById(index: number, item: ICaution): any {
    return item.id;
  }
}
