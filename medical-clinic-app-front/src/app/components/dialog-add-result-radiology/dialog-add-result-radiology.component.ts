import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { RadiologyResult } from 'src/app/interfaces/RadiologyResult';
import { ImagistService } from 'src/app/services/imagist/imagist.service';

@Component({
  selector: 'app-dialog-add-result-radiology',
  templateUrl: './dialog-add-result-radiology.component.html',
  styleUrls: ['./dialog-add-result-radiology.component.scss']
})
export class DialogAddResultRadiologyComponent implements OnInit {

  resultR: RadiologyResult
  resultA: RadiologyResult =  new RadiologyResult()
  constructor(private  _imagist: ImagistService, private dialogref: MatDialogRef < DialogAddResultRadiologyComponent >) {
   }

  ngOnInit() {
    this.resultR = this._imagist.resultRadiology;
  }
  saveResult(){  
    console.log(this.resultA)
    console.log("save")
  }
}