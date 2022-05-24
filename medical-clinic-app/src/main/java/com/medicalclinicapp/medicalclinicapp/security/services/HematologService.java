package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.models.HematologyResult;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentHematologyRepository;

import com.medicalclinicapp.medicalclinicapp.repository.HematologyResultRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.HematologRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HematologService {

    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;

    @Autowired
    private HematologyResultRepository hematologyResultRepository;

    public List<AppointmentHematology> getAllAppointmentHematology(){
        List<AppointmentHematology> appointmentHematologyList =appointmentHematologyRepository.findAll();
        return appointmentHematologyList;
    }
    public List<AppointmentHematology> getAllTodayAppointmentHematology(){
        List<AppointmentHematology> appointmentList = new ArrayList<>();
        for (int i = 0; i < appointmentHematologyRepository.findAll().size(); i++) {
            {
                Date curentData = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                if (sdf.format(curentData).equals(appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology())) {
                    if(appointmentHematologyRepository.findAll().get(i).getDone()==null)
                        appointmentList.add(appointmentHematologyRepository.findAll().get(i));
                }
            }
        }
        return appointmentList;
    }
    public int seeAppointment(Long idA){
        for (int i = 0; i < appointmentHematologyRepository.findAll().size(); i++) {
            if(appointmentHematologyRepository.findAll().get(i).getId() == idA){
                appointmentHematologyRepository.findAll().get(i).setDone(true);
                appointmentHematologyRepository.save(appointmentHematologyRepository.findAll().get(i));
                HematologyResult hematologyResult = new HematologyResult();
                hematologyResult.setAppointmentHematology(appointmentHematologyRepository.findAll().get(i));
                hematologyResultRepository.save(hematologyResult);
                break;
            }
        }
        //"Consultatia a fost realizata cu succes!"
        return 1;
        }
    public List<HematologyResult> seeAppointmentsWithoutResult() {
        List<HematologyResult> hematologyResultList = new ArrayList<>();
        for (int i = 0; i < hematologyResultRepository.findAll().size(); i++) {
            if (hematologyResultRepository.findAll().get(i).getDone() == null) {
                hematologyResultList.add(hematologyResultRepository.findAll().get(i));
            }
        }
        return hematologyResultList;
    }
    public int resultDone (Long idR, HematologyResult hematologyResult){
        for (int i = 0; i < hematologyResultRepository.findAll().size(); i++) {
            if(hematologyResultRepository.findAll().get(i).getId() == idR){
                hematologyResultRepository.findAll().get(i).setColesterol_seric_total(hematologyResult.getColesterol_seric_total());
                hematologyResultRepository.findAll().get(i).setHdl_colesterol(hematologyResult.getHdl_colesterol());
                hematologyResultRepository.findAll().get(i).setLdl_colesterol(hematologyResult.getLdl_colesterol());                hematologyResultRepository.findAll().get(i).setAcid_uric(hematologyResult.getAcid_uric());
                hematologyResultRepository.findAll().get(i).setTrigliceride_serice(hematologyResult.getTrigliceride_serice());                hematologyResultRepository.findAll().get(i).setAcid_uric(hematologyResult.getAcid_uric());
                hematologyResultRepository.findAll().get(i).setGlicemie(hematologyResult.getGlicemie());
                hematologyResultRepository.findAll().get(i).setTgo(hematologyResult.getTgo());
                hematologyResultRepository.findAll().get(i).setTgp(hematologyResult.getTgp());
                hematologyResultRepository.findAll().get(i).setUree_serica(hematologyResult.getUree_serica());
                hematologyResultRepository.findAll().get(i).setCreatina_serica(hematologyResult.getCreatina_serica());
                hematologyResultRepository.findAll().get(i).setPotasiu_seric(hematologyResult.getPotasiu_seric());
                hematologyResultRepository.findAll().get(i).setMagneziu_seric(hematologyResult.getMagneziu_seric());
                hematologyResultRepository.findAll().get(i).setAcid_uric(hematologyResult.getAcid_uric());
                hematologyResultRepository.findAll().get(i).setCalciu_ionic_seric(hematologyResult.getCalciu_ionic_seric());
                hematologyResultRepository.findAll().get(i).setCalciu_seric_total(hematologyResult.getCalciu_seric_total());
                hematologyResultRepository.findAll().get(i).setInr_cu_interpretare(hematologyResult.getInr_cu_interpretare());
                hematologyResultRepository.findAll().get(i).setHemoleucograma_completa(hematologyResult.getHemoleucograma_completa());
                hematologyResultRepository.findAll().get(i).setT3(hematologyResult.getT3());
                hematologyResultRepository.findAll().get(i).setT4(hematologyResult.getT4());
                hematologyResultRepository.findAll().get(i).setTsh(hematologyResult.getTsh());
                hematologyResultRepository.findAll().get(i).setDone(true);
                hematologyResultRepository.save(hematologyResultRepository.findAll().get(i));
                break;
            }
        }
        //"Rezultatele au fost adaugate cu succes!"
        return 1;
    }

    public List<HematologyResult> seeAllResult() {
        List<HematologyResult> hematologyResultList = new ArrayList<>();
        for (int i = 0; i < hematologyResultRepository.findAll().size(); i++) {
            if (hematologyResultRepository.findAll().get(i).getDone() != null) {
                hematologyResultList.add(hematologyResultRepository.findAll().get(i));
            }
        }
        return hematologyResultList;
    }
}
    /*
    public Hematology addConsultationHematology(Long idAppointment, Hematology hematology, Principal principal) throws Exception {
        if(hematologyRepository.existsById(hematology.getId()))
            throw new Exception("Din consultation it was done");
        String username = principal.getName();
        Hematolog hematolog = this.hematologRepository.findByCnp(username);
      //  hematology.setHematolog(hematolog);

        for(int i=0; i<appointmentHematologyRepository.findAll().size(); i++){
            if(appointmentHematologyRepository.findAll().get(i).getId().equals(idAppointment)){
                hematology.setAppointmentHematology(appointmentHematologyRepository.findAll().get(i));
            }}


        hematologyRepository.save(hematology);
        return hematology;
    }
}
        */