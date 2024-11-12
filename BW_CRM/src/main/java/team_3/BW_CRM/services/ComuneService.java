package team_3.BW_CRM.services;

import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team_3.BW_CRM.repositories.ComuneRepository;

@Slf4j
@Service
public class ComuneService {

    @Autowired
    private ComuneRepository cr;

    @Autowired
    private Validator validator;



}
