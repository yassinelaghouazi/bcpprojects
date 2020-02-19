import { ICompany } from 'app/shared/model/company.model';
import { IBopportunity } from 'app/shared/model/bopportunity.model';

export interface IProject {
  id?: number;
  idProject?: number;
  projectName?: string;
  projectDescription?: string;
  budget?: number;
  numeroMarche?: string;
  companies?: ICompany[];
  bopportuniys?: IBopportunity[];
  cautionId?: number;
  deliveryId?: number;
  ordersId?: number;
  reglementId?: number;
}

export class Project implements IProject {
  constructor(
    public id?: number,
    public idProject?: number,
    public projectName?: string,
    public projectDescription?: string,
    public budget?: number,
    public numeroMarche?: string,
    public companies?: ICompany[],
    public bopportuniys?: IBopportunity[],
    public cautionId?: number,
    public deliveryId?: number,
    public ordersId?: number,
    public reglementId?: number
  ) {}
}
