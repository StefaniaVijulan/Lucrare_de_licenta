import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { HematologyResult } from 'src/app/interfaces/hematologyResult';
import { HematologService } from 'src/app/services/hematolog/hematolog.service';

@Component({
  selector: 'app-dialog-add-result-hematology',
  templateUrl: './dialog-add-result-hematology.component.html',
  styleUrls: ['./dialog-add-result-hematology.component.scss']
})
export class DialogAddResultHematologyComponent implements OnInit {

  resultH: HematologyResult;
   
  resultA: HematologyResult =  new HematologyResult()
  constructor(private  _hematolog: HematologService, private dialogref: MatDialogRef < DialogAddResultHematologyComponent >) {
   }

  ngOnInit() {
    this.resultH = this._hematolog.resultHematology;
  }
  saveResult(){  
    console.log(this.resultA)
    this._hematolog.editAppointmentResult(this.resultH.id, this.resultA).subscribe((res)=>{
      console.log(res)
      this.dialogref.close("save");
    })
  }
}
