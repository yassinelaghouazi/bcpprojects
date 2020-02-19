export interface ICompany {
  id?: number;
  idcompany?: number;
  companyName?: string;
  companyLogoContentType?: string;
  companyLogo?: any;
  bopportunityId?: number;
  projectId?: number;
}

export class Company implements ICompany {
  constructor(
    public id?: number,
    public idcompany?: number,
    public companyName?: string,
    public companyLogoContentType?: string,
    public companyLogo?: any,
    public bopportunityId?: number,
    public projectId?: number
  ) {}
}
