import { Moment } from 'moment';
import { IMaitreOuvrage } from 'app/shared/model/maitre-ouvrage.model';
import { ICompany } from 'app/shared/model/company.model';

export interface IBopportunity {
  id?: number;
  idBopportunity?: number;
  dateRemisePlis?: Moment;
  montantCaution?: number;
  objetAffaire?: string;
  estimationBudget?: number;
  commentaire?: string;
  numeroAppelOffre?: string;
  numeroMarche?: string;
  url?: string;
  maitreOuvrages?: IMaitreOuvrage[];
  companies?: ICompany[];
  cautionId?: number;
  projectId?: number;
}

export class Bopportunity implements IBopportunity {
  constructor(
    public id?: number,
    public idBopportunity?: number,
    public dateRemisePlis?: Moment,
    public montantCaution?: number,
    public objetAffaire?: string,
    public estimationBudget?: number,
    public commentaire?: string,
    public numeroAppelOffre?: string,
    public numeroMarche?: string,
    public url?: string,
    public maitreOuvrages?: IMaitreOuvrage[],
    public companies?: ICompany[],
    public cautionId?: number,
    public projectId?: number
  ) {}
}
