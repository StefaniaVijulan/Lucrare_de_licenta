import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { ImagistService } from 'src/app/services/imagist/imagist.service';
import { AppointmentDoneComponent } from 'src/app/snacks/hematology/appointment-done/appointment-done.component';

@Component({
  selector: 'app-imagist',
  templateUrl: './imagist.component.html',
  styleUrls: ['./imagist.component.scss']
})
export class ImagistComponent implements OnInit {
  panelOpenState = false;
  listAppointmentsRadio: any;
  durationInSeconds = 4;
  constructor(public _imagist: ImagistService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.allTodayAppointmentsR()
  }
 allTodayAppointmentsR(){
    return this._imagist.getAllRadio().subscribe((res)=>{
      this.listAppointmentsRadio = res
     console.log(res)
    })
  }
  appointmentDone(element: any){
    console.log("done")
    this._imagist.seeAppointmentDone(element).subscribe((res)=>{
      console.log("res ", res)
      if(res == 1){
        this.allTodayAppointmentsR()
        this._snackBar.openFromComponent(AppointmentDoneComponent, {
          duration: this.durationInSeconds * 1000,
          panelClass: ['blue-snackbar']
        });
        
      }
    }
      
  ); 

  }
}
