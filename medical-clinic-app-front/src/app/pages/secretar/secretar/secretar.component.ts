import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FlexLayoutModule} from '@angular/flex-layout';
import { Hospitalization } from 'src/app/interfaces/hospitalization';
import { SecretarService } from 'src/app/services/secretar/secretar.service';
import { Pacient } from 'src/app/interfaces/pacient';
@Component({
  selector: 'app-secretar',
  templateUrl: './secretar.component.html',
  styleUrls: ['./secretar.component.scss']
})
export class SecretarComponent implements OnInit {
  listHospitalization: any
  pacientL: Pacient[];
  constructor( public _secretar: SecretarService,) { }

  ngOnInit() {
    this.allHospit()
    
  }
  allHospit(){
    console.log("poste")
    return this._secretar.getAllHospitalization().subscribe((response: any) => {  
      console.log("aici")
      this.listHospitalization = response
      console.log(this.listHospitalization)
      console.log(this.listHospitalization[0].registrationNoHospitalization)
      
      })};
  specificInfoP(element: string){
    return this._secretar.getSpecificP(element).subscribe((response: any) => {  
      console.log("aici")
      this.pacientL = response
      console.log(this.pacientL)
    
      
      })};
  
  }

