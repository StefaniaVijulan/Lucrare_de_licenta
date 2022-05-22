import { Component, OnInit } from '@angular/core';
import { HematologService } from 'src/app/services/hematolog/hematolog.service';

@Component({
  selector: 'app-hematolog-result',
  templateUrl: './hematolog-result.component.html',
  styleUrls: ['./hematolog-result.component.scss']
})
export class HematologResultComponent implements OnInit {
  panelOpenState = false;
  listAppointmentsWithoutResultHema: any;
  durationInSeconds = 4;
  constructor(public _hematolog: HematologService) { }

  ngOnInit() {
    this.allTodayAppointmentsH()
  }
  allTodayAppointmentsH(){
    this._hematolog.getAllAppointemntWithoutResult().subscribe((res)=>{
      this.listAppointmentsWithoutResultHema = res;
      console.log(res)
    })
  }
 
}
