import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';
import { environment } from 'src/environments/environment';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import { MatDialog, MatPaginator, MatSort, MAT_DIALOG_DATA, Sort } from '@angular/material';
import { DialogComponent } from 'src/app/components/moderator/dialog/dialog.component';
import { DialogAddUserComponent } from 'src/app/components/moderator/dialog-add-user/dialog-add-user.component';
import { DialogDeleteUserComponent } from 'src/app/components/moderator/dialog-delete-user/dialog-delete-user.component';
import { element } from 'protractor';
import { Imagist } from 'src/app/interfaces/imagist';
import { DialogResetPassComponent } from 'src/app/components/moderator/dialog-reset-pass/dialog-reset-pass.component';

@Component({
  selector: 'app-moderator-imagist',
  templateUrl: './moderator-imagist.component.html',
  styleUrls: ['./moderator-imagist.component.scss']
})
export class ModeratorImagistComponent  implements OnInit {

  displayedColumns = ['cnp', 'firstName', 'lastName', 'emailUser','numberUser', 'role', 'action'];
  dataSource !: MatTableDataSource<User>;

  @ViewChild(MatPaginator, {static: true}) 
  paginator!: MatPaginator;
  @ViewChild(MatSort, {static: true}) 
  sort!: MatSort;



  constructor(private dialog: MatDialog, 
    public _moderator: ModeratorService,
     private _http: HttpClient, 
      public _service: AuthService) {
       
       }

       ngOnInit(): void {
        this.allImagist();
      }
    
      setRoleCase(data){
        for(let item of data){
          item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
          //console.log(item.role)
        }
      }
    
      allImagist(){
    
        return this._moderator.getAllimagists().subscribe((response: any) => {  
          console.log("response in allImagist - moderator_imagist.ts")
          console.log(response)
          this.setRoleCase(response)
          this.dataSource = new MatTableDataSource<Imagist>( response); 
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
      })}
      //metoda destinata adaugarii Cardiolog
      openAddDialog(data: any){
        //In variabila ”data” avem rolul persoanei pe care vrem sa o adaugam (cardiolog, secretar etc)
        this._moderator.newUserRole = data;
    
        this.dialog.open(DialogAddUserComponent,{
         width: '30%'
        }).afterClosed().subscribe(val=>{
          //daca se intoarce cu save
          if(val === "save"){
            console.log("Imagistul a fost adaugat cu succes!")
            this.allImagist();
          }
        });
      }
      editUser(element: any){
        this.dialog.open(DialogAddUserComponent,{
          width: '30%',
          data:element
         }).afterClosed().subscribe(val=>{
          console.log("Cardiologul a fost editat cu succes!")
          console.log(val)
          if(val === "update"){
            this.allImagist();
          }
        })
      }
      deleteUser(data: any){
        console.log(data)
        this._moderator.deleteUser(data).subscribe((res)=>{
          this.allImagist()
        })
      }
      resetPass(element: any){
        console.log("aici")
        return this._moderator.resetPassword(element).subscribe((res)=>{
          console.log("ori d")
          this.dialog.open(DialogResetPassComponent,{
            width: '30%',
            data:element
           })
        })
      
      }
  }
  
  
