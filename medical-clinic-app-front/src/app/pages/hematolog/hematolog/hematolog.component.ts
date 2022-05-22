import { Component, OnInit } from '@angular/core';
import { MatDialog, MatSnackBar } from '@angular/material';
import { timeStamp } from 'console';
import { HematologService } from 'src/app/services/hematolog/hematolog.service';
import { AppointmentDoneComponent } from 'src/app/snacks/hematology/appointment-done/appointment-done.component';

@Component({
  selector: 'app-hematolog',
  templateUrl: './hematolog.component.html',
  styleUrls: ['./hematolog.component.scss']
})
export class HematologComponent implements OnInit {
  panelOpenState = false;
  listAppointmentsHema: any;
  durationInSeconds = 4;
  constructor(public _hematolog: HematologService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.allTodayAppointmentsH()
  }
  allTodayAppointmentsH(){
    return this._hematolog.getAllHema().subscribe((res)=>{
      this.listAppointmentsHema = res
     console.log(res)
    })
  }
  appointmentDone(element: any){
    console.log("done")
    this._hematolog.seeAppointmentDone(element).subscribe((res)=>{
      console.log("res ", res)
      if(res == 1){
        this._snackBar.openFromComponent(AppointmentDoneComponent, {
          duration: this.durationInSeconds * 1000,
          panelClass: ['blue-snackbar']
        });
        this.allTodayAppointmentsH()
      }
    }
      
  ); 
      
      
    
   
  }
}
