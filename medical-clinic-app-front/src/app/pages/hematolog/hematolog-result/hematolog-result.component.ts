import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogAddResultHematologyComponent } from 'src/app/components/dialog-add-result-hematology/dialog-add-result-hematology.component';
import { HematologService } from 'src/app/services/hematolog/hematolog.service';
import { AppointmentDoneComponent } from 'src/app/snacks/hematology/appointment-done/appointment-done.component';

@Component({
  selector: 'app-hematolog-result',
  templateUrl: './hematolog-result.component.html',
  styleUrls: ['./hematolog-result.component.scss']
})
export class HematologResultComponent implements OnInit {
  panelOpenState = false;
  listAppointmentsWithoutResultHema: any;
  durationInSeconds = 4;
  private _snackBar: any;
  constructor(public _hematolog: HematologService, private dialog: MatDialog) { }

  ngOnInit() {
    this.allTodayAppointmentsH()
  }
  allTodayAppointmentsH(){
    this._hematolog.getAllAppointemntWithoutResult().subscribe((res)=>{
      this.listAppointmentsWithoutResultHema = res;
      console.log(res)
    })
  }
  addResult(element){
    console.log("Add result")
      this._hematolog.resultHematology = element
      console.log(this._hematolog.resultHematology)
      this.dialog.open(DialogAddResultHematologyComponent,{
       width: '33%',
       panelClass: 'my-panel'
      }).afterClosed().subscribe(val=>{
       if(val === "save"){
         console.log("intra in save")
         this.allTodayAppointmentsH();
        /* this._snackBar.openFromComponent(AppointmentDoneComponent, {
          
          duration: this.durationInSeconds * 1000,
          panelClass: ['blue-snackbar']
        });*/
       }
     })

  }
}
