import { Component, OnInit } from '@angular/core';
import { DoctorService } from 'src/app/services/doctor/doctor.service';
import { PacientService } from 'src/app/services/pacient/pacient.service';

@Component({
  selector: 'app-pacient-all-doctors',
  templateUrl: './pacient-all-doctors.component.html',
  styleUrls: ['./pacient-all-doctors.component.scss']
})
export class PacientAllDoctorsComponent implements OnInit {
  listUsers:any;
  constructor(public _pacient: PacientService) { }

  ngOnInit() {
    this.seeAllDoctors()
  }

  seeAllDoctors(){
    this._pacient.allCardiolog().subscribe((res)=>{
      console.log(res)
      this.listUsers = res
    })

  }
}
