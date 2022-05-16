import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { timeStamp } from 'console';
import { HematologService } from 'src/app/services/hematolog/hematolog.service';

@Component({
  selector: 'app-hematolog',
  templateUrl: './hematolog.component.html',
  styleUrls: ['./hematolog.component.scss']
})
export class HematologComponent implements OnInit {
  panelOpenState = false;
  listAppointmentsHema: any;
  constructor(public _hematolog: HematologService, private dialog: MatDialog) { }

  ngOnInit() {
    this.allTodayAppointmentsH()
  }
  allTodayAppointmentsH(){
    return this._hematolog.getAllHema().subscribe((res)=>{
      this.listAppointmentsHema = res
     console.log(res)
    })
  }
  adaugaR(){
    console.log("merge")
  }
}
