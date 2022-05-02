import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';

@Component({
  selector: 'app-dialog-reset-pass',
  templateUrl: './dialog-reset-pass.component.html',
  styleUrls: ['./dialog-reset-pass.component.scss']
})
export class DialogResetPassComponent implements OnInit {

  constructor( private dialogref: MatDialogRef < DialogResetPassComponent >) { }

  ngOnInit() {
  }
  closeDialog(){
    this.dialogref.close("reset");
  }
  
}
