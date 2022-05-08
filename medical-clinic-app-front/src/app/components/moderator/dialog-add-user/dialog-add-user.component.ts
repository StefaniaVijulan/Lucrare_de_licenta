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
    if (this.editData) {
      this.myForm.controls['cnp'].setValue(this.editData.cnp);
      this.myForm.controls['cnp'].disable()
      this.myForm.controls['firstName'].setValue(this.editData.firstName);
      this.myForm.controls['lastName'].setValue(this.editData.lastName);
      this.myForm.controls['numberUser'].setValue(this.editData.numberUser);
      this.myForm.controls['emailUser'].setValue(this.editData.emailUser);
    }
  }
  register() {
    if (!this.editData) {
      if (this.myForm.valid) {
        console.log("Dialogul de adaugare a unui pacient este deschis!")
        if (this._service.newUserRole.toUpperCase() == "CARDIOLOG") {
          this._service.addCardiolog(this.myForm.value).subscribe((res) => {
              if (res == null) {
                console.log("Un cardiolog cu acest CNP exista deja!")
                this.msg = "Un cardiolog cu acest CNP exista deja!"
              } else {
                console.log("Cardiolog adaugat")
                //se reseteaza formularul
                this.myForm.reset();
                //se intoarce cu textul save
                this.dialogref.close("save");
              }

            }

          )
          this._service.getAllUsers()
        } else if (this._service.newUserRole.toUpperCase() == "SECRETAR") {
          this._service.addSecretaries(this.myForm.value).subscribe((res) => {
              if (res == null) {
                console.log("Un secretar cu acest CNP exista deja!")
                this.msg = "Un secretar cu acest CNP exista deja!"
              } else {
                console.log("Secretar adaugat")
                //se reseteaza formularul
                this.myForm.reset();
                //se intoarce cu textul save
                this.dialogref.close("save");
              }

            }

          )
          this._service.getAllUsers()
        }else if (this._service.newUserRole.toUpperCase() == "IMAGIST") {
          this._service.addImagists(this.myForm.value).subscribe((res) => {
              if (res == null) {
                console.log("Un imagist cu acest CNP exista deja!")
                this.msg = "Un imagist cu acest CNP exista deja!"
              } else {
                console.log("Imagist adaugat")
                //se reseteaza formularul
                this.myForm.reset();
                //se intoarce cu textul save
                this.dialogref.close("save");
              }

            }

          )
          this._service.getAllUsers()
        } else if (this._service.newUserRole.toUpperCase() == "HEMATOLOG") {
          this._service.addHematolog(this.myForm.value).subscribe((res) => {
              if (res == null) {
                console.log("Un hematolog cu acest CNP exista deja!")
                this.msg = "Un hematolog cu acest CNP exista deja!"
              } else {
                console.log("Hematolog adaugat")
                //se reseteaza formularul
                this.myForm.reset();
                //se intoarce cu textul save
                this.dialogref.close("save");
              }

            }

          )
          this._service.getAllUsers()
         
        }
      } else {
        this.msg = "Campurile obligatorii nu sunt completate"
      }

    } else {
      this.updateProduct(this.editData.role)

    }
   
  }
  updateProduct(info: any) {
    // rolul, cnpul userului si noul user
    this._service.editUser(info.toUpperCase(), this.editData.cnp, this.myForm.value).subscribe({
      next: (res) => {
        console.log("Userul a fost editat!")
        this.myForm.reset();
        this.dialogref.close("update");
      },
      error: () => {
        alert("errroooor")
      }
    })
  }
}