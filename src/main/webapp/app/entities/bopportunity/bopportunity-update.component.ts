import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBopportunity, Bopportunity } from 'app/shared/model/bopportunity.model';
import { BopportunityService } from './bopportunity.service';
import { ICaution } from 'app/shared/model/caution.model';
import { CautionService } from 'app/entities/caution/caution.service';
import { IProject } from 'app/shared/model/project.model';
import { ProjectService } from 'app/entities/project/project.service';

type SelectableEntity = ICaution | IProject;

@Component({
  selector: 'jhi-bopportunity-update',
  templateUrl: './bopportunity-update.component.html'
})
export class BopportunityUpdateComponent implements OnInit {
  isSaving = false;
  cautions: ICaution[] = [];
  projects: IProject[] = [];
  dateRemisePlisDp: any;

  editForm = this.fb.group({
    id: [],
    idBopportunity: [null, [Validators.required]],
    dateRemisePlis: [],
    montantCaution: [],
    objetAffaire: [],
    estimationBudget: [],
    commentaire: [],
    numeroAppelOffre: [null, [Validators.required]],
    numeroMarche: [],
    url: [],
    cautionId: [],
    projectId: []
  });

  constructor(
    protected bopportunityService: BopportunityService,
    protected cautionService: CautionService,
    protected projectService: ProjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bopportunity }) => {
      this.updateForm(bopportunity);

      this.cautionService.query().subscribe((res: HttpResponse<ICaution[]>) => (this.cautions = res.body || []));

      this.projectService.query().subscribe((res: HttpResponse<IProject[]>) => (this.projects = res.body || []));
    });
  }

  updateForm(bopportunity: IBopportunity): void {
    this.editForm.patchValue({
      id: bopportunity.id,
      idBopportunity: bopportunity.idBopportunity,
      dateRemisePlis: bopportunity.dateRemisePlis,
      montantCaution: bopportunity.montantCaution,
      objetAffaire: bopportunity.objetAffaire,
      estimationBudget: bopportunity.estimationBudget,
      commentaire: bopportunity.commentaire,
      numeroAppelOffre: bopportunity.numeroAppelOffre,
      numeroMarche: bopportunity.numeroMarche,
      url: bopportunity.url,
      cautionId: bopportunity.cautionId,
      projectId: bopportunity.projectId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bopportunity = this.createFromForm();
    if (bopportunity.id !== undefined) {
      this.subscribeToSaveResponse(this.bopportunityService.update(bopportunity));
    } else {
      this.subscribeToSaveResponse(this.bopportunityService.create(bopportunity));
    }
  }

  private createFromForm(): IBopportunity {
    return {
      ...new Bopportunity(),
      id: this.editForm.get(['id'])!.value,
      idBopportunity: this.editForm.get(['idBopportunity'])!.value,
      dateRemisePlis: this.editForm.get(['dateRemisePlis'])!.value,
      montantCaution: this.editForm.get(['montantCaution'])!.value,
      objetAffaire: this.editForm.get(['objetAffaire'])!.value,
      estimationBudget: this.editForm.get(['estimationBudget'])!.value,
      commentaire: this.editForm.get(['commentaire'])!.value,
      numeroAppelOffre: this.editForm.get(['numeroAppelOffre'])!.value,
      numeroMarche: this.editForm.get(['numeroMarche'])!.value,
      url: this.editForm.get(['url'])!.value,
      cautionId: this.editForm.get(['cautionId'])!.value,
      projectId: this.editForm.get(['projectId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBopportunity>>): void {
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
