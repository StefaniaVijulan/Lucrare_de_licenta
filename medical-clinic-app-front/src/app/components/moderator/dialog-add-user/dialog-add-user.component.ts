import {  Component,  Inject,  OnInit} from '@angular/core';
import {  FormBuilder,  FormGroup,  Validators} from '@angular/forms';
import {  MatDialogRef,  MAT_DIALOG_DATA} from '@angular/material';
import {  Router} from '@angular/router';
import {  User} from 'src/app/interfaces/user';
import {  ModeratorService} from 'src/app/services/moderator/moderator.service';
import {  DialogComponent} from '../dialog/dialog.component';

@Component({
  selector: 'app-dialog-add-user',
  templateUrl: './dialog-add-user.component.html',
  styleUrls: ['./dialog-add-user.component.scss']
})
export class DialogAddUserComponent implements OnInit {

  myForm!: FormGroup;
  disabled = false;
 
  msg = ''
  actionBtn: string = "Save"
  constructor(private _service: ModeratorService,

    private _router: Router,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    private dialogref: MatDialogRef < DialogAddUserComponent >
  ) {}

  ngOnInit(): void {
    this.myForm = this.formBuilder.group({
      cnp: ['', Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d')])],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      numberUser: ['', Validators.compose([Validators.required, Validators.pattern('(\\d{3})(\\d{3})(\\d{4})')])],
      emailUser: ['', Validators.compose([Validators.required, Validators.email])]
    });
    console.log("edit before")
    console.log(this.editData);
    console.log(this.myForm.value)
    if (this.editData) {
      this.myForm.controls['cnp'].setValue(this.editData.cnp);
      this.myForm.controls['cnp'].disable()
      this.myForm.controls['firstName'].setValue(this.editData.firstName);
      this.myForm.controls['lastName'].setValue(this.editData.lastName);
      this.myForm.controls['numberUser'].setValue(this.editData.numberUser);
      this.myForm.controls['emailUser'].setValue(this.editData.emailUser);
    }else{
      
    }
    console.log("edit")
    console.log(this.editData);

    console.log(this.myForm.value)
  }

  register() {
    if (!this.editData) {
      if (this.myForm.valid) {
        if (this._service.newUserS.toUpperCase() == "CARDIOLOG") {
          this._service.addCardiolog(this.myForm.value).subscribe({
              next: (data) => {
                this.myForm.reset();
                this.dialogref.close("save");    
              },
              error: () => {
                this.msg = "Error while adding the product";
              }
            }

          )
          this._service.getAllUsers()
        }
        else if(this._service.newUserS.toUpperCase() == "SECRETAR") {
          this._service.addSecretaries(this.myForm.value).subscribe({
              next: (data) => {
                this._service.count = 1
                this.myForm.reset();
                this.dialogref.close("save");
              },
              error: () => {
                this.msg = "Error while adding the product";
              }
            }

          )
        }
        else if(this._service.newUserS.toUpperCase() == "IMAGIST") {
          this._service.addImagists(this.myForm.value).subscribe({
              next: (data) => {
                this._service.count = 1
                this.myForm.reset();
                this.dialogref.close("save");
              },
              error: () => {
                this.msg = "Error while adding the product";
              }
            }

          )
        }
        else if(this._service.newUserS.toUpperCase() == "HEMATOLOG") {
          this._service.addHematolog(this.myForm.value).subscribe({
              next: (data) => {
                this._service.count = 1
                this.myForm.reset();
                this.dialogref.close("save");
              },
              error: () => {
                this.msg = "Error while adding the product";
              }
            }

          )
        }
      }

    } else {
      this.updateProduct(this.editData.role) 
      
    }
  }
  updateProduct(info: any) {
    console.log(info)
    this._service.editUser(info.toUpperCase(), this.editData.cnp, this.myForm.value).subscribe({
      next: (res) => {
        console.log(res)
        this.myForm.reset();
        this.dialogref.close("update");
      },
      error:()=>{
        alert("errroooor")
      }
    })
  }
}