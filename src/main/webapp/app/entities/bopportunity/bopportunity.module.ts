import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BtpprojectsSharedModule } from 'app/shared/shared.module';
import { BopportunityComponent } from './bopportunity.component';
import { BopportunityDetailComponent } from './bopportunity-detail.component';
import { BopportunityUpdateComponent } from './bopportunity-update.component';
import { BopportunityDeleteDialogComponent } from './bopportunity-delete-dialog.component';
import { bopportunityRoute } from './bopportunity.route';

@NgModule({
  imports: [BtpprojectsSharedModule, RouterModule.forChild(bopportunityRoute)],
  declarations: [BopportunityComponent, BopportunityDetailComponent, BopportunityUpdateComponent, BopportunityDeleteDialogComponent],
  entryComponents: [BopportunityDeleteDialogComponent]
})
export class BtpprojectsBopportunityModule {}
