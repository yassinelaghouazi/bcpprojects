import { Moment } from 'moment';
import { IBopportunity } from 'app/shared/model/bopportunity.model';
import { IMaitreOuvrage } from 'app/shared/model/maitre-ouvrage.model';
import { IBanque } from 'app/shared/model/banque.model';
import { IProject } from 'app/shared/model/project.model';
import { StatusCaution } from 'app/shared/model/enumerations/status-caution.model';
import { TypeCaution } from 'app/shared/model/enumerations/type-caution.model';

export interface ICaution {
  id?: number;
  idcaution?: number;
  montantCaution?: number;
  objet?: string;
  numeroCaution?: string;
  dateDemande?: Moment;
  dateRetrait?: Moment;
  dateDepot?: Moment;
  numeroMarche?: string;
  statusCaution?: StatusCaution;
  typeCaution?: TypeCaution;
  bopportuniys?: IBopportunity[];
  maitreouvrages?: IMaitreOuvrage[];
  banques?: IBanque[];
  projects?: IProject[];
}

export class Caution implements ICaution {
  constructor(
    public id?: number,
    public idcaution?: number,
    public montantCaution?: number,
    public objet?: string,
    public numeroCaution?: string,
    public dateDemande?: Moment,
    public dateRetrait?: Moment,
    public dateDepot?: Moment,
    public numeroMarche?: string,
    public statusCaution?: StatusCaution,
    public typeCaution?: TypeCaution,
    public bopportuniys?: IBopportunity[],
    public maitreouvrages?: IMaitreOuvrage[],
    public banques?: IBanque[],
    public projects?: IProject[]
  ) {}
}
