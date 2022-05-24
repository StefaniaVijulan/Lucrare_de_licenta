import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogAddResultRadiologyComponent } from 'src/app/components/dialog-add-result-radiology/dialog-add-result-radiology.component';
import { ImagistService } from 'src/app/services/imagist/imagist.service';

@Component({
  selector: 'app-imagist-result',
  templateUrl: './imagist-result.component.html',
  styleUrls: ['./imagist-result.component.scss']
})
export class ImagistResultComponent implements OnInit {
  panelOpenState = false;
  listAppointmentsWithoutResulRadio: any;
  durationInSeconds = 4;
  private _snackBar: any;
  constructor(public _imagist: ImagistService, private dialog: MatDialog) { }

  ngOnInit() {
    this.allTodayAppointmentsH()
  }
  allTodayAppointmentsH(){
    this._imagist.getAllAppointemntWithoutResult().subscribe((res)=>{
      this.listAppointmentsWithoutResulRadio = res;
      console.log(res)
    })
  }
  addResult(element){
    console.log("Add result")
    this._imagist.resultRadiology = element
    console.log(this._imagist.resultRadiology)
      this.dialog.open(DialogAddResultRadiologyComponent,{
       width: '33%',
       panelClass: 'my-panel'
      }).afterClosed().subscribe(val=>{
       if(val === "save"){
         console.log("intra in save")
         this.allTodayAppointmentsH();
       
       }
     })

  }
}