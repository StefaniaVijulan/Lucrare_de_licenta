import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';

@Component({
  selector: 'app-appointments-hematology',
  templateUrl: './appointments-hematology.component.html',
  styleUrls: ['./appointments-hematology.component.scss']
})
export class AppointmentsHematologyComponent implements OnInit {


  displayedColumns = ['dataAppointmentHematology','hourAppointmentHematology','cnpP'];
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
    this.allAppointmentHematology();
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }
  allAppointmentHematology(){
    console.log("intra in all appointment")
    return this._moderator.getAllAppointmentsH()
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