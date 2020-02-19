import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProject, Project } from 'app/shared/model/project.model';
import { ProjectService } from './project.service';
import { ICaution } from 'app/shared/model/caution.model';
import { CautionService } from 'app/entities/caution/caution.service';
import { IDelivery } from 'app/shared/model/delivery.model';
import { DeliveryService } from 'app/entities/delivery/delivery.service';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders/orders.service';
import { IReglement } from 'app/shared/model/reglement.model';
import { ReglementService } from 'app/entities/reglement/reglement.service';

type SelectableEntity = ICaution | IDelivery | IOrders | IReglement;

@Component({
  selector: 'jhi-project-update',
  templateUrl: './project-update.component.html'
})
export class ProjectUpdateComponent implements OnInit {
  isSaving = false;
  cautions: ICaution[] = [];
  deliveries: IDelivery[] = [];
  orders: IOrders[] = [];
  reglements: IReglement[] = [];

  editForm = this.fb.group({
    id: [],
    idProject: [null, [Validators.required]],
    projectName: [],
    projectDescription: [],
    budget: [],
    numeroMarche: [],
    cautionId: [],
    deliveryId: [],
    ordersId: [],
    reglementId: []
  });

  constructor(
    protected projectService: ProjectService,
    protected cautionService: CautionService,
    protected deliveryService: DeliveryService,
    protected ordersService: OrdersService,
    protected reglementService: ReglementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ project }) => {
      this.updateForm(project);

      this.cautionService.query().subscribe((res: HttpResponse<ICaution[]>) => (this.cautions = res.body || []));

      this.deliveryService.query().subscribe((res: HttpResponse<IDelivery[]>) => (this.deliveries = res.body || []));

      this.ordersService.query().subscribe((res: HttpResponse<IOrders[]>) => (this.orders = res.body || []));

      this.reglementService.query().subscribe((res: HttpResponse<IReglement[]>) => (this.reglements = res.body || []));
    });
  }

  updateForm(project: IProject): void {
    this.editForm.patchValue({
      id: project.id,
      idProject: project.idProject,
      projectName: project.projectName,
      projectDescription: project.projectDescription,
      budget: project.budget,
      numeroMarche: project.numeroMarche,
      cautionId: project.cautionId,
      deliveryId: project.deliveryId,
      ordersId: project.ordersId,
      reglementId: project.reglementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const project = this.createFromForm();
    if (project.id !== undefined) {
      this.subscribeToSaveResponse(this.projectService.update(project));
    } else {
      this.subscribeToSaveResponse(this.projectService.create(project));
    }
  }

  private createFromForm(): IProject {
    return {
      ...new Project(),
      id: this.editForm.get(['id'])!.value,
      idProject: this.editForm.get(['idProject'])!.value,
      projectName: this.editForm.get(['projectName'])!.value,
      projectDescription: this.editForm.get(['projectDescription'])!.value,
      budget: this.editForm.get(['budget'])!.value,
      numeroMarche: this.editForm.get(['numeroMarche'])!.value,
      cautionId: this.editForm.get(['cautionId'])!.value,
      deliveryId: this.editForm.get(['deliveryId'])!.value,
      ordersId: this.editForm.get(['ordersId'])!.value,
      reglementId: this.editForm.get(['reglementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProject>>): void {
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
