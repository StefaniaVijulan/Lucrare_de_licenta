import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
import { DialogAddAppointmentComponent } from '../dialog-add-appointment/dialog-add-appointment.component';

@Component({
  selector: 'app-dialog-cont',
  templateUrl: './dialog-cont.component.html',
  styleUrls: ['./dialog-cont.component.scss']
})
export class DialogContComponent implements OnInit {
  labelPosition: 'before' | 'after' = 'after';
  constructor(private dialog: MatDialog,   private dialogref: MatDialogRef < DialogContComponent >,) { }

  ngOnInit() {
  }
  nextStep(){
    if(this.labelPosition == 'after'){
      window.location.href = "/login"
    }else
    {
      this.dialogref.close()
      this.dialog.open(DialogAddAppointmentComponent,{
        width: '30%',
        panelClass: 'my-panel'
       });
    }

  }
}
