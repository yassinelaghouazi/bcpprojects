import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ICompany, Company } from 'app/shared/model/company.model';
import { CompanyService } from './company.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IBopportunity } from 'app/shared/model/bopportunity.model';
import { BopportunityService } from 'app/entities/bopportunity/bopportunity.service';
import { IProject } from 'app/shared/model/project.model';
import { ProjectService } from 'app/entities/project/project.service';

type SelectableEntity = IBopportunity | IProject;

@Component({
  selector: 'jhi-company-update',
  templateUrl: './company-update.component.html'
})
export class CompanyUpdateComponent implements OnInit {
  isSaving = false;
  bopportunities: IBopportunity[] = [];
  projects: IProject[] = [];

  editForm = this.fb.group({
    id: [],
    idcompany: [null, [Validators.required]],
    companyName: [],
    companyLogo: [],
    companyLogoContentType: [],
    bopportunityId: [],
    projectId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected companyService: CompanyService,
    protected bopportunityService: BopportunityService,
    protected projectService: ProjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ company }) => {
      this.updateForm(company);

      this.bopportunityService.query().subscribe((res: HttpResponse<IBopportunity[]>) => (this.bopportunities = res.body || []));

      this.projectService.query().subscribe((res: HttpResponse<IProject[]>) => (this.projects = res.body || []));
    });
  }

  updateForm(company: ICompany): void {
    this.editForm.patchValue({
      id: company.id,
      idcompany: company.idcompany,
      companyName: company.companyName,
      companyLogo: company.companyLogo,
      companyLogoContentType: company.companyLogoContentType,
      bopportunityId: company.bopportunityId,
      projectId: company.projectId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('btpprojectsApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const company = this.createFromForm();
    if (company.id !== undefined) {
      this.subscribeToSaveResponse(this.companyService.update(company));
    } else {
      this.subscribeToSaveResponse(this.companyService.create(company));
    }
  }

  private createFromForm(): ICompany {
    return {
      ...new Company(),
      id: this.editForm.get(['id'])!.value,
      idcompany: this.editForm.get(['idcompany'])!.value,
      companyName: this.editForm.get(['companyName'])!.value,
      companyLogoContentType: this.editForm.get(['companyLogoContentType'])!.value,
      companyLogo: this.editForm.get(['companyLogo'])!.value,
      bopportunityId: this.editForm.get(['bopportunityId'])!.value,
      projectId: this.editForm.get(['projectId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompany>>): void {
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
