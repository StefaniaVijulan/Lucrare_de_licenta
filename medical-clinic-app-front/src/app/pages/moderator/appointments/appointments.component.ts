import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatPaginator, MatSort } from '@angular/material';
import { MatTableDataSource } from '@angular/material/table';
import { Appointment } from 'src/app/interfaces/appointment';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.scss']
})
export class AppointmentsComponent implements OnInit {


  displayedColumns = ['dataA','hour','cnp','firstName', 'lastName', 'emailUser', 'numberUser'];
  dataSource !: MatTableDataSource<any>;


  @ViewChild(MatPaginator, {static: true}) 
  paginator!: MatPaginator;

  @ViewChild(MatSort, {static: true}) 
  sort!: MatSort;
  constructor( 
    public _moderator: ModeratorService ) {
       
       }

  ngOnInit(): void {
    this.paginator._intl.itemsPerPageLabel="Numarul de elemente afisate:";
    this.allAppointment();
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }
  allAppointment(){
    console.log("intra in all appointment")
    return this._moderator.getAllAppointments()
    .subscribe((res:any)=>{
      console.log("response")
     this.dataSource =  new MatTableDataSource(res);
     this.dataSource.paginator = this.paginator;
     this.dataSource.sort = this.sort;
    }
   )};

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }}