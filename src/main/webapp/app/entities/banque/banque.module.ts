import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BtpprojectsSharedModule } from 'app/shared/shared.module';
import { BanqueComponent } from './banque.component';
import { BanqueDetailComponent } from './banque-detail.component';
import { BanqueUpdateComponent } from './banque-update.component';
import { BanqueDeleteDialogComponent } from './banque-delete-dialog.component';
import { banqueRoute } from './banque.route';

@NgModule({
  imports: [BtpprojectsSharedModule, RouterModule.forChild(banqueRoute)],
  declarations: [BanqueComponent, BanqueDetailComponent, BanqueUpdateComponent, BanqueDeleteDialogComponent],
  entryComponents: [BanqueDeleteDialogComponent]
})
export class BtpprojectsBanqueModule {}
