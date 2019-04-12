import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HistoryPageComponent } from './history-page/history-page.component';

@NgModule({
  declarations: [HistoryPageComponent],
  imports: [
    CommonModule
  ],
  exports: [HistoryPageComponent]
})
export class HistoryModule { }
